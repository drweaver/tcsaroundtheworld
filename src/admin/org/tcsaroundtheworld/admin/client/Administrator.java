package org.tcsaroundtheworld.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class Administrator extends Composite implements EntryPoint {


	public void onModuleLoad() {

		RootPanel.get().add( new AdminControl() );

	}


}
