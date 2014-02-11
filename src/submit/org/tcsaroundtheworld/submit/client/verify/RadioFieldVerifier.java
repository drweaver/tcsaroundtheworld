package org.tcsaroundtheworld.submit.client.verify;

import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;

public class RadioFieldVerifier extends AbstractFieldVerifier {

	private final RadioButton[] buttons;

	public RadioFieldVerifier(final String itemName, final boolean mandatory,
			final SubmissionValueVerifier verifier, final Label errorLabel, final RadioButton...buttons) {
		super(itemName, mandatory, verifier, errorLabel);
		this.buttons = buttons;

		for( final RadioButton button : buttons ) {
			button.addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					checkField();
				}
			});
		}
	}

	@Override
	public String getValue() {
		String value = "";
		for( final RadioButton button : buttons ) {
			if( button.getValue() ) {
				value = button.getText();
			}
		}
		return value;
	}
}