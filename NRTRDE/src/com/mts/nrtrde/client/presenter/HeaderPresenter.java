/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.NRTRDE;


/**
 * @author Oded Nissan
 *
 */
public class HeaderPresenter implements Presenter {

	private final Display display;
	@SuppressWarnings("unused")
	private final EventBus eventBus;
	
	
	public HeaderPresenter(EventBus eventBus,Display display)
	{
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}
	
	
	@Override
	public void bind()
	{
		ClickHandler handler = new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				NRTRDE.nrtrdeService.logout(new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						MessageBox.info("Logout", "You have succesfully logged out", null);
				
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						MessageBox.alert("Error","Error in logout: " + caught.getMessage(),null);
						GWT.log("Error:" + caught.getMessage());
					}
				});
			}
		};
	
		this.display.getLogoutLink().addClickHandler(handler);
	}
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

	}
	
	
	public interface Display {
		HasClickHandlers getLogoutLink();
		Widget asWidget();
		
		
	}

}
