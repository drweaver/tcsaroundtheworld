package org.tcsaroundtheworld.common.server.db;

import java.util.Date;

import javax.persistence.Id;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.HasPersonEnabled;
import org.tcsaroundtheworld.common.shared.HasPersonId;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;
import org.tcsaroundtheworld.submit.server.DateParserServer;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

public class PersonEntity implements IsPerson, HasPersonBase, HasPersonEmail, HasPersonEnabled, HasPersonId {

	public enum Gender {
		MALE("Male"), FEMALE("Female");

		private final String name;

		private Gender(final String name) {
			this.name = name;
		}

		public static Gender fromString(final String genderString) {
			final String genderStringLower = genderString.trim().toLowerCase();
			if( genderStringLower.equals("male") ) {
				return MALE;
			}
			if( genderStringLower.equals("female") ) {
				return FEMALE;
			}
			throw new RuntimeException("Invalid gender: " + genderString );
		}

		@Override
		public String toString() {
			return name;
		}
	};
	public enum Inheritance {
		INHERITED("Inherited"), SPORADIC("Sporadic");

		private final String name;

		private Inheritance(final String name) {
			this.name = name;
		}

		public static Inheritance fromString(final String inhString) {
			final String inhStringLower = inhString.trim().toLowerCase();
			if( inhStringLower.equals("inherited") ) {
				return INHERITED;
			}
			if( inhStringLower.equals("sporadic") ) {
				return SPORADIC;
			}
			throw new RuntimeException("Invalid inheritance: " + inhString );
		}

		@Override
		public String toString() {
			return name;
		}
	};


	@Id	Long id;

	@Unindexed	String firstName;
	@Unindexed	String surname;
	@Unindexed	String email;
	@Unindexed	String occupation;
	@Unindexed	Date dob;
	@Unindexed	Gender gender;
	@Unindexed	boolean contactable;
	@Unindexed	Integer operations;
	@Unindexed	Inheritance inheritance;
	@Unindexed	String userComments;
	@Unindexed	String websiteUrl;
	@Unindexed String facebookId;


	@Unindexed	String city;
	//	@Indexed(IfUS.class)
	@Unindexed	String state;
	@Unindexed	String country;

	@Unindexed	double lat;
	@Unindexed	double lng;

	@Unindexed	String pictureRef;

	@Parent	Key<FamilyEntity> familyKey;

	boolean enabled = true;

	public PersonEntity() {
	}

	public PersonEntity(final Key<FamilyEntity> familyKey) {
		this.familyKey = familyKey;
	}
	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(final String facebookId) {
		this.facebookId = facebookId;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(final String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}


	private static boolean isEmptyString(final String s) {
		return s.trim().length()==0;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return firstName;
	}

	public void setName(final String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(final String occupation) {
		this.occupation = occupation;
	}

	public String getDob() {
		return dob==null?"":new DateParserServer(dob).toString();
	}

	public void setDob(final String dob) {
		this.dob = isEmptyString(dob)?null:DateParserServer.parse(dob).getDate();
	}

	public String getGender() {
		return gender.toString();
	}

	public void setGender(final String gender) {
		this.gender = Gender.fromString(gender);
	}

	public boolean isContactable() {
		return contactable;
	}

	public void setContactable(final boolean contactable) {
		this.contactable = contactable;
	}

	public String getOperations() {
		return operations==null?"":Integer.toString(operations);
	}

	public void setOperations(final String operations) {
		this.operations = isEmptyString(operations)?null:Integer.valueOf(operations);
	}

	public String getInheritance() {
		return inheritance.toString();
	}

	public void setInheritance(final String inheritance) {
		this.inheritance = Inheritance.fromString(inheritance);
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(final String userComments) {
		this.userComments = userComments;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getLatitude() {
		return Double.toString(lat);
	}

	public void setLatitude(final String lat) {
		this.lat = Double.valueOf(lat);
	}

	public String getLongitude() {
		return Double.toString(lng);
	}

	public void setLongitude(final String lng) {
		this.lng = Double.valueOf(lng);
	}

	public PictureReference getPictureReference() {
		return pictureRef==null?null:new PictureReference( pictureRef );
	}

	public void setPictureReference(final PictureReference pictureRef) {
		this.pictureRef = pictureRef == null ? null : pictureRef.getId();
	}

	public long getFamilyId() {
		return familyKey.getId();
	}

	public void setId(final long id) {
		//NOP
	}

	public void setFamilyId(final long familyId) {
		//NOP
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public void accept(final PersonVisitor visitor) {
		final HasPersonBase base = this;
		final HasPersonEmail email = this;
		final HasPersonId id = this;
		final HasPersonEnabled enabled = this;
		visitor.visit(base);
		visitor.visit(id);
		visitor.visit(email);
		visitor.visit(enabled);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, this, this, this);
	}

}
