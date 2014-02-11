package com.gwtfb.client;

import com.google.gwt.core.client.JavaScriptObject;

public class SessionJso extends JavaScriptObject {

	protected SessionJso() {}

	public final native String getUid() /*-{ return this.uid; }-*/;

}
