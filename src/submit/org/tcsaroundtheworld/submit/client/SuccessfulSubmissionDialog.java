package org.tcsaroundtheworld.submit.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SuccessfulSubmissionDialog extends DialogBox {

	final VerticalPanel subPanel = new VerticalPanel();

	public SuccessfulSubmissionDialog(final String title, final String message) {

		setGlassEnabled(true);
		setAnimationEnabled(true);
		setAutoHideEnabled(false);
		setAutoHideOnHistoryEventsEnabled(true);
		setText(title);
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		subPanel.setSpacing(5);
		setWidget(panel);
		panel.add( new Image("img/green_tick.gif") );
		panel.add( subPanel );
		subPanel.add( new Label(message) );

	}

	public SuccessfulSubmissionDialog addReturnToMapLink() {
		subPanel.add( new Anchor("Return to Map", "/") );
		return this;
	}

	public SuccessfulSubmissionDialog addCloseLink() {
		final Anchor w = new Anchor("Close");
		w.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				hide();
			}
		});
		subPanel.add( w );
		return this;
	}

	public void addLink( final Anchor link ) {
		subPanel.add( link );
	}

}
