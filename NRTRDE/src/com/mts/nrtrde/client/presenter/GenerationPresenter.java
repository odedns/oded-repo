/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.FileInfo;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class GenerationPresenter implements Presenter {

	
	@SuppressWarnings("unused")
	private EventBus eventBus;
	private Display display;
	
	
	public GenerationPresenter(EventBus eventBus,Display display)
	{
		this.eventBus = eventBus;
		this.display = display;
		bind();
	}
	
	
	
	public void bind()
	{
		this.display.getGenerateButton().addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				Date toDate = GenerationPresenter.this.display.getToDate();
				Date fromDate = GenerationPresenter.this.display.getFromDate();
				String operator = GenerationPresenter.this.display.getOperator();
				final MessageBox box = MessageBox.wait("Progress","Retrieving files Please Wait...", "Retrieving...");  
				NRTRDE.nrtrdeService.findGenerateFiles(fromDate,toDate,operator,new AsyncCallback<ArrayList<FileInfo>>() {
					
					@Override
					public void onSuccess(ArrayList<FileInfo> result) {
						// TODO Auto-generated method stub
						box.close();
						GenerationPresenter.this.display.getProxy().setData(result);
						GenerationPresenter.this.display.getLoader().load(0, NRTRDE.constants.pageSize());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						box.close();
						if(caught instanceof SessionException) {
							NRTRDE.showSessionDialog();
						} else {
							MessageBox.alert("Error calling Generate","Error: " + caught.getMessage(), null);
							GWT.log("Error:" + caught);
						}
					}
				});
			
				
			}
		});
		
		this.display.getCommercialRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				ComboBox<OperatorValueObject> operList =GenerationPresenter.this.display.getOperListBox();
				operList.reset();
				operList.getStore().removeAll();
				operList.getStore().add(NRTRDE.commercialList);
				GWT.log("commercial size=" + NRTRDE.commercialList.size());
				
			}
		});

		this.display.getNonCommercialRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				ComboBox<OperatorValueObject> operList =GenerationPresenter.this.display.getOperListBox();
				operList.reset();
				operList.getStore().removeAll();
				operList.getStore().add(NRTRDE.nonCommercialList);
				GWT.log("noncommercial size=" + NRTRDE.nonCommercialList.size());
				
			}
		});

	}
	
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		/*
		 * this needs to be done here because we need to verify that 
		 * the operList was already loader by the EntryPoint.
		 */
		
		container.clear();
		container.add(display.asWidget());
	}

	public interface Display {
		Radio getCommercialRadio();
		Radio getNonCommercialRadio();
		ComboBox<OperatorValueObject> getOperListBox();
		Button getGenerateButton();
		Date getFromDate();
		Date getToDate();
		String getOperator();		
		PagingModelMemoryProxy getProxy(); 
		PagingLoader<PagingLoadResult<ModelData>> getLoader();
		Widget asWidget();
	}
	
	
}
