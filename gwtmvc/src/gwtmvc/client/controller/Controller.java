/**
 * File: BasicController.java
 * Date: Jan 29, 2014
 * Author: I070659
 */
package gwtmvc.client.controller;

import org.apache.commons.lang.NotImplementedException;

import gwtmvc.client.view.View;

/**
 * @author Oded Nissan
 *
 */
public abstract class Controller  {
	protected View view;
	
	/**
	 * This method is called when the Controller is invoked.
	 * @param o Object containing data passed.
	 */
	public abstract void execute(Object o);
	

	/**
	 * dispatch method.
	 * @param route the route to dispatch
	 * @param o Object containing data passed.
	 * This method needs to be implemented by subclasses in case 
	 * there is a need to add methods to the same controller.
	 */
	public void dispatch(String route,Object o) {
		throw new NotImplementedException();
	}
	
	/**
	 * get this Controller's view
	 * @return View the view associated with the controller.
	 */
	public View getView() {
		return view;
	}

	/**
	 * set a view on the current Controller.
	 * @param view the View object to set.
	 */
	public void setView(View view) {
		this.view = view;
	}

}
