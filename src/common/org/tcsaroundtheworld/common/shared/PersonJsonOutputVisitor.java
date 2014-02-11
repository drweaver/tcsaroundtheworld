package org.tcsaroundtheworld.common.shared;

public class PersonJsonOutputVisitor extends AbstractOutputJson implements PersonVisitor {

	private String base = "";
	private String id = "";
	private String email = "";
	private String enabled = "";

	public void visit(final HasPersonBase p) {
		final StringBuilder sb = new StringBuilder();
		sb.append(jsonString("firstName", p.getName()))
		.append(jsonString("surname", p.getSurname()))
		.append(jsonString("dob", p.getDob()))
		.append(jsonString("gender", p.getGender()))
		.append(jsonString("occupation", p.getOccupation()))
		.append(jsonBoolean("contactable", p.isContactable()))
		.append(jsonString("operations", p.getOperations()))
		.append(jsonString("inheritance", p.getInheritance()))
		.append(jsonString("userComments", p.getUserComments()))
		.append(jsonString("websiteUrl", p.getWebsiteUrl()))
		.append(jsonString("city", p.getCity()))
		.append(jsonString("state", p.getState()))
		.append(jsonString("country", p.getCountry()))
		.append(jsonString("longitude", p.getLongitude()))
		.append(jsonString("latitude", p.getLatitude()))
		.append(jsonString("facebookId", p.getFacebookId()));
		if( p.getPictureReference() != null ) {
			sb.append(jsonString("pictureRef", p.getPictureReference().getId()));
		}
		base = sb.toString();
	}

	public void visit(final HasPersonId p) {
		final StringBuilder sb = new StringBuilder();
		sb.append(jsonString("familyId", Long.toString(p.getFamilyId())))
		.append(jsonString("id", Long.toString(p.getId())));
		id = sb.toString();

	}

	public void visit(final HasPersonEmail p) {
		final StringBuilder sb = new StringBuilder();
		sb.append( jsonString("email", p.getEmail()));
		email = sb.toString();
	}

	public void visit(final HasPersonEnabled p) {
		final StringBuilder sb = new StringBuilder();
		sb.append(jsonString("enabled", p.isEnabled()?"true":"false"));
		enabled = sb.toString();
	}

	@Override
	protected String jsonName() {
		return "Person";
	}

	@Override
	protected String innerJson() {
		final StringBuilder sb = new StringBuilder();
		sb.append( id ).append( email ).append( enabled ).append( base );
		return sb.toString();
	}


}
