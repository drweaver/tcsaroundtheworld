package org.tcsaroundtheworld.map.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.tcsaroundtheworld.map.shared.LocationStats;
import org.tcsaroundtheworld.map.shared.LocationStats.LocationCount;



public class LocationStatsCollector {

	private final Map<String, Integer> countryCount = new HashMap<String, Integer>();

	private final Map<String, Integer> usStateCount = new HashMap<String, Integer>();


	public static void incrementCount(final Map<String,Integer> map, final String key) {
		if( !map.containsKey(key) ) {
			map.put(key, 0);
		}

		Integer count = map.get(key);
		count = count+1;
		map.put(key, count);
	}

	private static List<LocationCount> toLocationCountList(final Map<String, Integer> mapCount) {
		final List<LocationCount> list = new ArrayList<LocationCount>();
		for( final Entry<String, Integer> e : mapCount.entrySet() ) {
			list.add( new LocationCount( e.getKey(), e.getValue() ) );
		}
		return list;
	}

	public void incrementCountry(final String country) {
		incrementCount(countryCount, country);
	}

	public void incrementUsCounty(final String state) {
		incrementCount(usStateCount, state);
	}

	public LocationStats toLocationStats() {
		final LocationStats stats = new LocationStats();
		stats.setCountryCount(toLocationCountList(countryCount));
		stats.setUsStateCount(toLocationCountList(usStateCount));
		return stats;
	}

}
