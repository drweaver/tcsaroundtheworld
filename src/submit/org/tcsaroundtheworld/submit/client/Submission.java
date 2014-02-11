package org.tcsaroundtheworld.submit.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Submission implements EntryPoint {

	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get().add( new FamilySubmissionWidget() );

	}

	public static native boolean extendedMode() /*-{
		return typeof($wnd.extended_mode) != "undefined" && $wnd.extended_mode == true;
	}-*/;


}
