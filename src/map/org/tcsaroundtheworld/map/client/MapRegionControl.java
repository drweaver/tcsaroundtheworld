package org.tcsaroundtheworld.map.client;

import java.util.Arrays;

import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;

class MapRegionControl {

	public static MapRegionControl UK = new MapRegionControl("UK", 49.50, -10.94, 58.88, 2.24);
	public static MapRegionControl N_AMERICA = new MapRegionControl("N. America", 8.8, -168.2, 69.16, -56.4 );
	public static MapRegionControl S_AMERICA = new MapRegionControl("S. America", -55.88, -87.7, 14.9, -35.2 );
	public static MapRegionControl USA = new MapRegionControl("USA", 26.27, -125.07, 47.87, -64.51);
	public static MapRegionControl EUROPE = new MapRegionControl("Europe", 35.39, -10.02, 69.50, 27.51 );
	public static MapRegionControl ASIA = new MapRegionControl("Asia", -2.1, 35.2, 71.86, 152.8 );
	public static MapRegionControl AUSTRALIA = new MapRegionControl("Australia", -43.96, 112.76,-10.49, 152.58);
	public static MapRegionControl AFRICA = new MapRegionControl("Africa", -34.09, -16.44, 36.24, 49.04 );
	public static MapRegionControl WORLD = new MapRegionControl("World", -47.0, -148.7, 71.4, 160.0);


	public static void addDefaultControls(final MapWidget mapWidget) {
		for( final MapRegionControl c : Arrays.asList(UK, USA, N_AMERICA, S_AMERICA, AUSTRALIA, AFRICA, EUROPE, ASIA, WORLD) ) {
			c.addControl(mapWidget);
		}
	}

	private final String regionName;
	private final LatLngBounds regionBounds;

	public MapRegionControl(final String regionName, final double SWLat, final double SWLng, final double NELat, final double NELng) {
		this( regionName, new LatLngBounds(new LatLng(SWLat, SWLng), new LatLng(NELat, NELng)) );
	}

	public MapRegionControl(final String regionName, final LatLngBounds regionBounds) {
		this.regionName = regionName;
		this.regionBounds = regionBounds;
	}

	public void addControl(final MapWidget mapWidget) {

		final Element controlDiv = MapControlUtil.createControl("Click to change region", regionName);

		MapControlUtil.addFitBoundsClickListener( mapWidget.getMap().getJso(), controlDiv, regionBounds.getJso());

		MapControlUtil.addControlTopRight( mapWidget.getMap().getJso(), controlDiv );

	}

	public void fitToBounds(final MapWidget mapWidget) {
		mapWidget.getMap().fitBounds(regionBounds);
	}

}
