/**
 * File: Controller1.java
 * Date: Feb 5, 2014
 * Author: I070659
 */
package gwtmvc.client.controller;

import gwtmvc.client.view.View;

import com.google.gwt.core.shared.GWT;

/**
 * @author I070659
 *
 */
public class Controller2 extends Controller {

	public Controller2(View view)
	{
		this.view = view;
	}
	@Override
	public void execute(Object o) {
		// TODO Auto-generated method stub
		
		GWT.log("in execute");
	}

}
