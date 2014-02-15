package org.tcsaroundtheworld.common.server.db;

import java.util.Date;

import javax.persistence.Id;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.HasPersonEnabled;
import org.tcsaroundtheworld.common.shared.HasPersonId;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;
import org.tcsaroundtheworld.submit.server.DateParserServer;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

@Cached
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
	@Override
	public String getFacebookId() {
		return facebookId;
	}

	@Override
	public void setFacebookId(final String facebookId) {
		this.facebookId = facebookId;
	}

	@Override
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	@Override
	public void setWebsiteUrl(final String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}


	private static boolean isEmptyString(final String s) {
		return s.trim().length()==0;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return firstName;
	}

	@Override
	public void setName(final String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String getOccupation() {
		return occupation;
	}

	@Override
	public void setOccupation(final String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String getDob() {
		return dob==null?"":new DateParserServer(dob).toString();
	}

	@Override
	public void setDob(final String dob) {
		this.dob = isEmptyString(dob)?null:DateParserServer.parse(dob).getDate();
	}

	@Override
	public String getGender() {
		return gender.toString();
	}

	@Override
	public void setGender(final String gender) {
		this.gender = Gender.fromString(gender);
	}

	@Override
	public boolean isContactable() {
		return contactable;
	}

	@Override
	public void setContactable(final boolean contactable) {
		this.contactable = contactable;
	}

	@Override
	public String getOperations() {
		return operations==null?"":Integer.toString(operations);
	}

	@Override
	public void setOperations(final String operations) {
		this.operations = isEmptyString(operations)?null:Integer.valueOf(operations);
	}

	@Override
	public String getInheritance() {
		return inheritance.toString();
	}

	@Override
	public void setInheritance(final String inheritance) {
		this.inheritance = Inheritance.fromString(inheritance);
	}

	@Override
	public String getUserComments() {
		return userComments;
	}

	@Override
	public void setUserComments(final String userComments) {
		this.userComments = userComments;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(final String city) {
		this.city = city;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(final String state) {
		this.state = state;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(final String country) {
		this.country = country;
	}

	@Override
	public String getLatitude() {
		return Double.toString(lat);
	}

	@Override
	public void setLatitude(final String lat) {
		this.lat = Double.valueOf(lat);
	}

	@Override
	public String getLongitude() {
		return Double.toString(lng);
	}

	@Override
	public void setLongitude(final String lng) {
		this.lng = Double.valueOf(lng);
	}

	@Override
	public PictureReference getPictureReference() {
		return pictureRef==null?null:new PictureReference( pictureRef );
	}

	@Override
	public void setPictureReference(final PictureReference pictureRef) {
		this.pictureRef = pictureRef == null ? null : pictureRef.getId();
	}

	@Override
	public long getFamilyId() {
		return familyKey.getId();
	}

	@Override
	public void setId(final long id) {
		//NOP
	}

	@Override
	public void setFamilyId(final long familyId) {
		//NOP
	}

	@Override
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	@Override
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

	@Override
	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, this, this, this);
	}

}
