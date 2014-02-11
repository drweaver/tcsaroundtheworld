package org.tcsaroundtheworld.common.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonEnabled implements IsSerializable, IsPersonComponent, HasPersonEnabled {

	boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public void accept(final PersonVisitor visitor) {
		visitor.visit(this);
	}
}
