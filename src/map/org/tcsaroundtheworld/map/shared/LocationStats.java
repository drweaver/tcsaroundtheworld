package org.tcsaroundtheworld.map.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LocationStats implements IsSerializable {

	public static class LocationCount implements IsSerializable {
		String location;
		public String getLocation() {
			return location;
		}
		public Integer getCount() {
			return count;
		}
		Integer count;
		public LocationCount() {
		}
		public LocationCount(String location, Integer count) {
			super();
			this.location = location;
			this.count = count;
		}
	}

	List<LocationCount> countryCount;
	List<LocationCount> usStateCount;
	public List<LocationCount> getCountryCount() {
		return countryCount;
	}
	public void setCountryCount(List<LocationCount> countryCount) {
		this.countryCount = countryCount;
	}
	public List<LocationCount> getUsStateCount() {
		return usStateCount;
	}
	public void setUsStateCount(List<LocationCount> usStateCount) {
		this.usStateCount = usStateCount;
	}



}
