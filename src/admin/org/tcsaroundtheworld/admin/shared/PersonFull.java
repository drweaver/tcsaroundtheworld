package org.tcsaroundtheworld.admin.shared;

import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonBase;
import org.tcsaroundtheworld.common.shared.PersonEmail;
import org.tcsaroundtheworld.common.shared.PersonEnabled;
import org.tcsaroundtheworld.common.shared.PersonId;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonFull implements IsSerializable, IsPerson {

	PersonId id = new PersonId();
	PersonEmail email = new PersonEmail();
	PersonEnabled enabled = new PersonEnabled();
	PersonBase base = new PersonBase();

	public void accept(final PersonVisitor visitor) {
		id.accept(visitor);
		email.accept(visitor);
		enabled.accept(visitor);
		base.accept(visitor);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(base, id, email, enabled);
	}

	public PersonBase getBase() {
		return base;
	}

	public PersonEmail getEmail() {
		return email;
	}

	public PersonEnabled getEnabled() {
		return enabled;
	}

	public PersonId getId() {
		return id;
	}

}
