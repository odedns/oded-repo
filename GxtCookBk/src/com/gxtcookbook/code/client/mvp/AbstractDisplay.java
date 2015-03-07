package com.gxtcookbook.code.client.mvp;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.gxtcookbook.code.client.events.Action;

public abstract class AbstractDisplay {
	protected Map<String, Action> actionsMap;
	protected ActionsManager actionsManager;
	
	public AbstractDisplay() {
		super();
		
		actionsManager = new ActionsManager();		
		actionsMap = new HashMap<String, Action>(); 
		initActions();
	}
	
	private void initActions(){
		onInitActions();
		getActionsManager().buildActions(actionsMap);
	}
	
	protected Map<String, Action> getActionsMap(){
		return actionsMap;
	}
	
	public ActionsManager getActionsManager(){
		return actionsManager;
	}
	
	protected abstract void onInitActions();

	public abstract Menu ctxMenu();
	public abstract Component viewComponent();
	
}
