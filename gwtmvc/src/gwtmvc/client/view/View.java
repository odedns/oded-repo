/**
 * File: View.java
 * Date: Jan 30, 2014
 * Author: I070659
 */
package gwtmvc.client.view;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Oded Nissan
 *
 */
public abstract class View extends Composite implements IsWidget {

	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		return this;
	}
	


}
