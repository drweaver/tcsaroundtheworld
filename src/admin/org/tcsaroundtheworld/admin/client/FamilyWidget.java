package org.tcsaroundtheworld.admin.client;

import org.tcsaroundtheworld.admin.shared.FamilyFull;
import org.tcsaroundtheworld.admin.shared.PersonFull;
import org.tcsaroundtheworld.common.client.DateParserClient;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FamilyWidget extends Composite {

	AdminServiceAsync adminService = GWT.create(AdminService.class);

	private static FamilyWidgetUiBinder uiBinder = GWT.create(FamilyWidgetUiBinder.class);

	interface FamilyWidgetUiBinder extends UiBinder<Widget, FamilyWidget> {
	}

	public FamilyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Label id;
	@UiField
	Image loading;
	@UiField
	Button approve;
	@UiField
	Label dateSubmitted;
	@UiField
	VerticalPanel members;
	@UiField
	Button delete;

	private FamilyFull f;

	public FamilyWidget(final FamilyFull f) {
		initWidget(uiBinder.createAndBindUi(this));
		this.f = f;
		id.setText(Long.toString(f.getId()));
		if( f.isApproved() ) {
			approve.setVisible(false);
		}
		for( final PersonFull p : f.getMembersFull() ) {
			final PersonWidget w = new PersonWidget();
			w.accept( p.createCopyVisitor() );
			members.add(w);
		}
		if( f.getDateSubmitted() != null ) {
			dateSubmitted.setText("Submitted on "+ new DateParserClient(f.getDateSubmitted()).toString());
		}
	}

	@UiHandler("approve")
	void onApprove(final ClickEvent event) {
		if( f.isNewSubmission() || Window.confirm("User defined this as a replacement/updated submission, you may need to delete an earlier entry or confirm that this is indeed a valid update.  Go ahead and approve anyway?") ) {
			loading.setVisible(true);
			approve.setEnabled(false);
			adminService.approveFamily(f.getId(), new AsyncCallback<Void>() {
				public void onFailure(final Throwable caught) {
					Window.alert("Failed to communicate with server, check network connection");
					loading.setVisible(false);
					approve.setEnabled(true);
				}

				public void onSuccess(final Void result) {
					loading.setVisible(false);
					approve.setVisible(false);
				}
			});
		}
	}

	@UiHandler("delete")
	void deleteFamily(final ClickEvent event) {
		if( f.getMembersFull().size() == 0 ||  Window.confirm("Are you sure you wish to delete this family with members: "+memberNames())) {
			loading.setVisible(true);
			adminService.deleteFamily(f.getId(), new AsyncCallback<Void>() {
				public void onFailure(final Throwable caught) {
					Window.alert("Failed to communicate with server, check network connection");
					loading.setVisible(false);
				}
				public void onSuccess(final Void result) {
					setVisible(false);
				};
			});
		}
	}

	private String memberNames() {
		final StringBuilder sb = new StringBuilder();
		boolean first = true;
		for( final PersonFull p : f.getMembersFull() ) {
			if( !first ) {
				sb.append(", ");
			}
			first = false;
			sb.append(p.getBase().getName()).append(" ").append(p.getBase().getSurname());
		}
		return sb.toString();
	}

}
