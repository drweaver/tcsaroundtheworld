package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class TextFieldVerifier extends LabelFieldVerifier {

	public TextFieldVerifier(final String itemName, final boolean mandatory, final SubmissionValueVerifier verifier, final Label errorLabel, final TextBox field) {
		super(itemName, mandatory, verifier, errorLabel, field);
		field.addChangeHandler(new ChangeHandler() {
			public void onChange(final ChangeEvent event) {
				checkField();
			}
		});
	}

}