package org.tcsaroundtheworld.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AdminTasks extends Composite {

	private static AdminTasksUiBinder uiBinder = GWT.create(AdminTasksUiBinder.class);

	interface AdminTasksUiBinder extends UiBinder<Widget, AdminTasks> {}

	AdminServiceAsync adminService = GWT.create(AdminService.class);

	public AdminTasks() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("clearMemcache")
	public void clearMemcache(ClickEvent event) {
		adminService.clearMemcache( new AsyncCallback<Void>() {
			public void onFailure(final Throwable caught) {
				Window.alert("Failed to communicate with server");
			}
			public void onSuccess(final Void result) {
				Window.alert("Memcache cleared");
			}
		});
	}

}
