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
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.FileDeliveryDetails;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class FileDeliveryReportPresenter implements Presenter {

	
	@SuppressWarnings("unused")
	private EventBus eventBus;
	private Display display;
	
	public FileDeliveryReportPresenter(EventBus eventBus,Display display)
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
		 container.clear();
		 container.add(display.asWidget());

	}

	public interface Display {
		Button getSearchButton();
		Button getResetButton();
		Widget asWidget();
		Radio getNRFileRadio();
		Radio getFDRFileRadio();
		Radio getDateRadio();
		Radio getSeqRadio();		
		ComboBox<OperatorValueObject> getOperList();
		String getOperator();		
		TextField<String> getNRFileField();
		TextField<String> getFDRFileField();
		DateField getFromDateField();
		DateField getToDateField();
		TextField<String> getSeqField();
		PagingModelMemoryProxy getProxy(); 
		PagingLoader<PagingLoadResult<ModelData>> getLoader();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
			
		/*
		 * bind the radio buttons to disable fields
		 * that are not selected.
		 */
		this.display.getNRFileRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				FileDeliveryReportPresenter.this.display.getNRFileField().enable();
				FileDeliveryReportPresenter.this.display.getFDRFileField().disable();
				FileDeliveryReportPresenter.this.display.getSeqField().disable();
				FileDeliveryReportPresenter.this.display.getFromDateField().disable();
				FileDeliveryReportPresenter.this.display.getToDateField().disable();
			}
		});
		
		this.display.getFDRFileRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				FileDeliveryReportPresenter.this.display.getNRFileField().disable();
				FileDeliveryReportPresenter.this.display.getFDRFileField().enable();
				FileDeliveryReportPresenter.this.display.getSeqField().disable();
				FileDeliveryReportPresenter.this.display.getFromDateField().disable();
				FileDeliveryReportPresenter.this.display.getToDateField().disable();
			}
		});
		
				
		this.display.getDateRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				FileDeliveryReportPresenter.this.display.getNRFileField().disable();
				FileDeliveryReportPresenter.this.display.getFDRFileField().disable();
				
				FileDeliveryReportPresenter.this.display.getSeqField().disable();
				FileDeliveryReportPresenter.this.display.getFromDateField().enable();
				FileDeliveryReportPresenter.this.display.getToDateField().enable();
			}
		});
		
		this.display.getSeqRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				FileDeliveryReportPresenter.this.display.getNRFileField().disable();
				FileDeliveryReportPresenter.this.display.getFDRFileField().disable();
				
				FileDeliveryReportPresenter.this.display.getSeqField().enable();
				FileDeliveryReportPresenter.this.display.getFromDateField().disable();
				FileDeliveryReportPresenter.this.display.getToDateField().disable();
			}
		});
		
		this.display.getResetButton().addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				FileDeliveryReportPresenter.this.display.getFDRFileField().reset();
				FileDeliveryReportPresenter.this.display.getNRFileField().reset();
				FileDeliveryReportPresenter.this.display.getFromDateField().reset();
				FileDeliveryReportPresenter.this.display.getToDateField().reset();
				FileDeliveryReportPresenter.this.display.getSeqField().reset();
				FileDeliveryReportPresenter.this.display.getOperList().reset();
			}
		});
		this.display.getSearchButton().addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				String NRFile = null;
				String FDRFile = null;
				Date fromDate = null;
				Date toDate = null;
				String sequence = null;
				
				final MessageBox box = MessageBox.wait("Progress","Retrieving Alerts Please Wait...", "Retrieving...");
				String operator = FileDeliveryReportPresenter.this.display.getOperator();
				if(FileDeliveryReportPresenter.this.display.getNRFileField().isEnabled()){
					NRFile = FileDeliveryReportPresenter.this.display.getNRFileField().getValue();
				}
				if(FileDeliveryReportPresenter.this.display.getFDRFileField().isEnabled()){
					FDRFile = FileDeliveryReportPresenter.this.display.getFDRFileField().getValue();
				}
				if(FileDeliveryReportPresenter.this.display.getFromDateField().isEnabled()) {
					fromDate = FileDeliveryReportPresenter.this.display.getFromDateField().getValue();
					toDate = FileDeliveryReportPresenter.this.display.getToDateField().getValue();
				}
				if(FileDeliveryReportPresenter.this.display.getSeqField().isEnabled()) {
					sequence = FileDeliveryReportPresenter.this.display.getSeqField().getValue();
				}
				NRTRDE.nrtrdeService.executeFileDeliveryReport(operator, NRFile, FDRFile, fromDate, toDate, sequence, new AsyncCallback<ArrayList<FileDeliveryDetails>>() {
					
					@Override
					public void onSuccess(ArrayList<FileDeliveryDetails> result) {
						// TODO Auto-generated method stub
						box.close();
						FileDeliveryReportPresenter.this.display.getProxy().setData(result);
						FileDeliveryReportPresenter.this.display.getLoader().load(0, NRTRDE.constants.pageSize());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						box.close();
						if(caught instanceof SessionException) {
							NRTRDE.showSessionDialog();
						} else {
							MessageBox.alert("Error excecuting FileDelivery Report","Error: " + caught.getMessage(), null);
							GWT.log("Error:" + caught);
						}
					}
				});
				
			}
		});
		
		
		
	}

}
