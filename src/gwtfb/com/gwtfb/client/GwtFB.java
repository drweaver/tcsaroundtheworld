package com.gwtfb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtfb.client.examples.Example;
import com.gwtfb.client.examples.FriendsExample;
import com.gwtfb.client.examples.StreamPublishExample;
import com.gwtfb.sdk.FBCore;
import com.gwtfb.sdk.FBEvent;
import com.gwtfb.sdk.FBXfbml;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author ola
 * 
 */
public class GwtFB implements EntryPoint, ValueChangeHandler<String>  {


	public String APPID = "d8724776da77150465c351df97a80d95";

	// prod
	//public String APPID = "0d51db8fd8b95ef0c2337ccbdc00d736";

	private final DockPanel mainPanel = new DockPanel ();
	private final SimplePanel mainView = new SimplePanel ();
	private final SimplePanel sideBarView = new SimplePanel ();

	private final FBCore fbCore = GWT.create(FBCore.class);
	private final FBEvent fbEvent = GWT.create(FBEvent.class);
	private final FBXfbml fbXfbml = GWT.create(FBXfbml.class);

	private final boolean status = true;
	private final boolean xfbml = true;
	private final boolean cookie = true;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		History.addValueChangeHandler ( this );

		fbCore.init(APPID, status, cookie, xfbml);

		RootPanel root = RootPanel.get();
		root.getElement().setId ( "TheApp" );
		mainView.getElement().setId("MainView");
		sideBarView.getElement().setId("SideBarView");
		mainPanel.add( new TopMenuPanel () , DockPanel.NORTH );
		mainPanel.add ( new TopMenuLinksPanel (), DockPanel.NORTH );
		mainPanel.add( sideBarView, DockPanel.WEST );
		mainPanel.add( mainView, DockPanel.CENTER );
		root.add ( mainPanel );


		//
		// Callback used when session status is changed
		//
		class SessionChangeCallback extends Callback<JavaScriptObject> {
			@Override
			public void onSuccess ( JavaScriptObject response ) {
				// Make sure cookie is set so we can use the non async method
				renderHomeView ();
			}
		}

		//
		// Get notified when user session is changed
		//
		SessionChangeCallback sessionChangeCallback = new SessionChangeCallback ();
		fbEvent.subscribe("auth.sessionChange",sessionChangeCallback);

		// Callback used when checking login status
		class LoginStatusCallback extends Callback<JavaScriptObject> {
			@Override
			public void onSuccess ( JavaScriptObject response ) {
				LoginStatusJso status = response.cast();
				GWT.log( "LoginStatus response: " + status.getStatus() );
				renderApp( Window.Location.getHash() );
			}
		}
		LoginStatusCallback loginStatusCallback = new LoginStatusCallback ();

		// Get login status
		fbCore.getLoginStatus( loginStatusCallback );

		class UserCallback extends Callback<JavaScriptObject> {
			@Override
			public void onSuccess(JavaScriptObject result) {
				UserJso user = result.cast();
				GWT.log( "FB ID=" + user.getId() );
				GWT.log( "FB fname="+ user.getName() );
			}
		}

		fbCore.api("/me", new UserCallback());
	}

	/**
	 * Render GUI
	 */
	private void renderApp ( String token ) {

		token = token.replace("#", "");

		if ( token == null || "".equals ( token ) || "#".equals ( token ) )
		{
			token = "home";
		}

		if ( token.endsWith("home") ) {
			renderHomeView ();
		} else if ( token.endsWith ( "wave" ) ) {
			renderWaveView ();
		} else if ( token.startsWith("example" ) ) {

			/*
			 * Wrap example, display sourcecode link etc.
			 */
			String example = token.split("/")[1];

			Example e = null;
			if ( "stream.publish".equals ( example ) ) {
				e = new StreamPublishExample ( fbCore );
			} else if ( "friends".equals ( example ) ) {
				e = new FriendsExample ( fbCore );
			}

			VerticalPanel examplePanel = new VerticalPanel ();
			examplePanel.setWidth ( "700px" );
			examplePanel.getElement().setId("ExampleView");

			HorizontalPanel headerPanel = new HorizontalPanel ();
			headerPanel.addStyleName( "header" );
			headerPanel.add ( new HTML ( "Method: " + e.getMethod() ) );

			Anchor sourceLink = new Anchor ( "Source" );
			sourceLink.addStyleName("sourceLink");
			sourceLink.setTarget( "blank");
			sourceLink.setHref("http://code.google.com/p/gwtfb/source/browse/trunk/GwtFB/src/com/gwtfb/client/examples/" + e.getSimpleName() + ".java" );
			headerPanel.add ( sourceLink );
			examplePanel.add( headerPanel );

			examplePanel.addStyleName ( "example" );
			e.addStyleName( "example" );
			examplePanel.add ( e );
			// Add example
			mainView.setWidget( examplePanel );

		} else {
			Window.alert ( "Unknown  url "  + token );
		}
	}

	/**
	 * Render GUI when logged in
	 */
	private void renderWhenLoggedIn () {
		mainView.setWidget ( new UserInfoViewController ( fbCore ) );
		fbXfbml.parse();
	}

	/**
	 * Render GUI when not logged in
	 */
	private void renderWhenNotLoggedIn () {
		mainView.setWidget ( new FrontpageViewController () );
		fbXfbml.parse();
	}

	/**
	 * Render home view. If user is logged in display welcome message, otherwise
	 * display login dialog.
	 */
	private void renderHomeView () {
		sideBarView.clear();

		if ( fbCore.getSession() == null ) {
			renderWhenNotLoggedIn ();
		} else {
			sideBarView.setWidget( new HomeSideBarPanel () );
			renderWhenLoggedIn();
		}
	}
	/**
	 * Render Wave
	 */
	private void renderWaveView () {
		WaveView waveView = new WaveView ();
		sideBarView.setWidget(new DocSideBarPanel () );
		mainView.setWidget( waveView );
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		renderApp ( event.getValue() );
	}

}