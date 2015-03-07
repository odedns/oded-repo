package com.gxtcookbook.code.client.mvp;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.gxtcookbook.code.client.data.LiveStore;
import com.gxtcookbook.code.client.ext.ActionMenu;
import com.gxtcookbook.code.client.mvp.DepartmentPresenter.DepartmentDisplay;
import com.gxtcookbook.code.server.comet.event.MvpDepartmentUpdate;

public class DepartmentListView extends DepartmentView {
	
	private static DepartmentListView instance;
	
	public static void init(){
		instance = (instance == null ? new DepartmentListView() : instance);
	}
	
	public static DepartmentListView get(){
		init();
		return instance;
	}
	
	private ActionMenu ctxMenu;
	private ListView<BeanModel> listView;
	
	protected DepartmentListView(){
		super();
		
		// Create the list-view,
		// giving it the store of beans
		// and the template from a call
		// to our getTemplate().
		// We also configure setItemSelector()
		// and setSelectStyle() responsible for
		// how items in the list behave when
		// they are selected.
	   listView = new ListView<BeanModel>();
	   listView.setStore(store);
	   listView.setItemSelector("div.x-department-item");
	   listView.setSelectStyle("x-department-item-sel");
	   listView.setTemplate(getTemplate());	 
	   listView.setLoadingText("Loading");
	   
	   listView.addListener(MvpEvents.Attach, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent evt) {
				if(store.getModels().size() <= 0){
					store.getLoader().load();
				}				
			};
		});	
	   
	    // Used for Comet
		liveStore.addObserver(new LiveStore.UpdateObserver() {	
			@Override
			public void broadcastReceived(ListStore<? extends ModelData> store, MvpDepartmentUpdate updateEvt) {
				if(listView.isVisible()){
					listView.refresh();
				}				
			}
		});
	}

	@Override
	public DepartmentDisplay display() {
		init();
		return instance;
	}

	@Override
	public ListStore<BeanModel> store() {
		return store;
	}

	@Override
	public Menu ctxMenu() {
		return ctxMenu;
	}

	@Override
	public Component viewComponent() {
		return listView;
	}
	
	// Give us a template string,
	// however we are using JSNI,
	// hence the JavaScript syntax.
	// Can equally be achieved with a
	// StringBuilder by appending 
	// these same strings to it.
	private native String getTemplate()/*-{
		return ['<tpl for=".">',  
	       '<div class="x-department-item">', 
		       '<div class="code">{code}</div>',  
		       '<div class="name">{name}</div>',  
	       '</div>',  
	   '</tpl>',  
	   ''].join("");
	}-*/;

}
