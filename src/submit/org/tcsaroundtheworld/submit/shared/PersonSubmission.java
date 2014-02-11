package org.tcsaroundtheworld.submit.shared;

import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonBase;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PersonEmail;
import org.tcsaroundtheworld.common.shared.PersonVisitor;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonSubmission implements IsPerson, IsSerializable {

	PersonBase base = new PersonBase();
	PersonEmail email = new PersonEmail();

	public PersonBase getBase() {
		return base;
	}

	public PersonEmail getEmail() {
		return email;
	}

	public void accept(final PersonVisitor visitor) {
		base.accept(visitor);
		email.accept(visitor);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(base, null, email, null);
	}



}
