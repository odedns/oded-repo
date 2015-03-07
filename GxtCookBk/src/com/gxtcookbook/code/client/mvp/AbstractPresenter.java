package com.gxtcookbook.code.client.mvp;

public abstract class AbstractPresenter {
	
	ActionsManager actionsManager;
	
	protected AbstractPresenter(){
		super();
		
		setActionsManager(new ActionsManager());
		bindActions();
	}

	public ActionsManager getActionsManager() {
		return actionsManager;
	}

	public void setActionsManager(ActionsManager manager) {
		actionsManager = manager;
	}

	protected void bindActions() {
		onBindActions();
		beginActions();
	}		
	protected void beginActions(){}
	
	protected abstract void onBindActions();

}
