/**
 * 
 */
package gwtmvc.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author C5132784
 *
 */
public class FooterView extends Composite {
	VerticalPanel panel;
	
	
	public FooterView()
	{
		panel = new VerticalPanel();
		panel.setBorderWidth(2);
		panel.add(new HTML("<h4>This is the Footer </h4>"));
		panel.setWidth("100%");
		initWidget(panel);
		
		
	}
	

}
