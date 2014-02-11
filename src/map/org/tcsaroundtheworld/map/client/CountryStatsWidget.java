package org.tcsaroundtheworld.map.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class CountryStatsWidget extends Composite {

	private static CountryStatsWidgetUiBinder uiBinder = GWT.create(CountryStatsWidgetUiBinder.class);

	interface CountryStatsWidgetUiBinder extends UiBinder<Widget, CountryStatsWidget> {
	}

	public CountryStatsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField FlexTable countryTable;
	@UiField FlexTable statesTable;

	public CountryStatsWidget(final String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
