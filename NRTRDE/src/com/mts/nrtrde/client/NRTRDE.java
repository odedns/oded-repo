package com.mts.nrtrde.client;



import java.util.ArrayList;

import com.extjs.gxt.themes.client.Access;
import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;

import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mts.nrtrde.client.event.NavigationEvent;
import com.mts.nrtrde.client.presenter.HeaderPresenter;
import com.mts.nrtrde.client.presenter.TreePresenter;
import com.mts.nrtrde.client.view.HeaderView;
import com.mts.nrtrde.client.view.NavigationView;
import com.mts.nrtrde.client.view.TreeView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NRTRDE implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */


	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	 public static final int TABLE_HEIGHT = 580;
	 public static final int TABLE_WIDTH = 600;
	 
	 public static final AppResources images = GWT.create(AppResources.class);
	 public static final NRTRDEConstants constants = GWT.create(NRTRDEConstants.class);
	 public static final NRTRDEServiceAsync nrtrdeService = GWT.create(NRTRDEService.class);
	 public static final NRTRDEMessages nrtrdeMessage = GWT.create(NRTRDEMessages.class);
	 public final SimpleEventBus eventBus = new SimpleEventBus();
	 public AppController appController = null;
	 public static NavigationView navView; 
	 public static ArrayList<OperatorValueObject> operList = null;
	 public static ArrayList<OperatorValueObject> allOperList = null;
	 public static ArrayList<OperatorValueObject> commercialList = null;
	 public static ArrayList<OperatorValueObject> nonCommercialList = null;
	 
	/**
	 * This is the entry point method.
	 */		 
	public void onModuleLoad() {

		ThemeManager.register(Slate.SLATE);
		ThemeManager.register(Access.ACCESS);
		GXT.setDefaultTheme(Theme.BLUE, false);
				
		init();
		showLogin();		
			
	}
	
	
	/**
	 * build the main screen UI.
	 */
	private void buildUI()
	{
		HeaderView headerView = new HeaderView();
		
		@SuppressWarnings("unused")
		HeaderPresenter headerPresenter = new HeaderPresenter(eventBus, headerView);
		
		TreeView treeView = new TreeView();
		TreePresenter treePresenter = new TreePresenter(eventBus,treeView);
	
		navView = new NavigationView();
		SimplePanel	content = new SimplePanel();

		content.setSize("100%", "100%");
		
		VerticalPanel vContentPanel = new VerticalPanel();
		vContentPanel.setSize("100%", "100%");
		vContentPanel.add(navView);
		vContentPanel.add(content);							 
		this.appController = new AppController(content,eventBus);
		
		
		
		RootPanel.get("headerId").add(headerView);
		RootPanel.get("navId").add(treeView);
		/*
		 * need to expand the tree only after adding the TreePanel to 
		 * the root panel.
		 */
		treeView.getTree().expandAll();
		RootPanel.get("contId").add(vContentPanel);
		
		
	}
	
	private void showLogin() {
		// TODO Auto-generated method stub
		
		final Dialog loginDialog = new Dialog() {
			protected void createButtons() {
				super.createButtons();
				getButtonBar().removeAll();
				Button loginBtn = new Button("Login");
				loginBtn.setItemId("login");
				setFocusWidget(null);
				addButton(loginBtn);
		
			}
		};
		loginDialog.setClosable(false);
		
		FormPanel formPanel = new FormPanel();
		formPanel.setHeading("Login");
		
		final TextField<String> userField = new TextField<String>();
		userField.setFieldLabel("Username");
		final TextField<String> passField = new TextField<String>();
		passField.setFieldLabel("Password");
		passField.setPassword(true);
		
		//formPanel.add(loginButton);
		
		Button loginButton = loginDialog.getButtonById("login");
		loginButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				String user = userField.getValue();
				String pass = passField.getValue();
				// TODO Auto-generated method stub
				nrtrdeService.login(user, pass, new AsyncCallback<Boolean>() {
					
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						loginDialog.hide();						
						buildUI();
						eventBus.fireEvent(new NavigationEvent("NRTRDE"));		
					}
					
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						MessageBox.alert("Error","Login Failed: wrong user name or passoword",null);
					}
				});
				
						 		
			}
		});
			
		formPanel.setFieldWidth(150);
		formPanel.add(userField);
		formPanel.add(passField);
		loginDialog.setHideOnButtonClick(true);
		loginDialog.add(formPanel);
		loginDialog.setWidth(300);
		loginDialog.layout();
				 
        loginDialog.show();
	}
	
	private void init() 
	{
		nrtrdeService.getOperators(new AsyncCallback<ArrayList<OperatorValueObject>>() {
			
			public void onSuccess(ArrayList<OperatorValueObject> result) {
				// TODO Auto-generated method stub
				/*
				 * lists can only be filtered after
				 * this call returns.
				 */
				NRTRDE.operList = result;
				NRTRDE.allOperList = new ArrayList<OperatorValueObject>();
				NRTRDE.allOperList.add(new OperatorValueObject("all", true, "ALL Operators"));
				NRTRDE.allOperList.addAll(operList);
				
				NRTRDE.commercialList = filterList(NRTRDE.operList, true);
				NRTRDE.nonCommercialList = filterList(NRTRDE.operList, false);
			}
			
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("getOperators Failed: " + caught.getMessage());
				GWT.log("getOptions failed: " + caught);
			}
		});		
		
	}
	
	/**
	 * filter the operator lists for commercial and noncommercial.
	 * @param list the operator list
	 * @param commercial true if commectial
	 * @return the filtered list. 
	 */
	private ArrayList<OperatorValueObject> filterList(ArrayList<OperatorValueObject> list, boolean commercial)
	{
		ArrayList<OperatorValueObject> newList = new ArrayList<OperatorValueObject>();
		for(int i=0; i < list.size(); ++i) {
			OperatorValueObject v = list.get(i);
			if(v.getType() == commercial) {
				newList.add(v);
			}
		}
		return(newList);
		
		
	}
	
	public static void showSessionDialog()
	{
		MessageBox.info("Session Expired", "The Session has Expired", new Listener<MessageBoxEvent>() {
			
			@Override
			public void handleEvent(MessageBoxEvent be) {
				// TODO Auto-generated method stub
				GWT.log("Session has expired");
				Window.Location.reload();	
			}
		});
	}
	
}
