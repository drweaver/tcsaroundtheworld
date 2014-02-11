package org.tcsaroundtheworld.map.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.MapWidget;

class HelpControl {

	public void add(final MapWidget mapWidget) {

		final Element control = MapControlUtil.createControl("Information About this Site", "Help");

		MapControlUtil.addControlBottomLeft(mapWidget.getMap().getJso(), control);

		MapControlUtil.addDialogClickListener(control, new HelpDialog());

	}

}
