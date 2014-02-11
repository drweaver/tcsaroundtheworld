package org.tcsaroundtheworld.map.server;

import java.util.List;

import org.tcsaroundtheworld.common.server.db.DAO;
import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.map.client.MapService;
import org.tcsaroundtheworld.map.shared.LocationStats;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MapServiceImpl extends RemoteServiceServlet implements MapService {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();

	public LocationStats getCountryStats() {
		return dao.getCountryStats();
	}

	public List<Family> getFamilies() {
		return dao.getFamiliesForPublic();
	}

}
