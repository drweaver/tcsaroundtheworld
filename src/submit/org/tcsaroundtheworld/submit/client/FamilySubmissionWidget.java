package org.tcsaroundtheworld.submit.client;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.PersonSubmission;
import org.tcsaroundtheworld.submit.shared.ReCaptchaFields;
import org.tcsaroundtheworld.submit.shared.ReCaptchaHost;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus.Status;

import com.claudiushauptmann.gwt.recaptcha.client.RecaptchaWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FamilySubmissionWidget extends Composite {

	SubmissionServiceAsync submissionService = GWT.create(SubmissionService.class);

	private static FamilySubmissionWidgetUiBinder uiBinder = GWT.create(FamilySubmissionWidgetUiBinder.class);

	interface FamilySubmissionWidgetUiBinder extends UiBinder<Widget, FamilySubmissionWidget> {}

	private final SubmissionHelp submissionHelp = GWT.create(SubmissionHelp.class);

	@UiField HTML introduction;

	@UiField VerticalPanel personPanel;

	@UiField Label submitErrors;
	@UiField Image loadingImage;
	@UiField Label recaptchaError;

	@UiField Button addPerson;
	@UiField Button removePerson;
	@UiField Button submit;

	@UiField CheckBox updatedSubmission;

	@UiField SimplePanel recaptchaContainer;

	private  RecaptchaWidget recaptchaWidget;

	public FamilySubmissionWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		introduction.setHTML(submissionHelp.introduction());

		if( GWT.isProdMode() ) {
			final ReCaptchaKeyConstants reCaptchaKeyConstants = GWT.create(ReCaptchaKeyConstants.class);
			recaptchaWidget = new RecaptchaWidget(reCaptchaKeyConstants.publicKeys().get(ReCaptchaHost.clean(Location.getHost())), "en", "red");
			recaptchaContainer.add( recaptchaWidget );
		}

		addPerson(null);

		removePerson.setEnabled(false);

	}


	@UiHandler("submit")
	protected void validateAndSubmit(final ClickEvent event) {
		loading( true );
		submitErrors.setText("");
		boolean valid = true;
		for( final Widget w : personPanel ) {
			final PersonSubmissionWidget personWidget = (PersonSubmissionWidget) w;
			if( !personWidget.isValid() ) {
				valid = false;
			}
		}

		if( valid ) {
			submit();
		} else {
			submitErrors.setText("There were errors with your form, please review and re-submit.");
			loading( false );
		}
	}

	private ReCaptchaFields getReCaptchaFields() {
		final ReCaptchaFields fields = new ReCaptchaFields();
		if( GWT.isProdMode() ) {
			fields.setChallenge(recaptchaWidget.getChallenge());
			fields.setResponse(recaptchaWidget.getResponse());
		}
		return fields;
	}

	private void submit() {
		submitErrors.setText("Your form is being submitted...  Please wait.");
		final FamilySubmission nf = new FamilySubmission();
		nf.setNewSubmission(!updatedSubmission.getValue());
		nf.setDateSubmitted(new Date());
		final List<PersonSubmission> members = new ArrayList<PersonSubmission>();
		for( final Widget w : personPanel ) {
			final PersonSubmissionWidget npw = (PersonSubmissionWidget)w;
			final PersonSubmission ps = new PersonSubmission();
			ps.accept( npw.createCopyVisitor() );
			members.add( ps );
		}
		nf.setMembers(members);

		submissionService.submitNewFamily( nf, getReCaptchaFields(), new AsyncCallback<SubmissionStatus>() {
			public void onFailure(final Throwable caught) {
				submitErrors.setText(Submission.SERVER_ERROR);
				loading(false);
				trackSubmissionAttempt("Failed", "Undefined server error");
			}
			public void onSuccess(final SubmissionStatus result) {
				if( result.status == Status.FAILURE ) {
					submitErrors.setText(result.message);
					loading(false);
					trackSubmissionAttempt("Failed", result.message);
				} else { // SUCCESS
					successfullSubmissionDialog();
					submitErrors.setText("");
					loading(false);
					trackSubmissionAttempt("Successful", "");
				}
			}
		});
	}

	protected native void trackSubmissionAttempt( String status, String msg ) /*-{
		$wnd._gaq.push(['_trackEvent', 'Submission Attempt', status, 'Message', msg]);
	}-*/;

	private void loading( final boolean loading ) {
		submit.setEnabled(!loading);
		loadingImage.setVisible(loading);
	}

	@UiHandler("addPerson")
	void addPerson(final ClickEvent event) {
		final PersonSubmissionWidget newPersonWidget = new PersonSubmissionWidget( personPanel.getWidgetCount() );
		if( personPanel.getWidgetCount() > 0 ) {
			final PersonSubmissionWidget existingPerson = (PersonSubmissionWidget) personPanel.getWidget( personPanel.getWidgetCount()-1 );
			newPersonWidget.copyCommonInformation(existingPerson);
		}
		personPanel.add( newPersonWidget );
		if(personPanel.getWidgetCount() == 5) {
			addPerson.setEnabled(false);
		}
		removePerson.setEnabled( true );
	}

	@UiHandler("removePerson")
	void removeLastPerson(final ClickEvent event) {
		if( personPanel.getWidgetCount() > 1 ) {
			personPanel.remove( personPanel.getWidgetCount()-1 );
		}
		if( personPanel.getWidgetCount() == 1 ) {
			removePerson.setEnabled(false);
		}
		addPerson.setEnabled(true);
	}

	private void successfullSubmissionDialog() {
		final SuccessfulSubmissionDialog dialogBox = new SuccessfulSubmissionDialog("Submission Successful", "Thank you for your submission.  Your entries will be approved soon");
		dialogBox.addReturnToMapLink();
		if( Submission.extendedMode() ) {
			dialogBox.addLink( createSubmitAnotherLink() );
			dialogBox.addLink( new Anchor("Approvals page", "administrator.html#awaitingapproval") );
		}
		dialogBox.center();
	}

	private Anchor createSubmitAnotherLink() {
		final Anchor link = new Anchor("Submit Another");
		link.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				Window.Location.reload();
			}
		});
		return link;
	}
}
