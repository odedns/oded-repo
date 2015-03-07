/**
 * 
 */
package com.mts.nrtrde.client;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mts.nrtrde.client.event.NavigationEvent;
import com.mts.nrtrde.client.event.NavigationEventHandler;
import com.mts.nrtrde.client.presenter.AlertsPresenter;
import com.mts.nrtrde.client.presenter.CollectPresenter;
import com.mts.nrtrde.client.presenter.ErrorReportPresenter;
import com.mts.nrtrde.client.presenter.FileDeliveryReportPresenter;
import com.mts.nrtrde.client.presenter.GenerationPresenter;
import com.mts.nrtrde.client.presenter.ParametersPresenter;
import com.mts.nrtrde.client.presenter.Presenter;
import com.mts.nrtrde.client.presenter.WelcomePresenter;
import com.mts.nrtrde.client.view.AlertsView;
import com.mts.nrtrde.client.view.CollectView;
import com.mts.nrtrde.client.view.ErrorReportView;
import com.mts.nrtrde.client.view.FileDeliveryReportView;
import com.mts.nrtrde.client.view.GenerationView;
import com.mts.nrtrde.client.view.ParametersView;
import com.mts.nrtrde.client.view.WelcomeView;


/**
 * @author Oded Nissan
 *
 */
public class AppController implements Presenter, ValueChangeHandler<String> {
	
	private EventBus eventBus;
	private HasWidgets  container;
	private HashMap<String,Presenter> navigationMap;
	private String currentToken;
	
	public AppController(HasWidgets sPanel,EventBus eventBus)
	{
		this.eventBus = eventBus;
		this.container = sPanel;
		bind();
		
	}
	
	
	private void initPresenters()
	{
		navigationMap = new HashMap<String,Presenter>();
		//navigationMap.put(NRTRDE.constants.generate(), value)
		WelcomePresenter welcomePresenter = new WelcomePresenter(this.eventBus, new WelcomeView());
		navigationMap.put("NRTRDE", welcomePresenter);
		GenerationPresenter generatePresenter = new GenerationPresenter(this.eventBus,new GenerationView());
		navigationMap.put(NRTRDE.constants.generation(), generatePresenter);
		CollectPresenter collectPresenter = new CollectPresenter(this.eventBus,new CollectView());
		navigationMap.put(NRTRDE.constants.collect(), collectPresenter);
	
		FileDeliveryReportPresenter filePresenter = new FileDeliveryReportPresenter(eventBus, new FileDeliveryReportView());
		navigationMap.put(NRTRDE.constants.fileDeliveryReport(), filePresenter);
		ErrorReportPresenter errorPresenter = new ErrorReportPresenter(eventBus, new ErrorReportView());
		navigationMap.put(NRTRDE.constants.errorReport(), errorPresenter);
		ParametersPresenter paramPresenter = new ParametersPresenter(eventBus, new ParametersView());
		navigationMap.put(NRTRDE.constants.params(), paramPresenter);				
		AlertsPresenter alertsPresenter = new AlertsPresenter(eventBus, new AlertsView());
		navigationMap.put(NRTRDE.constants.alerts(), alertsPresenter);
		
	}
	
	
	
	public void bind()
	{
		initPresenters();
		 History.addValueChangeHandler(this);
		 eventBus.addHandler(NavigationEvent.TYPE,
			        new NavigationEventHandler() {
			          public void doNavigation(NavigationEvent event) {
			        	currentToken = event.getId();
			            GWT.log("navigation event: " + event.getId());
			            Presenter presenter = navigationMap.get(event.getId());
			            if(null == presenter) {
			            	GWT.log("Cannot find Presenter:" + event.getId());
			            	//Window.alert("cannot find presenter");
			            
			            } else {
			            	if(event.getParent() != null) {
			            		NRTRDE.navView.getNavigation().setText(event.getParent() + "-->"+ event.getId());			            		
			            	} else {
			            		NRTRDE.navView.getNavigation().setText(event.getId());
			            	}
			            	presenter.go(container);
			            	History.newItem(event.getId());
			            }
			          }
			        });  

		
	}

	

	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub
		 String historyToken = event.getValue();
		 GWT.log("History Token = " + historyToken + "\tcurrentToken = " + currentToken);
		 /*
		  * check that the currentToken is different than the historyToken
		  * before applying the history.
		  */
		 if(!historyToken.equals(currentToken)) {
			 eventBus.fireEvent(new NavigationEvent(historyToken));
		 }
	}

	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}

}
