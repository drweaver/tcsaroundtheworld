package org.tcsaroundtheworld.submit.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Contact implements EntryPoint {

	public void onModuleLoad() {

		RootPanel.get().add( new ContactFormWidget() );

	}

}
