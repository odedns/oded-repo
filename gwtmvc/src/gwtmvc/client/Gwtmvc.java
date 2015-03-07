package gwtmvc.client;


import gwtmvc.client.controller.Controller1;
import gwtmvc.client.controller.Controller2;
import gwtmvc.client.controller.Controller3;
import gwtmvc.client.controller.Router;
import gwtmvc.client.view.FooterView;
import gwtmvc.client.view.HeaderView;
import gwtmvc.client.view.MainView;
import gwtmvc.client.view.NavView;
import gwtmvc.client.view.View1;
import gwtmvc.client.view.View2;
import gwtmvc.client.view.View3;
import gwtmvc.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtmvc implements EntryPoint {
	 public static Router router = new Router();
	 public static final AppResources images = GWT.create(
		      AppResources.class);

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		router.addRoute("/ctrl1",new Controller1(new View1()));
		router.addRoute("/ctrl2",new Controller2(new View2()));
		router.addRoute("/ctrl3",new Controller3(new View3()));

		
		MainView view = new MainView();
		router.setPanel(view.getCenterPanel());
		
		RootLayoutPanel.get().add(view);	
		
	}
}
