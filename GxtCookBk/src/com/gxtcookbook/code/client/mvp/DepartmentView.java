package com.gxtcookbook.code.client.mvp;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.gxtcookbook.code.client.data.DataCenter;
import com.gxtcookbook.code.client.data.LiveStore;
import com.gxtcookbook.code.client.events.Action;
import com.gxtcookbook.code.client.icons.IconSet;
import com.gxtcookbook.code.client.mvp.DepartmentPresenter.DepartmentDisplay;

public abstract class DepartmentView extends AbstractDisplay implements DepartmentDisplay {		
	
	public enum ActionKeys{
		REFRESH, SAVE
	}
		
	// Used for Comet
	protected LiveStore liveStore;
	
	protected ListStore<BeanModel> store;
	
	@SuppressWarnings("unchecked")
	protected DepartmentView(){
		super();
		
		DataCenter.init();
		String storeKey = DataCenter.StoreKeys.MVP_DEPARTMENT_STORE.encode();
		store = (ListStore<BeanModel>) Registry.get(storeKey);
		
		// used for comet
		liveStore = new LiveStore(store);
	}

	@Override
	public Action refreshAction() {
		// What will perform refreshes
		return getActionsMap().get(ActionKeys.REFRESH.name());
	}

	@Override
	public ActionsManager actionsManager() {
		// return actionsManager
		// from super class
		return getActionsManager();
	}

	@Override
	public Action addAction() {
		// We decline this action
		return null;
	}

	@Override
	public Action saveAction() {
		// What will perform saves
		return getActionsMap().get(ActionKeys.SAVE.name());
	}

	@Override
	public Action deleteAction() {
		// We decline this action
		return null;
	}

	@Override
	protected void onInitActions() {
		// Setup the actions that
		// can be performed on
		// "displays" of "department"
		getActionsMap().put(ActionKeys.SAVE.name(), new BaseAction("Save", AbstractImagePrototype.create(IconSet.misc.disk())));
		getActionsMap().put(ActionKeys.REFRESH.name(), new BaseAction("Refresh", AbstractImagePrototype.create(IconSet.misc.refresh())));		

	}

}
