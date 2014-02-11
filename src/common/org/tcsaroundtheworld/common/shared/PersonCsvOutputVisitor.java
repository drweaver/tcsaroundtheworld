package org.tcsaroundtheworld.common.shared;

import java.util.Arrays;

public class PersonCsvOutputVisitor implements PersonVisitor, OutputsCsv {

	private String base = "";
	private String id = "";
	private String email = "";
	private String enabled = "";

	@SuppressWarnings("unchecked")
	public void visit(final HasPersonBase p) {
		final StringBuilder sb  = new StringBuilder();
		for( final Object o : Arrays.asList(p.getName(), p.getSurname(), p.getDob(), p.getGender(), p.getOccupation(), p.isContactable(), p.getOperations(), p.getInheritance(), p.getUserComments(), p.getWebsiteUrl(), p.getCity(), p.getState(), p.getCountry(), p.getLongitude(), p.getLatitude(), p.getFacebookId() ) ) {
			sb.append(o).append(",");
		}
		sb.append(p.getPictureReference()==null?",":p.getPictureReference().getId()+",");
		base = sb.toString();
	}

	public void visit(final HasPersonId p) {
		final StringBuilder sb = new StringBuilder();
		sb.append(p.getFamilyId()).append(",").append(p.getId()).append(",");
		id = sb.toString();
	}

	public void visit(final HasPersonEmail person) {
		final StringBuilder sb = new StringBuilder();
		sb.append(person.getEmail()).append(",");
		email = sb.toString();
	}

	public void visit(final HasPersonEnabled person) {
		final StringBuilder sb = new StringBuilder();
		sb.append(person.isEnabled()).append(",");
		enabled = sb.toString();
	}

	public String toCsv() {
		final StringBuilder sb = new StringBuilder();
		sb.append( id ).append( email ).append(enabled).append(base);
		return sb.toString();
	}

	public String csvHeader() {
		final StringBuilder sb = new StringBuilder();
		for( final Object o : Arrays.asList("familyId", "id", "email", "enabled", "name", "surname", "dob", "gender", "occupation",
				"contactable", "operations", "inheritance", "userComments", "websiteUrl", "city",
				"state", "country", "longitude", "latitude", "facebookId", "pictureRef" ) ) {
			sb.append(o).append(",");
		}
		return sb.toString();
	}
}
