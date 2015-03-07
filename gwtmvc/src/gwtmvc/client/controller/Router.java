/**
 * File: Router.java
 * Date: Feb 2, 2014
 * Author: I070659
 */
package gwtmvc.client.controller;

import java.util.HashMap;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.SimplePanel;


/**
 * @author Oded Nissan
 *
 */
public class Router {
	
	private SimplePanel panel;
	private HashMap<String,Controller> routeMap = new HashMap<String,Controller>();
	public Router()
	{
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			  

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				  String historyToken = event.getValue();
			      GWT.log("history token :" + historyToken);
			      route(historyToken,null);
			}
			});	
	}
	
	public void addRoute(String uri, Controller controller)
	{
		routeMap.put(uri, controller);
	}
	
	public Controller getRoute(String uri)
	{
		return(routeMap.get(uri));
	}

	public void route(String uri, Object o)
	{
		String parts[] = uri.split("\\.");
		Controller ctrl = getRoute(parts[0]);
		if(parts.length > 1) {
			ctrl.dispatch(parts[1],o);
		} else {
			ctrl.execute(o);
			render(ctrl);
			History.newItem(uri);
		}
	}
	
	public void setPanel(SimplePanel panel)
	{
		this.panel = panel;
	}

	public SimplePanel getPanel() {
		return panel;
	}
	
	private void render(Controller ctrl)
	{
		this.panel.clear();
		this.panel.add(ctrl.getView());
	}
}
