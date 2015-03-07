package com.mts.nrtrde.client.view;

/**
 * 
 */



import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.presenter.WelcomePresenter;

/**
 * @author Oded Nissan
 *
 */
public class WelcomeView extends LayoutContainer implements WelcomePresenter.Display {

	
	
	public WelcomeView()
	{
	
	}

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		//add(new Image(NRTRDE.images.NRTRDE()));
		Viewport port = new Viewport();
		
		port.addStyleName("welcome-panel");
		add(port);
		setLayout(new FitLayout());
		layout();
	}
	
	
	
}
