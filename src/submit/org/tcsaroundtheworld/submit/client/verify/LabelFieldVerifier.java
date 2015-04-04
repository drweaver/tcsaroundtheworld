package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;

public class LabelFieldVerifier extends AbstractFieldVerifier {

	private final HasText field;

	public LabelFieldVerifier(final String itemName, final boolean mandatory, final SubmissionValueVerifier verifier, final Label errorLabel, final HasText field) {
		super(itemName, mandatory, verifier, errorLabel);
		this.field = field;
	}

	@Override
	public String getValue() {
		return field.getText();
	}
}