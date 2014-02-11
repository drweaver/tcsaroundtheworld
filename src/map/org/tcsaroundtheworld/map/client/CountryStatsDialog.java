package org.tcsaroundtheworld.map.client;

import java.util.Collections;
import java.util.Comparator;

import org.tcsaroundtheworld.map.shared.LocationStats;
import org.tcsaroundtheworld.map.shared.LocationStats.LocationCount;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

class CountryStatsDialog extends DialogBox implements AsyncCallback<LocationStats> {

	MapServiceAsync mapService = GWT.create(MapService.class);

	VerticalPanel mainPanel = new VerticalPanel();

	public CountryStatsDialog() {
		setAutoHideEnabled(true);
		setAutoHideOnHistoryEventsEnabled(true);
		setGlassEnabled(true);
		setWidget(mainPanel);
		setText("Country Stats");
		final HorizontalPanel loadingPanel = new HorizontalPanel();
		loadingPanel.add( new Image("img/loading.gif") );
		loadingPanel.add( new Label("Loading stats, please wait...") );
		mainPanel.add( loadingPanel );
		mapService.getCountryStats(this);
	}

	public void onFailure(final Throwable caught) {
		mainPanel.clear();
		mainPanel.add( new Label("Failed to load stats from server: "+caught.getMessage()));
	}

	public void onSuccess(final LocationStats result) {
		mainPanel.clear();
		final CountryStatsWidget statsWidget = new CountryStatsWidget();
		mainPanel.add( statsWidget );
		final Comparator<LocationCount> comparator = new Comparator<LocationCount>() {
			public int compare(final LocationCount arg0, final LocationCount arg1) {
				return arg1.getCount() - arg0.getCount();
			}
		};
		Collections.sort(result.getCountryCount(), comparator);
		Collections.sort(result.getUsStateCount(), comparator);
		int row=0;
		for( final LocationCount c : result.getCountryCount() ) {
			statsWidget.countryTable.setText(row, 0, c.getLocation());
			statsWidget.countryTable.setText(row, 1, c.getCount().toString());
			row++;
		}
		row = 0;
		for( final LocationCount c : result.getUsStateCount()) {
			statsWidget.statesTable.setText(row, 0, c.getLocation());
			statsWidget.statesTable.setText(row, 1, c.getCount().toString());
			row++;
		}
		final Anchor closeLink = new Anchor("close");
		closeLink.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				CountryStatsDialog.this.hide();
			}
		});
		mainPanel.add( closeLink );
	}

}
