/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.AlertInfoContainer;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class AlertsPresenter implements Presenter {

	@SuppressWarnings("unused")
	private EventBus eventBus;	
	private Display display;
	
	
	public AlertsPresenter(EventBus eventBus,Display alertsView)
	{
		this.eventBus = eventBus;
		this.display = alertsView;
		bind();
	}
	
	
	
	public void bind()
	{
	
	}
	
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		final MessageBox box = MessageBox.wait("Progress","Retrieving Alerts Please Wait...", "Retrieving...");
		NRTRDE.nrtrdeService.listAlerts(new AsyncCallback<AlertInfoContainer>() {
			
			@Override
			public void onSuccess(AlertInfoContainer result) {
				// TODO Auto-generated method stub
				box.close();
				if(result != null)  {
					AlertsPresenter.this.display.getAlertLabel().setValue(NRTRDE.nrtrdeMessage.alertMsg(result.getCurrentVolumeThreshold(),result.getCurrentUsageThreshold(),result.getCurrentSMSThreshold()));
					AlertsPresenter.this.display.getProxy().setData(result.getAlerts());
					AlertsPresenter.this.display.getLoader().load(0, NRTRDE.constants.pageSize());
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				box.close();
				if(caught instanceof SessionException) {
					NRTRDE.showSessionDialog();
				} else {
					MessageBox.alert("Error retreiving alerts","Error: " + caught.getMessage(), null);
					GWT.log("Error:" + caught);
				}
			}
		});
		container.clear();
		container.add(display.asWidget());
	}

	public interface Display {
		PagingModelMemoryProxy getProxy(); 
		PagingLoader<PagingLoadResult<ModelData>> getLoader();
		Widget asWidget();
		LabelField getAlertLabel();
	}
	
	
	
	
}
