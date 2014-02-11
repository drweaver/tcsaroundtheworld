package org.tcsaroundtheworld.map.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

class RecentMembersControl {

	public void add(final MapWidget mapWidget, final RecentMembersWidget recent) {

		final Element control = MapControlUtil.createControl("Recent Submissions", "Recent Submissions");

		MapControlUtil.addControlBottomLeft(mapWidget.getMap().getJso(), control);

		final VerticalPanel panel = new VerticalPanel();
		final DialogBox dialog = new DialogBox();
		dialog.setAnimationEnabled(true);
		dialog.setAutoHideEnabled(true);
		dialog.setGlassEnabled(true);
		dialog.setText("Recent Submissions");
		dialog.add( panel );
		panel.add( recent );
		panel.add( new Button("Close", new ClickHandler() {
			public void onClick(final ClickEvent event) {
				dialog.hide();
			}
		}));

		MapControlUtil.addDialogClickListener(control, dialog);

	}

}
