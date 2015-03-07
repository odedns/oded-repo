/**
 * 
 */
package com.mts.nrtrde.client.view;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.mts.nrtrde.client.presenter.NavigationPresenter;

/**
 * @author Oded Nissan
 *
 */
public class NavigationView extends LayoutContainer implements NavigationPresenter.Display {
	
	

	private Hyperlink navigation;
	
	public NavigationView()
	{
	
		
	}
	
	
	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);

		navigation = new Hyperlink("NRTRDE","NRTRDE");
		addStyleName("navigation-panel");
		add(navigation);
	
		setSize("100%", "100%");
		layout();
		
	}


	public HasHTML getNavigation() {
		// TODO Auto-generated method stub
		return navigation;
	}

}
