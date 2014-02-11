package org.tcsaroundtheworld.submit.client;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;

import com.google.gwt.core.client.JavaScriptObject;

public class PersonSubmissionJso extends JavaScriptObject implements IsPerson, HasPersonBase, HasPersonEmail {

	protected PersonSubmissionJso() {}

	public final native String getName() /*-{ return this.Person.firstName; }-*/;
	public final native String getSurname() /*-{ return this.Person.surname; }-*/;
	public final native String getEmail() /*-{ return this.Person.email; }-*/;
	public final native String getDob() /*-{ return this.Person.dob; }-*/;
	public final native String getGender() /*-{ return this.Person.gender; }-*/;
	public final native String getOccupation() /*-{ return this.Person.occupation; }-*/;
	public final native boolean isContactable() /*-{ return this.Person.contactable == true; }-*/;
	public final native String getOperations() /*-{ return this.Person.operations; }-*/;
	public final native String getInheritance() /*-{ return this.Person.inheritance; }-*/;
	public final native String getCity() /*-{ return this.Person.city; }-*/;
	public final native String getState() /*-{ return this.Person.state; }-*/;
	public final native String getCountry() /*-{ return this.Person.country; }-*/;
	public final native String getLatitude() /*-{ return this.Person.latitude; }-*/;
	public final native String getLongitude() /*-{ return this.Person.longitude; }-*/;
	public final native String getPictureRef() /*-{ return this.Person.pictureRef; }-*/;
	public final native String getUserComments() /*-{ return this.Person.userComments; }-*/;
	public final native String getWebsiteUrl() /*-{ return this.Person.websiteUrl; }-*/;
	public final native String getFacebookId() /*-{ return this.Person.facebookId; }-*/;
	public final PictureReference getPictureReference() {
		return getPictureRef()==null?null:new PictureReference(getPictureRef());
	}
	public static native PersonSubmissionJso parseJson(String json) /*-{ return eval('(' + json + ')') }-*/;

	public final void setEmail(final String email) {
		//NOP

	}

	public final void setCity(final String city) {
		//NOP

	}

	public final void setState(final String state) {
		//NOP

	}

	public final void setCountry(final String country) {
		//NOP

	}

	public final void setLatitude(final String latitude) {
		//NOP

	}

	public final void setLongitude(final String longitude) {
		//NOP

	}

	public final void setName(final String name) {
		//NOP

	}

	public final void setSurname(final String surname) {
		//NOP

	}

	public final void setOccupation(final String occupation) {
		//NOP

	}

	public final void setDob(final String dob) {
		//NOP

	}

	public final void setGender(final String gender) {
		//NOP

	}

	public final void setOperations(final String operations) {
		//NOP

	}

	public final void setInheritance(final String inheritance) {
		//NOP

	}

	public final void setPictureReference(final PictureReference picture) {
		//NOP

	}

	public final void setUserComments(final String userComments) {
		//NOP

	}

	public final void setContactable(final boolean contactable) {
		//NOP

	}

	public final void setWebsiteUrl(final String websiteUrl) {
		//NOP

	}

	public final void setFacebookId(final String facebookId) {
		//NOP

	}

	public final void accept(final PersonVisitor visitor) {
		final HasPersonBase base = this;
		final HasPersonEmail email = this;
		visitor.visit(base);
		visitor.visit(email);
	}

	public final PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, null, this, null);
	}

}
