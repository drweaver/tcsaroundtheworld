package org.tcsaroundtheworld.common.server.db;

import com.googlecode.objectify.condition.ValueIf;

public class IfUS extends ValueIf<String> {

	@Override
	public boolean matches(String country) {
		return country.equals("US");
	}

}
