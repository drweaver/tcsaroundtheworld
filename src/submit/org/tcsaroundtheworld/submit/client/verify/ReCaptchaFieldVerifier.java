package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.claudiushauptmann.gwt.recaptcha.client.RecaptchaWidget;
import com.google.gwt.user.client.ui.Label;

public class ReCaptchaFieldVerifier extends AbstractFieldVerifier {

	private static SubmissionValueVerifier reCaptchaValidator = new SubmissionValueVerifier() {
		public boolean isValid(final String text) {
			return text!=null && text.length()>0;
		}
	};

	private final RecaptchaWidget field;

	public ReCaptchaFieldVerifier(final Label errorLabel, final RecaptchaWidget field) {
		super("reCaptcha", true, reCaptchaValidator, errorLabel);
		this.field = field;
	}

	@Override
	public String getValue() {
		return field.getResponse();
	}

}