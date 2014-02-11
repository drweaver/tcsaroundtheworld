package org.tcsaroundtheworld.common.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonEmail implements IsSerializable, IsPersonComponent, HasPersonEmail {

	private String email;

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void accept(final PersonVisitor visitor) {
		visitor.visit(this);
	}

}
