package org.tcsaroundtheworld.common.shared;

public interface HasPersonBase {
	String getCity();

	String getState();

	String getCountry();

	String getLatitude();

	String getLongitude();

	String getName();

	String getSurname();

	String getOccupation();

	String getDob();

	String getGender();

	String getOperations();

	String getInheritance();

	PictureReference getPictureReference();

	String getUserComments();

	boolean isContactable();

	String getWebsiteUrl();

	String getFacebookId();
	void setCity(final String city);

	void setState(final String state);

	void setCountry(final String country);

	void setLatitude(final String latitude);

	void setLongitude(final String longitude);

	void setName(final String name);

	void setSurname(final String surname);

	void setOccupation(final String occupation);

	void setDob(final String dob);

	void setGender(final String gender);

	void setOperations(final String operations);

	void setInheritance(final String inheritance);

	void setPictureReference(final PictureReference picture);

	void setUserComments(String userComments);

	void setContactable(final boolean contactable);

	void setWebsiteUrl(String websiteUrl);

	void setFacebookId(String facebookId);
}
