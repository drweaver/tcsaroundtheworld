package org.tcsaroundtheworld.submit.client.map;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.InfoWindow;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.geocoder.Geocoder;
import com.google.gwt.maps.client.geocoder.GeocoderCallback;
import com.google.gwt.maps.client.geocoder.GeocoderRequest;
import com.google.gwt.maps.client.geocoder.HasAddressComponent;
import com.google.gwt.maps.client.geocoder.HasGeocoderResult;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddressSearch extends Composite {

	private static AddressSearchUiBinder uiBinder = GWT.create(AddressSearchUiBinder.class);

	interface AddressSearchUiBinder extends UiBinder<Widget, AddressSearch> {}

	@UiField SimplePanel mapContainer;
	@UiField TextBox addressQuery;
	@UiField Button addressSearch;
	@UiField Button ok;
	@UiField Button cancel;

	private final MapWidget map = createMap();
	private final Geocoder geocoder = new Geocoder();
	private final Marker mapMarker = new Marker();
	private final InfoWindow infoWindow = new InfoWindow();

	private String city = null;
	private String state = null;
	private String country = null;

	public AddressSearch() {
		initWidget(uiBinder.createAndBindUi(this));
		mapContainer.add( map );
	}

	private MapWidget createMap() {
		final MapOptions mapOptions = new MapOptions();
		mapOptions.setCenter(new LatLng(51.5001524, -0.1262362));
		mapOptions.setZoom(2);
		mapOptions.setDraggable(true);
		mapOptions.setNavigationControl(true);
		mapOptions.setMapTypeId(new MapTypeId().getRoadmap());
		final MapWidget map = new MapWidget(mapOptions);
		map.setSize("600px", "400px");
		return map;
	}

	@UiHandler("addressQuery")
	void checkEnterPressedInAddress(final KeyUpEvent event) {
		if( event.getNativeKeyCode() == KeyCodes.KEY_ENTER ) {
			showAddress();
		}
	}

	@UiHandler("addressSearch")
	void addressSearchClicked(final ClickEvent event) {
		showAddress();
	}

	public void showAddress() {

		final GeocoderRequest request = new GeocoderRequest();
		request.setAddress(SafeHtmlUtils.htmlEscape(addressQuery.getText()));
		geocoder.geocode(request, new GeocoderCallback() {
			@Override
			public void callback(final List<HasGeocoderResult> responses, final String status) {
				if( !status.equals( "OK" ) ) {
					Window.alert("Sorry, that address did not goecode correctly, please try a town/village name followed by a city/country");
					return;
				}
				for( final HasGeocoderResult r : responses ) {
					String country = null;
					String city = null;
					String state = null;
					String county = null;
					for( final HasAddressComponent a : r.getAddressComponents() ) {
						if( a.getTypes().contains("country") ) {
							country = a.getLongName();
						}
						if( a.getTypes().contains("locality") ) {
							city = a.getLongName();
						}
						if( a.getTypes().contains("administrative_area_level_2") ) {
							county = a.getLongName();
						}
						if( a.getTypes().contains("administrative_area_level_1") ) {
							state = a.getLongName();
						}
					}
					if( county == null ) {
						county = state;
					}

					updateMarkerAndFields(r.getGeometry().getLocation(), city, country!=null&&country.equals("United States")?state:county, country );
					break;
				}
			}
		});

	}

	private void updateMarkerAndFields(final HasLatLng position, final String city, final String state, final String country) {

		if( city == null || city.length() == 0 ) {
			Window.alert("The address you supplied is too vague, please supply at least a city or town");
		} else {
			mapMarker.setPosition(position);
			mapMarker.setMap(map.getMap());

			final String message = city + ", " + state + ", " + country;
			map.getMap().setZoom(12);
			infoWindow.setContent(message);
			infoWindow.open(map.getMap(), mapMarker);

			this.city = city;
			this.state = state;
			this.country = country;
			ok.setEnabled(true);
		}
	}

	public void focusQueryBox() {
		addressQuery.setFocus(true);
	}

	public double getLng() {
		return mapMarker.getPosition().getLongitude();
	}
	public double getLat() {
		return mapMarker.getPosition().getLatitude();
	}
	public String getLngString() {
		return Double.toString(getLng());
	}
	public String getLatString() {
		return Double.toString(getLat());
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}

	public TextBox getAddressQuery() {
		return addressQuery;
	}

	public Button getAddressSearch() {
		return addressSearch;
	}

	public Button getOk() {
		return ok;
	}

	public Button getCancel() {
		return cancel;
	}



}
