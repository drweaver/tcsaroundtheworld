package org.tcsaroundtheworld.map.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.MapWidget;

class SubmissionControl {

	public void add(final MapWidget mapWidget) {

		final Element control = MapControlUtil.createControl("Submit new entry to the map", "Submission Form");

		MapControlUtil.addControlBottomLeft(mapWidget.getMap().getJso(), control);

		MapControlUtil.addLinkClickListener(control, "/submission.html");

	}



}
