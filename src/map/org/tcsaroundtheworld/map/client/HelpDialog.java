package org.tcsaroundtheworld.map.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HelpDialog extends DialogBox {

	public HelpDialog() {
		setAutoHideEnabled(true);
		setAutoHideOnHistoryEventsEnabled(true);
		setGlassEnabled(true);
		setText("Helpful information about TCS Around The World");
		setWidth("500px");

		final VerticalPanel mainPanel = new VerticalPanel();
		setWidget(mainPanel);

		final MapHelpWidget helpWidget = new MapHelpWidget();
		mainPanel.add( helpWidget );
		mainPanel.add( new Button("Close", new ClickHandler() {
			public void onClick(final ClickEvent event) {
				HelpDialog.this.hide();
			}
		}));
		helpWidget.contactLink.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				HelpDialog.this.hide();
				new ContactFormDialog(null, null, "Administrator");
			}
		});
	}

}
