/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Oded Nissan
 *
 */
public class NavigationPresenter implements Presenter {

	
	@SuppressWarnings("unused")
	private EventBus eventBus;
	private Display display;
	
	public NavigationPresenter(EventBus eventBus,Display display)
	{
		this.eventBus = eventBus;
		this.display = display;
		
		
	}
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 //NavigationView v = (NavigationView)display;
		 
		 container.add(display.asWidget());

	}

	public interface Display {
		
		HasHTML getNavigation();
		Widget asWidget();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}
