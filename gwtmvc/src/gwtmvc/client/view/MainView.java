/**
 * File: MainView.java
 * Date: Aug 25, 2013
 * Author: I070659
 */
package gwtmvc.client.view;

import gwtmvc.client.Gwtmvc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author Oded Nissan
 *
 */
public class MainView extends Composite implements IsWidget {
	
	private static MainViewImplUiBinder uiBinder = GWT.create(MainViewImplUiBinder.class);
	
	
	
	interface MainViewImplUiBinder extends UiBinder<Widget, MainView>
	{
	}
	
	@UiField FlowPanel westPanel;
	@UiField SimplePanel centerPanel;
	@UiField Button but1;
	@UiField Button but2;
	@UiField Button but3;
	
	public MainView()
	{
		
		initWidget(uiBinder.createAndBindUi(this));
		// Create the store that the contains the data to display in the tree
	
	}
	
	public SimplePanel getCenterPanel()
	{
		return(this.centerPanel);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		return this;
	}

	@UiHandler("but1")
	 public void onButton1Click(ClickEvent event) {
			Window.alert("but1 pressed");
			Gwtmvc.router.route("/ctrl1",event );
		 
     }
	
	@UiHandler("but2")
	 public void onButton2Click(ClickEvent event) {
			Window.alert("but2 pressed");
			Gwtmvc.router.route("/ctrl2",event );
		 
    }
	
	@UiHandler("but3")
	 public void onButton3Click(ClickEvent event) {
			Window.alert("but3 pressed");
			Gwtmvc.router.route("/ctrl3",event );

		 
   }
}
