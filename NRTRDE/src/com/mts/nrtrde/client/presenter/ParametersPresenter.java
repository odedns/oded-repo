/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import java.util.HashMap;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.BasicValueObject;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class ParametersPresenter implements Presenter {

	
	@SuppressWarnings("unused")
	private EventBus eventBus;
	private Display display;
	BasicValueObject selectedPhone = null;

	
	
	public ParametersPresenter(EventBus eventBus,Display display)
	{
		this.eventBus = eventBus;
		this.display = display;
		bind();
				
	}
	
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		NRTRDE.nrtrdeService.getParameters(new AsyncCallback<HashMap<String,String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				if(caught instanceof SessionException) {
					NRTRDE.showSessionDialog();
				} else {
					MessageBox.alert("Error calling getParameters","Error: " + caught.getMessage(), null);
					GWT.log("Error:" + caught);
				}
			}

			@Override
			public void onSuccess(HashMap<String, String> result) {
				// TODO Auto-generated method stub
				if(result != null) {
					GWT.log("got Params = " + result.toString());
					ParametersPresenter.this.display.setParameters(result);
				}
			}
		});
		
		
		
		container.clear();
		container.add(display.asWidget());

	}

	/**
	 * bind all selection handlers.
	 */
	public void bind()
	{
		/*
		 * handler for submit button.
		 */
		this.display.getSubmitButton().addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
			
				HashMap<String,String> map = ParametersPresenter.this.display.getParameters();
				GWT.log("params: " + map.toString());
				final MessageBox box = MessageBox.wait("Saving", "Saving parameter, Please wait....", "Saving...");
				NRTRDE.nrtrdeService.updateParameters(map, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						box.close();
						MessageBox.alert("Success", "Parameters updated succesfully", null);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						box.close();
						if(caught instanceof SessionException) {
							NRTRDE.showSessionDialog();
						} else {
							MessageBox.alert("Error calling updateParams","Error: " + caught.getMessage(), null);
							GWT.log("Error:" + caught);
						}
					}
				});
				
			}
		});
	} /* bind */
	
	public interface Display {
	
		Button getSubmitButton();
		HashMap<String,String> getParameters();
		void setParameters(HashMap<String,String> map);
		Widget asWidget();
	}

}
