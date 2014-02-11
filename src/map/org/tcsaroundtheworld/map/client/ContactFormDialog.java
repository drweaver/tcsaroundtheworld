package org.tcsaroundtheworld.map.client;

import org.tcsaroundtheworld.submit.client.ContactFormWidget;
import org.tcsaroundtheworld.submit.client.SuccessfulSubmissionDialog;
import org.tcsaroundtheworld.submit.client.ContactFormWidget.CancelHandler;
import org.tcsaroundtheworld.submit.client.ContactFormWidget.SuccessfulSubmissionHandler;

import com.google.gwt.user.client.ui.DialogBox;

public class ContactFormDialog extends DialogBox {

	ContactFormWidget contactForm;

	public ContactFormDialog(final Long familyId, final Long personId, final String contactName) {

		contactForm = new ContactFormWidget(familyId, personId, contactName);

		setText("Contact Form");
		setWidget(contactForm);

		setGlassEnabled(true);
		setAutoHideEnabled(false);
		setAnimationEnabled(true);
		contactForm.setCancelHandler(new CancelHandler() {
			public void onCancel() {
				ContactFormDialog.this.hide();
			}
		});

		contactForm.addSuccessfulSubmissionHandler(new SuccessfulSubmissionHandler() {
			public void onSuccessfulSubmission() {
				final SuccessfulSubmissionDialog success = new SuccessfulSubmissionDialog("Contact Request", "Thank you, your message has been successfully sent");
				success.addCloseLink();
				success.center();
				ContactFormDialog.this.hide();
			}
		});

		center();
	}

}
