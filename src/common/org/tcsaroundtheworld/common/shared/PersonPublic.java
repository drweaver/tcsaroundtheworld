package org.tcsaroundtheworld.common.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonPublic implements IsPerson, IsSerializable {

	PersonBase base = new PersonBase();
	PersonId id = new PersonId();

	public void accept(final PersonVisitor visitor) {
		base.accept(visitor);
		id.accept(visitor);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(base, id, null, null);
	}

	public PersonBase getBase() {
		return base;
	}

	public PersonId getId() {
		return id;
	}
}
