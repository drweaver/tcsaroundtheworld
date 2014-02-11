package org.tcsaroundtheworld.common.shared;

public class PersonCopyFromVisitor implements PersonVisitor {

	private final HasPersonBase fromBase;
	private final HasPersonId fromId;
	private final HasPersonEmail fromEmail;
	private final HasPersonEnabled fromEnabled;

	public PersonCopyFromVisitor(final HasPersonBase fromBase, final HasPersonId fromId, final HasPersonEmail fromEmail, final HasPersonEnabled fromEnabled) {
		this.fromBase = fromBase;
		this.fromId = fromId;
		this.fromEmail = fromEmail;
		this.fromEnabled = fromEnabled;
	}

	public void visit(final HasPersonBase to) {
		if( fromBase!=null ) {
			to.setName(fromBase.getName());
			to.setSurname(fromBase.getSurname());
			to.setOccupation(fromBase.getOccupation());
			to.setGender(fromBase.getGender());
			to.setInheritance(fromBase.getInheritance());
			to.setDob(fromBase.getDob());
			to.setContactable(fromBase.isContactable());
			to.setOperations(fromBase.getOperations());
			to.setUserComments(fromBase.getUserComments());
			to.setWebsiteUrl(fromBase.getWebsiteUrl());
			to.setFacebookId(fromBase.getFacebookId());
			to.setCity(fromBase.getCity());
			to.setState(fromBase.getState());
			to.setCountry(fromBase.getCountry());
			to.setLongitude(fromBase.getLongitude());
			to.setLatitude(fromBase.getLatitude());
			to.setPictureReference(fromBase.getPictureReference());
		}
	}

	public void visit(final HasPersonId to) {
		if( fromId!=null ) {
			to.setFamilyId(fromId.getFamilyId());
			to.setId(fromId.getId());
		}
	}

	public void visit(final HasPersonEmail to) {
		if( fromEmail!=null ) {
			to.setEmail(fromEmail.getEmail());
		}
	}

	public void visit(final HasPersonEnabled to) {
		if( fromEnabled!=null ) {
			to.setEnabled(fromEnabled.isEnabled());
		}
	}

}
