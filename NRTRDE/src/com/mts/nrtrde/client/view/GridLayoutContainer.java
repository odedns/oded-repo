/**
 * 
 */
package com.mts.nrtrde.client.view;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

/**
 * @author Oded Nissan
 *
 */
public class GridLayoutContainer extends LayoutContainer {
	
	 public static final int TABLE_HEIGHT_OFFSET = 120;
	 public static final int TABLE_WIDTH_OFFSET = 200;
	protected ContentPanel cp;  
	
	@Override
	protected void onWindowResize(int width, int height) {
		// TODO Auto-generated method stub
		super.onWindowResize(width, height);
		int w = width - TABLE_WIDTH_OFFSET;
		int h = height - TABLE_HEIGHT_OFFSET;
		GWT.log("content width= " + w + " height = " + h);
		cp.setSize(w, h);
		
	}
  @Override
  protected void onRender(Element parent, int index) {
	// TODO Auto-generated method stub
	  super.onRender(parent, index);
	  this.setMonitorWindowResize(true);
	  int h = Window.getClientHeight();
	  int w = Window.getClientWidth();
	  GWT.log("initial h= " + h + " w =" + w);
  }
}
