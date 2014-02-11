package org.tcsaroundtheworld.map.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MapHelpWidget extends Composite {

	private static MapHelpWidgetUiBinder uiBinder = GWT.create(MapHelpWidgetUiBinder.class);

	interface MapHelpWidgetUiBinder extends UiBinder<Widget, MapHelpWidget> {
	}

	private final MapHelp mapHelp = GWT.create(MapHelp.class);

	@UiField SpanElement aboutThisSite;
	@UiField SpanElement aboutMe;
	@UiField SpanElement updatingSubmissions;
	@UiField SpanElement removingSubmissions;
	@UiField Anchor contactLink;

	public MapHelpWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		aboutThisSite.setInnerHTML(mapHelp.aboutThisSite());
		aboutMe.setInnerHTML(mapHelp.aboutMe());
		updatingSubmissions.setInnerHTML(mapHelp.updatingSubmissions());
		removingSubmissions.setInnerHTML(mapHelp.removingSubmissions());
		contactLink.setHref("javascript:;");
	}

}
