/**
 * 
 */
package gwtmvc.client.view;


import gwtmvc.client.Gwtmvc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Oded Nissan
 *
 */
public class HeaderView extends Composite {
	DockPanel panel;
	HorizontalPanel searchPanel;
	Label label;
	TextBox searchBox;
	Button searchButton;
	HorizontalPanel titlePanel;
	
	public HeaderView()
	{
		panel = new DockPanel();
		
		
		titlePanel = new HorizontalPanel();
		titlePanel.add(new Image(Gwtmvc.images.google()));
		titlePanel.add(new HTML("<h1>This is my header </h1>"));
		titlePanel.setBorderWidth(2);
		titlePanel.setWidth("100%");
		initWidget(titlePanel);
		
		
		
		
	}
	

}
