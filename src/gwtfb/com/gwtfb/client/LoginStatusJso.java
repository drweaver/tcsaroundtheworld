package com.gwtfb.client;

import com.google.gwt.core.client.JavaScriptObject;

public class LoginStatusJso extends JavaScriptObject {

	protected LoginStatusJso() {}

	public final native String getStatus() /*-{ return this.status; }-*/;
	public final native SessionJso getSession() /*-{ return this.session; }-*/;

}
