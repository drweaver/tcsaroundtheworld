package org.tcsaroundtheworld.map.client;

import java.util.List;

import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.map.shared.LocationStats;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mapservice")
public interface MapService extends RemoteService {

	public LocationStats getCountryStats();

	public List<Family> getFamilies();

}
