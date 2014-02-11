package org.tcsaroundtheworld.admin.client;

import java.util.ArrayList;
import java.util.List;

import org.tcsaroundtheworld.admin.shared.FamilyFull;
import org.tcsaroundtheworld.admin.shared.PersonFull;
import org.tcsaroundtheworld.common.shared.PersonCsvOutputVisitor;
import org.tcsaroundtheworld.common.shared.PersonJsonOutputVisitor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubmissionDisplay extends Composite {

	private static SubmissionDisplayUiBinder uiBinder = GWT.create(SubmissionDisplayUiBinder.class);

	interface SubmissionDisplayUiBinder extends	UiBinder<Widget, SubmissionDisplay> {}

	private final DialogBox exportDialog = new DialogBox();
	private final List<PersonFull> personsToExport = new ArrayList<PersonFull>();

	public enum Filter { NONE, AWAITING_APPROVAL };

	@UiField VerticalPanel content;

	public SubmissionDisplay(final List<FamilyFull> submissions, final Filter filter) {
		initWidget(uiBinder.createAndBindUi(this));
		for( final FamilyFull f : submissions ) {
			if( filter == Filter.NONE || !f.isApproved() ) {
				content.add( new FamilyWidget(f) );
				personsToExport.addAll(f.getMembersFull());
			}
		}
		exportDialog.setGlassEnabled(true);
		exportDialog.setAutoHideEnabled(true);
	}

	private void openExportDialog(final String s) {
		final TextArea ta = new TextArea();
		ta.setHeight("500px");
		ta.setWidth("600px");
		ta.setText(s);
		exportDialog.setText("Exported Data");
		exportDialog.clear();
		exportDialog.add( ta );
		exportDialog.center();
	}

	@UiHandler("exportCsv")
	void exportPersonsAsCsv(final ClickEvent event) {
		final PersonCsvOutputVisitor csvOutputVisitor = new PersonCsvOutputVisitor();
		final StringBuilder sb = new StringBuilder();
		sb.append( csvOutputVisitor.csvHeader()).append("\n");
		for( final PersonFull p : personsToExport ) {
			p.accept(csvOutputVisitor);
			sb.append(csvOutputVisitor.toCsv()).append("\n");
		}
		openExportDialog(sb.toString());
	}

	@UiHandler("exportJson")
	void exportPersonsAsJson(final ClickEvent event) {
		final PersonJsonOutputVisitor jsonVisitor = new PersonJsonOutputVisitor();
		final StringBuilder sb = new StringBuilder();
		sb.append("{ \"all_people\": [ \n");
		boolean first = true;
		for( final PersonFull p : personsToExport ) {
			if( !first ) {
				sb.append(",\n");
			}
			first = false;
			p.accept(jsonVisitor);
			sb.append(jsonVisitor.toJson());
		}
		sb.append("] }");

		openExportDialog(sb.toString());
	}

}
