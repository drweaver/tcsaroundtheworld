package org.tcsaroundtheworld.submit.client;

import java.util.ArrayList;
import java.util.List;

import org.tcsaroundtheworld.common.shared.verify.EmailValidator;
import org.tcsaroundtheworld.common.shared.verify.MessageValidator;
import org.tcsaroundtheworld.common.shared.verify.NameValidator;
import org.tcsaroundtheworld.submit.client.verify.ReCaptchaFieldVerifier;
import org.tcsaroundtheworld.submit.client.verify.SubmissionVerifier;
import org.tcsaroundtheworld.submit.client.verify.TextFieldVerifier;
import org.tcsaroundtheworld.submit.shared.ContactSubmission;
import org.tcsaroundtheworld.submit.shared.ReCaptchaFields;
import org.tcsaroundtheworld.submit.shared.ReCaptchaHost;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus.Status;

import com.claudiushauptmann.gwt.recaptcha.client.RecaptchaWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ContactFormWidget extends Composite {

	private static ContactFormWidgetUiBinder uiBinder = GWT.create(ContactFormWidgetUiBinder.class);

	interface ContactFormWidgetUiBinder extends UiBinder<Widget, ContactFormWidget> {}

	SubmissionServiceAsync submissionService = GWT.create(SubmissionService.class);

	@UiField Label contact;
	@UiField TextBox name;
	@UiField TextBox email;
	@UiField TextArea message;
	@UiField SimplePanel recaptchaContainer;

	@UiField Label nameError;
	@UiField Label emailError;
	@UiField Label messageError;
	@UiField Label recaptchaError;
	@UiField Label sendErrors;
	@UiField Image loadingImage;
	@UiField Button send;
	@UiField Button cancel;

	final ReCaptchaKeyConstants reCaptchaKeyConstants = GWT.create(ReCaptchaKeyConstants.class);
	private final RecaptchaWidget recaptchaWidget = new RecaptchaWidget(reCaptchaKeyConstants.publicKeys().get(ReCaptchaHost.clean(Location.getHost())));

	private final SubmissionVerifier submissionVerifier = new SubmissionVerifier();

	private final Long familyId;
	private final Long personId;

	private final List<SuccessfulSubmissionHandler> successfulSubmissionHandlers = new ArrayList<ContactFormWidget.SuccessfulSubmissionHandler>();

	private CancelHandler cancelHandler = null;

	/**
	 * Constructor for use when the intended recipient is the administrator
	 */
	public ContactFormWidget() {
		this(null, null, "Administrator");
	}

	/**
	 * 
	 * Constructor for sending to a specific user
	 * 
	 * @param familyId
	 * @param personId
	 * @param contact
	 */
	public ContactFormWidget(final Long familyId, final Long personId, final String contact) {
		initWidget(uiBinder.createAndBindUi(this));

		this.familyId = familyId;
		this.personId = personId;
		this.contact.setText(contact);

		name.setMaxLength(NameValidator.MAX_LENGTH);
		email.setMaxLength(EmailValidator.MAX_LENGTH);
		message.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(final KeyPressEvent event) {
				final String msg = message.getText();
				if( msg.length() > MessageValidator.MAX_LENGTH ) {
					message.setText( msg.substring(0, MessageValidator.MAX_LENGTH) );
				}
			}
		});

		submissionVerifier.add(new TextFieldVerifier("name", true, new NameValidator(), nameError, name) );
		submissionVerifier.add(new TextFieldVerifier("email", true, new EmailValidator(), emailError, email) );
		//submissionVerifier.add(new TextAreaVerifier("message", true, new MessageValidator(), messageError, message) );

		if( GWT.isProdMode() ) {
			recaptchaContainer.add( recaptchaWidget );
			submissionVerifier.add(new ReCaptchaFieldVerifier(recaptchaError, recaptchaWidget));
		}

	}

	@UiHandler("send")
	public void validateAndSend( final ClickEvent event ) {
		sendErrors.setText("");
		if( isValid() ) {
			loading(true);
			submissionService.submitContactRequest( toContactSubmission(), getReCaptchaFields(), new AsyncCallback<SubmissionStatus>() {
				public void onFailure(final Throwable caught) {
					sendErrors.setText(Submission.SERVER_ERROR);
					loading(false);
				}
				public void onSuccess(final SubmissionStatus result) {
					if( result.status == Status.FAILURE ) {
						sendErrors.setText(result.message);
						loading(false);
					} else { // SUCCESS
						sendErrors.setText("");
						loading(false);
						fireSuccessfulSubmissionHandlers();
					}
				}
			});
		}
	}

	@UiHandler("cancel")
	public void cancel(final ClickEvent event ) {
		if( cancelHandler != null ) {
			cancelHandler.onCancel();
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

	private void loading( final boolean loading ) {
		send.setEnabled(!loading);
		loadingImage.setVisible(loading);
	}

	public boolean isValid() {
		return submissionVerifier.isValid();
	}

	private ContactSubmission toContactSubmission() {
		return new ContactSubmission( familyId, personId, name.getText(), email.getText(), message.getText() );
	}

	public static interface CancelHandler {
		void onCancel();
	}

	public static interface SuccessfulSubmissionHandler {
		void onSuccessfulSubmission();
	}

	public void setCancelHandler(final CancelHandler handler) {
		cancelHandler = handler;
	}

	public void addSuccessfulSubmissionHandler(final SuccessfulSubmissionHandler handler) {
		successfulSubmissionHandlers.add( handler );
	}

	private void fireSuccessfulSubmissionHandlers() {
		for( final SuccessfulSubmissionHandler handler : successfulSubmissionHandlers ) {
			handler.onSuccessfulSubmission();
		}
	}

}
