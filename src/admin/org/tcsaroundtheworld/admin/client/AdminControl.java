package org.tcsaroundtheworld.admin.client;

import java.util.ArrayList;
import java.util.List;

import org.tcsaroundtheworld.admin.shared.FamilyFull;
import org.tcsaroundtheworld.admin.shared.PersonFull;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminControl extends Composite implements ValueChangeHandler<String> {

	private static AdminControlUiBinder uiBinder = GWT.create(AdminControlUiBinder.class);

	interface AdminControlUiBinder extends UiBinder<Widget, AdminControl> {
	}

	public static final String AWAITINGAPPROVAL = "awaitingapproval";
	public static final String ALL = "all";

	AdminServiceAsync adminService = GWT.create(AdminService.class);

	@UiField SimplePanel content;

	List<PersonFull> personsToExport = new ArrayList<PersonFull>();

	public AdminControl() {
		initWidget(uiBinder.createAndBindUi(this));
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();
	}

	@UiHandler(ALL)
	public void loadAllFamilies(final ClickEvent event) {
		requestFamilies(SubmissionDisplay.Filter.NONE);
	}

	@UiHandler(AWAITINGAPPROVAL)
	public void loadNewSubmissions(final ClickEvent event) {
		requestFamilies(SubmissionDisplay.Filter.AWAITING_APPROVAL);
	}

	@UiHandler("tasks")
	public void loadTasks(final ClickEvent event) {
		content.clear();
		content.add( new AdminTasks() );
	}

	public void onValueChange(final ValueChangeEvent<String> event) {
		final String token = event.getValue();
		if( token.equals(ALL) ) {
			requestFamilies(SubmissionDisplay.Filter.NONE);
		} else if( token.equals(AWAITINGAPPROVAL) ) {
			requestFamilies(SubmissionDisplay.Filter.AWAITING_APPROVAL);
		}

	}

	public void requestFamilies(final SubmissionDisplay.Filter token) {
		content.clear();
		content.add( new Label("Loading...") );
		adminService.getFamilies(new AsyncCallback<List<FamilyFull>>() {
			public void onFailure(final Throwable caught) {
				Window.alert("Failed to communicate with server, check network connections");
			}
			public void onSuccess(final List<FamilyFull> result) {
				content.clear();
				content.add(new SubmissionDisplay(result, token));
			}
		});
	}


}
