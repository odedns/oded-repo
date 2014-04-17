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
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.ErrorReportDetails;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.OperatorValueObject;
import com.mts.nrtrde.shared.SessionException;

/**
 * @author Oded Nissan
 *
 */
public class ErrorReportPresenter implements Presenter {

	
	@SuppressWarnings("unused")
	private EventBus eventBus;
	private Display display;
	
	public ErrorReportPresenter(EventBus eventBus,Display display)
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
		NumberField getErrCodeField();
		SimpleComboBox<String> getErrTypeCombo();
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
				ErrorReportPresenter.this.display.getNRFileField().enable();
				ErrorReportPresenter.this.display.getFDRFileField().disable();
				ErrorReportPresenter.this.display.getSeqField().disable();
				ErrorReportPresenter.this.display.getFromDateField().disable();
				ErrorReportPresenter.this.display.getToDateField().disable();
			}
		});
		
		this.display.getFDRFileRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				ErrorReportPresenter.this.display.getNRFileField().disable();
				ErrorReportPresenter.this.display.getFDRFileField().enable();
				ErrorReportPresenter.this.display.getSeqField().disable();
				ErrorReportPresenter.this.display.getFromDateField().disable();
				ErrorReportPresenter.this.display.getToDateField().disable();
			}
		});
		
				
		this.display.getDateRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				ErrorReportPresenter.this.display.getNRFileField().disable();
				ErrorReportPresenter.this.display.getFDRFileField().disable();
				
				ErrorReportPresenter.this.display.getSeqField().disable();
				ErrorReportPresenter.this.display.getFromDateField().enable();
				ErrorReportPresenter.this.display.getToDateField().enable();
			}
		});
		
		this.display.getSeqRadio().addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				ErrorReportPresenter.this.display.getNRFileField().disable();
				ErrorReportPresenter.this.display.getFDRFileField().disable();
				
				ErrorReportPresenter.this.display.getSeqField().enable();
				ErrorReportPresenter.this.display.getFromDateField().disable();
				ErrorReportPresenter.this.display.getToDateField().disable();
			}
		});
		
		this.display.getResetButton().addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				ErrorReportPresenter.this.display.getFDRFileField().reset();
				ErrorReportPresenter.this.display.getNRFileField().reset();
				ErrorReportPresenter.this.display.getFromDateField().reset();
				ErrorReportPresenter.this.display.getToDateField().reset();
				ErrorReportPresenter.this.display.getSeqField().reset();
				ErrorReportPresenter.this.display.getErrCodeField().reset();
				ErrorReportPresenter.this.display.getErrTypeCombo().reset();
				ErrorReportPresenter.this.display.getOperList().reset();
			}
		});
		ErrorReportPresenter.this.display.getSearchButton().addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				// TODO Auto-generated method stub
				final MessageBox box = MessageBox.wait("Progress","Executing Error Report Please Wait...", "Retrieving...");
				String operator = ErrorReportPresenter.this.display.getOperator();
				String NRFile = null;
				String FDRFile = null;
				Date fromDate = null;
				Date toDate = null;
				String sequence = null;
				int  errorCode = 0;
				
				String errorType = ErrorReportPresenter.this.display.getErrTypeCombo().getSimpleValue();
				Number  n = ErrorReportPresenter.this.display.getErrCodeField().getValue();
				if(n != null) {
					errorCode = n.intValue();
				}
				
				if(ErrorReportPresenter.this.display.getNRFileField().isEnabled()){
					NRFile = ErrorReportPresenter.this.display.getNRFileField().getValue();
				}
				if(ErrorReportPresenter.this.display.getFDRFileField().isEnabled()){
					FDRFile = ErrorReportPresenter.this.display.getFDRFileField().getValue();
				}
				if(ErrorReportPresenter.this.display.getFromDateField().isEnabled()) {
					fromDate = ErrorReportPresenter.this.display.getFromDateField().getValue();
					toDate = ErrorReportPresenter.this.display.getToDateField().getValue();
				}
				if(ErrorReportPresenter.this.display.getSeqField().isEnabled()) {
					sequence = ErrorReportPresenter.this.display.getSeqField().getValue();
				}
				
				
				NRTRDE.nrtrdeService.executeErrorReport(operator, errorType,errorCode, NRFile, FDRFile, fromDate, toDate, sequence, new AsyncCallback<ArrayList<ErrorReportDetails>>() {
					
					@Override
					public void onSuccess(ArrayList<ErrorReportDetails> result) {
						// TODO Auto-generated method stub
						box.close();
						ErrorReportPresenter.this.display.getProxy().setData(result);
						ErrorReportPresenter.this.display.getLoader().load(0, NRTRDE.constants.pageSize());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						box.close();
						if(caught instanceof SessionException) {
							NRTRDE.showSessionDialog();
						} else {
									
							MessageBox.alert("Error excecuting Error Report","Error: " + caught.getMessage(), null);
							GWT.log("Error:" + caught);
						}
					}
				});
				
			}
		});
		
		
	}

}
