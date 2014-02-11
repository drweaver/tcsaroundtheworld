package org.tcsaroundtheworld.common.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonId implements IsSerializable, IsPersonComponent, HasPersonId {

	long id;
	long familyId;

	public long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(final long familyId) {
		this.familyId = familyId;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void accept(final PersonVisitor visitor) {
		visitor.visit( this );
	}

}
