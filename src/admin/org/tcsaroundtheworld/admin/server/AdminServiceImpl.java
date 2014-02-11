package org.tcsaroundtheworld.admin.server;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import org.tcsaroundtheworld.admin.client.AdminService;
import org.tcsaroundtheworld.admin.shared.FamilyFull;
import org.tcsaroundtheworld.common.server.db.DAO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService {

	DAO dao = new DAO();

	Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

	public void clearMemcache() {
		try {
			final Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
			cache.clear();
		} catch (final CacheException e) {
			log.log(Level.WARNING, "Failed to clear memcache", e);
			throw new RuntimeException(e);
		}
	}

	public List<FamilyFull> getFamilies() {
		return dao.getFamiliesForAdmin();
	}

	public void enablePerson(final long familyId, final long id, final Boolean value) {
		dao.enablePerson(familyId, id, value);
	}

	public void approveFamily(final long id) {
		dao.approveFamily(id);
	}

	public void deletePerson(final long id) {
		dao.deletePerson(id);
	}

	public void deleteFamily(final long id) {
		dao.deleteFamily(id);
	}


}
