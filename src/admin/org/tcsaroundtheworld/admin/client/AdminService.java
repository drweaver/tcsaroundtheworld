package org.tcsaroundtheworld.admin.client;

import java.util.List;

import org.tcsaroundtheworld.admin.shared.FamilyFull;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("adminservice")
public interface AdminService extends RemoteService {
	void clearMemcache();

	List<FamilyFull> getFamilies();

	void enablePerson(long familyId, long id, Boolean value);

	void approveFamily(long id);

	void deletePerson(long id);

	void deleteFamily(long id);

}
