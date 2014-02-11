package com.gwtfb.client;

import com.google.gwt.core.client.JavaScriptObject;

public class UserJso extends JavaScriptObject {

	protected UserJso() {}

	public final native String getId() /*-{ return this.id; }-*/;
	public final native String getName() /*-{ return this.first_name; }-*/;
	public final native String getSurname() /*-{ return this.last_name; }-*/;
	public final native String getEmail() /*-{ return this.email; }-*/;
	public final native String getBirthday() /*-{ return this.birthday; }-*/;
	public final native String getUserComments() /*-{ return this.bio; }-*/;
	public final native String getWebsiteUrl() /*-{ return this.website; }-*/;
	public final native String getGender() /*-{ return this.gender; }-*/;
	public final native String getCity() /*-{ return this.hometown; }-*/;

	public final String getDob() {
		String birthday = getBirthday();
		if( birthday == null || !birthday.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d") ) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(birthday.substring(3, 6))
		.append(birthday.substring(0, 3))
		.append(birthday.substring(6));
		return sb.toString();
	}

}
