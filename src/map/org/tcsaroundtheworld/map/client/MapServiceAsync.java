package org.tcsaroundtheworld.map.client;

import java.util.List;

import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.map.shared.LocationStats;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MapServiceAsync {

	public void getCountryStats(AsyncCallback<LocationStats> callback);

	void getFamilies(AsyncCallback<List<Family>> callback);

}
