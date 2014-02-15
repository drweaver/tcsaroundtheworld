package org.tcsaroundtheworld.map.client;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

class DataLoadingDialog extends DialogBox {

	final HorizontalPanel panel = new HorizontalPanel();

	public DataLoadingDialog() {
		setAutoHideEnabled(true);
		setAutoHideOnHistoryEventsEnabled(true);
		setGlassEnabled(false);
		panel.setSpacing(5);
		panel.add(new Label("Please wait...") );
		panel.add(new Image("img/loading.gif") );
		setWidget(panel);
		setText("Loading Map Data");
	}

}
