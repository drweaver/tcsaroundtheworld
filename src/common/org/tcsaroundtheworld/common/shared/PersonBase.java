package org.tcsaroundtheworld.common.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonBase implements IsSerializable, IsPersonComponent, HasPersonBase {

	String name;
	String surname;
	String occupation;
	String dob;
	String gender;
	boolean contactable;
	String operations;
	String inheritance;
	String city;
	String state;
	String country;
	String latitude;
	String longitude;
	String userComments;
	PictureReference pictureReference;
	String websiteUrl;
	String facebookId;


	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(final String facebookId) {
		this.facebookId = facebookId;
	}

	/*
	 * Empty constructor to satisfy serialisable policy
	 */
	public PersonBase() {}

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
		return latitude;
	}
	public void setLatitude(final String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(final String longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(final String occupation) {
		this.occupation = occupation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(final String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(final String gender) {
		this.gender = gender;
	}
	public boolean isContactable() {
		return contactable;
	}
	public void setContactable(final boolean contactable) {
		this.contactable = contactable;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(final String operations) {
		this.operations = operations;
	}
	public String getInheritance() {
		return inheritance;
	}
	public void setInheritance(final String inheritance) {
		this.inheritance = inheritance;
	}
	public void setPictureReference(final PictureReference picture) {
		pictureReference = picture;
	}
	public PictureReference getPictureReference() {
		return pictureReference;
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}

	public void setUserComments(final String userComments) {
		this.userComments = userComments;
	}
	public String getUserComments() {
		return userComments;
	}

	public void setWebsiteUrl(final String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void accept(final PersonVisitor visitor) {
		visitor.visit(this);
	}



}
