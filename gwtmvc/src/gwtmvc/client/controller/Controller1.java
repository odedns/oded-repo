/**
 * File: Controller1.java
 * Date: Feb 5, 2014
 * Author: I070659
 */
package gwtmvc.client.controller;

import gwtmvc.client.view.View;
import gwtmvc.client.view.View1;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Oded Nissan
 *
 */
public class Controller1 extends Controller {

	public Controller1(View view)
	{
		this.view = view;
	}
	@Override
	public void execute(Object o) {
		// TODO Auto-generated method stub
		
		GWT.log("in execute");
	}
	@Override
	public void dispatch(String route,Object o) {
		// TODO Auto-generated method stub
		if(route.equals("press")) {
			press();
		}
	}
	
	public void press()
	{
		View1 view = (View1) getView();
		view.getPanel().add(new Label("button pressed"));
		
	}

}
