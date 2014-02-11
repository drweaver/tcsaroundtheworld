package org.tcsaroundtheworld.map.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Map implements EntryPoint {

	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		PersonInfoWindowBuilder.exportOpenContactFormDialog();

		RootLayoutPanel.get().add( new TreacherCollinsMapWidget() );

	}

}
