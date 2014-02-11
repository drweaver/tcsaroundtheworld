package org.tcsaroundtheworld.submit.client;

import java.util.HashMap;
import java.util.Map;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;



public class PersonSubmissionEmailImport implements IsPerson, HasPersonBase, HasPersonEmail {

	final Map<String,String> entries = new HashMap<String, String>();

	public PersonSubmissionEmailImport(final String emailText) {
		final String[] lines = emailText.split("[\n\r]");
		for( final String l : lines ) {
			final String[] components = l.split("\\s*=\\s*");
			if( components.length >= 2 ) {
				entries.put( components[0].trim(), components[1].trim());
			}
		}
	}
	public String getEmail() {
		return entries.get("replyemail");
	}

	public String getCity() {
		return null;
	}

	public String getCountry() {
		return null;
	}

	public String getDob() {
		if( entries.get("day")!=null && entries.get("month")!=null && entries.get("year1")!=null && entries.get("year2")!= null) {
			return entries.get("day")+"/"+entries.get("month")+"/19"+entries.get("year1")+entries.get("year2");
		} else {
			return null;
		}
	}

	public String getGender() {
		final String gender = entries.get("gender");
		if( gender == null ) {
			return null;
		} else if( gender.toLowerCase().startsWith("m") ) {
			return "Male";
		} else if( gender.toLowerCase().startsWith("f") ) {
			return "Female";
		}
		return null;
	}

	public String getInheritance() {
		final String inh = entries.get("inherit");
		if( inh == null ) {
			return null;
		} else if( inh.toLowerCase().startsWith("s") ) {
			return "Sporadic";
		} else if( inh.toLowerCase().startsWith("i") ) {
			return "Inherited";
		}
		return null;
	}

	public String getLatitude() {
		return null;
	}

	public String getLongitude() {
		return null;
	}

	public String getName() {
		return entries.get("fname");
	}

	public String getOccupation() {
		return entries.get("occupation");
	}

	public String getOperations() {
		return entries.get("ops");
	}

	public PictureReference getPictureReference() {
		return null;
	}

	public String getState() {
		return null;
	}

	public String getSurname() {
		return entries.get("sname");
	}

	public String getUserComments() {
		return entries.get("comments");
	}

	public boolean isContactable() {
		return true;
	}

	public String getAddressSearch() {
		if( entries.get("town")!=null && entries.get("country")!=null ) {
			return entries.get("town") + ", " + entries.get("country");
		} else {
			return null;
		}
	}

	public String getWebsiteUrl() {
		return null;
	}

	public String getFacebookId() {
		return null;
	}
	public void setEmail(final String email) {
		//NOP

	}
	public void setCity(final String city) {
		//NOP

	}
	public void setState(final String state) {
		//NOP

	}
	public void setCountry(final String country) {
		//NOP

	}
	public void setLatitude(final String latitude) {
		//NOP

	}
	public void setLongitude(final String longitude) {
		//NOP

	}
	public void setName(final String name) {
		//NOP

	}
	public void setSurname(final String surname) {
		//NOP

	}
	public void setOccupation(final String occupation) {
		//NOP

	}
	public void setDob(final String dob) {
		//NOP

	}
	public void setGender(final String gender) {
		//NOP

	}
	public void setOperations(final String operations) {
		//NOP

	}
	public void setInheritance(final String inheritance) {
		//NOP

	}
	public void setPictureReference(final PictureReference picture) {
		//NOP

	}
	public void setUserComments(final String userComments) {
		//NOP

	}
	public void setContactable(final boolean contactable) {
		//NOP

	}
	public void setWebsiteUrl(final String websiteUrl) {
		//NOP

	}
	public void setFacebookId(final String facebookId) {
		//NOP

	}
	public void accept(final PersonVisitor visitor) {
		final HasPersonBase base = this;
		final HasPersonEmail email = this;
		visitor.visit(base);
		visitor.visit(email);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, null, this, null);
	}
}
