package org.tcsaroundtheworld.submit.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ContactSubmission implements IsSerializable {

	private Long familyId;
	private Long personId;
	private String name;
	private String email;
	private String message;

	public ContactSubmission() {
	}

	public ContactSubmission(final Long familyId, final Long personId, final String name,
			final String email, final String message) {
		this.familyId = familyId;
		this.personId = personId;
		this.name = name;
		this.email = email;
		this.message = message;

	}

	public Long getFamilyId() {
		return familyId;
	}

	public Long getPersonId() {
		return personId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMessage() {
		return message;
	}



}
