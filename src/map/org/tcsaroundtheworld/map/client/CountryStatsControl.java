package org.tcsaroundtheworld.map.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.MapWidget;

class CountryStatsControl {

	public void add(final MapWidget mapWidget) {

		final Element control = MapControlUtil.createControl("See country stats", "Country Stats");

		MapControlUtil.addControlBottomLeft(mapWidget.getMap().getJso(), control);

		MapControlUtil.addDialogClickListener(control, new CountryStatsDialog());

	}

}
