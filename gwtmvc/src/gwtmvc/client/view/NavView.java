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
 * @author C5132784
 *
 */
public class NavView extends Composite {
	
	
	public NavView()
	{
		VerticalPanel vp = new VerticalPanel();
		vp.setBorderWidth(2);
		vp.setHeight("100%");
		Button view1 = new Button("view1");
		vp.add(view1);
		Button view2 = new Button("view2");
		vp.add(view2);
		Button view3 = new Button("view3");
		vp.add(view3);
		Button view4 = new Button("view4");
		vp.add(view4);
		initWidget(vp);
		
		
	}
	

}
