package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.google.gwt.user.client.ui.Label;

public abstract class AbstractFieldVerifier implements FieldVerifier {
	public static final String FIELD_IS_MANDATORY = "This field is mandatory";
	private final String itemName;
	private final boolean mandatory;
	private final SubmissionValueVerifier verifier;
	protected final Label errorLabel;

	public AbstractFieldVerifier(final String itemName, final boolean mandatory, final SubmissionValueVerifier verifier, final Label errorLabel) {
		this.itemName = itemName;
		this.mandatory = mandatory;
		this.verifier = verifier;
		this.errorLabel = errorLabel;
	}

	protected String errorMessage() {
		return "Please specify a valid " + itemName;
	}

	private boolean isEmptyText(final String text) {
		return text.trim().length() == 0;
	}

	public boolean checkField() {
		final String trimmedValue = getValue().trim();
		if( mandatory && isEmptyText(trimmedValue) ) {
			errorLabel.setText(FIELD_IS_MANDATORY);
			return false;
		}
		if( !isEmptyText(trimmedValue) && !verifier.isValid(trimmedValue) ) {
			errorLabel.setText(errorMessage());
			return false;
		}
		errorLabel.setText("");
		return true;
	}

	protected abstract String getValue();
}