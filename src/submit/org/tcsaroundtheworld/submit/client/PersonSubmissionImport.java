package org.tcsaroundtheworld.submit.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class PersonSubmissionImport extends Composite {

	private static PersonSubmissionImportUiBinder uiBinder = GWT.create(PersonSubmissionImportUiBinder.class);

	interface PersonSubmissionImportUiBinder extends UiBinder<Widget, PersonSubmissionImport> {
	}

	@UiField TextArea content;
	@UiField Button importButton;
	@UiField Button cancelButton;
	@UiField RadioButton emailChoice;
	@UiField RadioButton jsonChoice;


	public PersonSubmissionImport() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
