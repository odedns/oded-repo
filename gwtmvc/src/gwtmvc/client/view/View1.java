/**
 * File: View1.java
 * Date: Feb 5, 2014
 * Author: I070659
 */
package gwtmvc.client.view;

import gwtmvc.client.Gwtmvc;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author I070659
 *
 */
public class View1 extends View {
	HTMLPanel panel;
	public View1(){
		 panel = new HTMLPanel("<p>This is view1");
		Button b = new Button("press");
		b.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Gwtmvc.router.route("/ctrl1.press", event);
			}
		});
		panel.add(b);
		initWidget(panel);
		
	}
	
	public HTMLPanel getPanel()
	{
		return(this.panel);
	}

}
