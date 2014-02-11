package org.tcsaroundtheworld.map.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.HasJso;
import com.google.gwt.user.client.ui.DialogBox;

class MapControlUtil {

	static Element createControl(final String title, final String text) {
		final Element controlDiv = Document.get().createElement("DIV");

		// Set CSS styles for the DIV containing the control
		// Setting padding to 5 px will offset the control
		// from the edge of the map
		controlDiv.getStyle().setPadding(5, Unit.PX);

		// Set CSS for the control border
		final Element controlUI = Document.get().createElement("DIV");
		controlUI.getStyle().setBackgroundColor("white");
		controlUI.getStyle().setBorderStyle(BorderStyle.SOLID);
		controlUI.getStyle().setBorderWidth(2, Unit.PX);
		controlUI.getStyle().setCursor(Cursor.POINTER);
		//		controlUI.getStyle().settextAlign = "center";
		controlUI.setTitle(title);
		controlDiv.appendChild(controlUI);

		// Set CSS for the control interior
		final Element controlText = Document.get().createElement("DIV");
		//		controlText.getStyle(). = "Arial,sans-serif";
		controlText.getStyle().setFontSize(12, Unit.PX);
		controlText.getStyle().setPaddingLeft(4, Unit.PX);
		controlText.getStyle().setPaddingRight(4, Unit.PX);
		controlText.setInnerHTML( text );
		controlUI.appendChild(controlText);
		return controlDiv;
	}

	static native void addControlTopRight(JavaScriptObject map, JavaScriptObject control) /*-{
		map.controls[$wnd.google.maps.ControlPosition.TOP_RIGHT].push(control);
	}-*/;

	static native void addControlBottomLeft(JavaScriptObject map, JavaScriptObject control) /*-{
	map.controls[$wnd.google.maps.ControlPosition.BOTTOM_LEFT].push(control);
}-*/;

	static native void addControlTopCenter(JavaScriptObject map, JavaScriptObject control) /*-{
	map.controls[$wnd.google.maps.ControlPosition.TOP_CENTER].push(control);
}-*/;

	static native void addFitBoundsClickListener(JavaScriptObject map, JavaScriptObject control, JavaScriptObject latlngbounds) /*-{
		$wnd.google.maps.event.addDomListener(control, "click", function() {
			map.fitBounds(latlngbounds)
		});
	}-*/;

	static native void addControlTopLeft(final JavaScriptObject map, final JavaScriptObject control) /*-{
		map.controls[$wnd.google.maps.ControlPosition.TOP_LEFT].push(control);
	}-*/;

	static native void addHistoryChangeClickListener(JavaScriptObject control, String token) /*-{
		$wnd.google.maps.event.addDomListener(control, "click", function() {
			@com.google.gwt.user.client.History::newItem(Ljava/lang/String;)(token);
		});
	}-*/;

	static native void addDialogClickListener(JavaScriptObject control, DialogBox dialog) /*-{
		$wnd.google.maps.event.addDomListener(control, "click", function() {
			dialog.@org.tcsaroundtheworld.map.client.CountryStatsDialog::center()();
		});
	}-*/;

	static HasJso hasJsoWrapper( final JavaScriptObject jso ) {
		return new HasJso() {
			public JavaScriptObject getJso() {
				return jso;
			}
		};
	}

	static native void addLinkClickListener(Element control, String href) /*-{
		$wnd.google.maps.event.addDomListener(control, "click", function() {
			$wnd.location.assign(href);
		});
	}-*/;

}
