package org.tcsaroundtheworld.admin.client;

import java.util.List;

import org.tcsaroundtheworld.admin.shared.FamilyFull;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AdminServiceAsync {
	void clearMemcache(AsyncCallback<Void> asyncCallback);

	void getFamilies(AsyncCallback<List<FamilyFull>> asyncCallback);

	void enablePerson(long familyId, long id, Boolean value, AsyncCallback<Void> asyncCallback);

	void approveFamily(long id, AsyncCallback<Void> asyncCallback);

	void deletePerson(long id, AsyncCallback<Void> asyncCallback);

	void deleteFamily(long id, AsyncCallback<Void> asyncCallback);

}
