package org.tcsaroundtheworld.map.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.common.shared.PersonBase;
import org.tcsaroundtheworld.common.shared.PersonPublic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.InfoWindow;
import com.google.gwt.maps.client.base.InfoWindowOptions;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.base.Size;
import com.google.gwt.maps.client.event.Event;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerImage;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;


class TreacherCollinsMapWidget extends Composite implements ValueChangeHandler<String> {

	private final MapServiceAsync mapService = GWT.create(MapService.class);

	private final MapWidget mapWidget;

	private final InfoWindow infoWindow = new InfoWindow();

	final Map<String, MarkerControl> markers = new HashMap<String, MarkerControl>();

	final PersonInfoWindowBuilder personInfoWindowBuilder = new PersonInfoWindowBuilder();

	final DataLoadingDialog dataLoadingDialog = new DataLoadingDialog();
	private boolean dataLoaded = false;

	private final RecentMembersWidget recent = new RecentMembersWidget();

	public interface InfoWindowTemplates extends SafeHtmlTemplates {
		@Template("<li><a href='#tab-{0}'><span>{1}</span></a></li>")
		SafeHtml listItem(int tab, String name);
		@Template("<div id='tab-{0}'>{1}</div>")
		SafeHtml itemContent(int tab, SafeHtml content);
	}

	InfoWindowTemplates infoWindowTemplates = GWT.create(InfoWindowTemplates.class);

	public TreacherCollinsMapWidget() {

		final MapOptions mapOptions = new MapOptions();
		mapOptions.setCenter(new LatLng(51.5001524, -0.1262362));
		mapOptions.setZoom(2);
		mapOptions.setDraggable(true);
		mapOptions.setNavigationControl(true);
		mapOptions.setMapTypeId(new MapTypeId().getRoadmap());
		mapWidget = new MapWidget(mapOptions);
		mapWidget.setSize("100%", "100%");
		mapService.getFamilies(new AsyncCallback<List<Family>>() {
			@Override
			public void onFailure(final Throwable caught) {
				GWT.log("Failed to load families", caught);
			}
			@Override
			public void onSuccess(final List<Family> result) {
				dataLoaded = true;
				dataLoadingDialog.hide();
				createFamilyMarkers(result);
				createCountryMarkers(result);
				recent.populate(result, 5);
				History.addValueChangeHandler(TreacherCollinsMapWidget.this);
				History.fireCurrentHistoryState();
			}
		});

		final InfoWindowOptions options = new InfoWindowOptions();
		options.setMaxWidth(500);
		infoWindow.setOptions(options );

		initWidget(mapWidget);


		MapRegionControl.WORLD.fitToBounds(mapWidget);

		MapRegionControl.addDefaultControls(mapWidget);

		new HelpControl().add(mapWidget);

		new SubmissionControl().add(mapWidget);

		new CountryStatsControl().add(mapWidget);

		new RecentMembersControl().add(mapWidget, recent);

		if( dataLoaded == false ) {
			dataLoadingDialog.center();
		}
	}

	public void reset() {
		final Timer timer = new Timer() {
			@Override
			public void run() {
				checkResize();
				MapRegionControl.WORLD.fitToBounds(mapWidget);
			}
		};
		timer.schedule(300);
	}

	private void createCountryMarkers(final List<Family> result) {

	}

	private void createFamilyMarkers(final List<Family> result) {
		for( final Family f : result ) {
			for( final PersonPublic p : f.getMembers() ) {
				final PersonBase base = p.getBase();
				final LatLng position = getPersonPosition(base);
				//				while( markers.containsKey(createRoundedPositionKey(position)) ) {
				//					fuzzPosition(p);
				//					position = getPersonPosition(p);
				//				}
				final String posKey = createRoundedPositionKey(position);
				final List<PersonPublic> relatives = new ArrayList<PersonPublic>( f.getMembers() );
				relatives.remove(p);
				if( markers.containsKey(posKey) ) {
					markers.get( posKey ).addPerson( p );
				} else {
					final Marker marker = new Marker();
					marker.setPosition(position);
					marker.setClickable(true);
					Event.addListener(marker,"click" , new EventCallback() {
						@Override
						public void callback() {

							openPersonInfo( posKey );
							GWT.log("Tracking person bubble opened for "+base.getName()+' '+base.getSurname());
							trackPersonOpened( base.getName()+' '+base.getSurname() );
						}
					});

					markers.put( posKey, new MarkerControl(marker, p));
				}
			}
		}
		clusterMarkers();
	}

	protected native void trackPersonOpened(String name) /*-{
		$wnd._gaq.push(['_trackEvent', 'Members', 'Opened', 'Name', name]);
	}-*/;

	private void openPersonInfo(final String posKey) {
		final MarkerControl markerControl = markers.get(posKey);
		final SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<div id='tabs' class=tabs>\n");
		if( markerControl.people.size() > 1 ) {
			sb.appendHtmlConstant("<ul>");
			for( int i=0; i< markerControl.people.size(); i++ ) {
				final PersonPublic p = markerControl.people.get(i);
				sb.append(infoWindowTemplates.listItem(i, p.getBase().getName()));
			}
			sb.appendHtmlConstant("</ul>");
		}
		for( int i=0; i< markerControl.people.size(); i++ ) {
			final PersonPublic p = markerControl.people.get(i);
			sb.append(infoWindowTemplates.itemContent(i, personInfoWindowBuilder.build(p.getBase(),p.getId())));
		}
		sb.appendHtmlConstant("</div>");
		infoWindow.setContent(sb.toSafeHtml().asString());
		infoWindow.open(mapWidget.getMap(), markerControl.marker);
		if( markerControl.people.size() > 1 ) {
			initInfoWindowTabs();
		}
	}

	private native void initInfoWindowTabs() /*-{
		$wnd.$(".tabs").tabs();
	}-*/;

	private LatLng getPersonPosition( final PersonBase p ) {
		final Double lat = Double.valueOf( p.getLatitude() );
		final Double lng = Double.valueOf( p.getLongitude() );
		return new LatLng(lat, lng);
	}

	private static class MarkerControl {
		Marker marker;
		private final List<PersonPublic> people = new ArrayList<PersonPublic>();
		public MarkerControl(final Marker marker, final PersonPublic firstPerson) {
			this.marker = marker;
			people.add( firstPerson );
		}
		public void addPerson( final PersonPublic person ) {
			people.add( person );
			updateIcon();
		}
		private void updateIcon() {
			final String imgUrl = "img/red0"+people.size()+".png";
			final String shwUrl = "img/red-shadow.png";
			final MarkerImage img = new MarkerImage.Builder(imgUrl)
			.setSize(new Size(27,27))
			.setAnchor(new Point(13, 0))
			.setOrigin(new Point(0,0)).build();
			final MarkerImage shw = new MarkerImage.Builder(shwUrl)
			.setSize(new Size(41,27))
			.setAnchor(new Point(13,0))
			.setOrigin(new Point(0,0)).build();
			marker.setIcon( img );
			setShadow(marker.getJso(), shw.getJso());
		}
		private native void setShadow(JavaScriptObject marker, JavaScriptObject shw) /*-{
			marker.setShadow( shw );
		}-*/;
	}

	private String createRoundedPositionKey( final LatLng latLng ) {
		final double lat = Math.round( latLng.getLatitude() * 1000d ) / 1000d;
		final double lng = Math.round( latLng.getLongitude() * 1000d ) / 1000d;
		final String key = Double.toString(lat) + "," + Double.toString(lng);
		return key;
	}

	private void clusterMarkers() {
		final JsArray<JavaScriptObject> markerArray = JsArray.createArray().cast();
		for( final MarkerControl m : markers.values() ) {
			markerArray.push(m.marker.getJso());
		}
		clusterMarkers( mapWidget.getMap().getJso(), markerArray );
	}

	private native void clusterMarkers(JavaScriptObject map, JavaScriptObject markerArray) /*-{
		var mcOptions = {'gridSize': 25, 'maxZoom': 15};
		var mc = new $wnd.MarkerClusterer(map, markerArray, mcOptions);
	}-*/;

	@Override
	public void onValueChange(final ValueChangeEvent<String> event) {
		final String[] tokens = event.getValue().split("/");
		if( tokens.length > 1 && tokens[0].toUpperCase().equals("PERSON" ) ) {
			if( markers.containsKey( tokens[1] ) ) {
				openPersonInfo(tokens[1]);
			}
		}
	}

	public void checkResize() {
		checkResizeJsni(mapWidget.getMap().getJso());
	}

	private static native void checkResizeJsni(JavaScriptObject mapJso) /*-{
		$wnd.google.maps.event.trigger(mapJso, 'resize');
		mapJso.setZoom( mapJso.getZoom() );
	}-*/;

}
