package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public class TextAreaVerifier extends LabelFieldVerifier {

	public TextAreaVerifier(final String itemName, final boolean mandatory, final SubmissionValueVerifier verifier, final Label errorLabel, final TextArea field) {
		super(itemName, mandatory, verifier, errorLabel, field);
		field.addChangeHandler(new ChangeHandler() {
			public void onChange(final ChangeEvent event) {
				checkField();
			}
		});
	}

}