package com.gxtcookbook.code.client.data;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.gxtcookbook.code.client.comet.MvpDepartmentCometListener;
import com.gxtcookbook.code.server.comet.event.MvpDepartmentUpdate;

import de.novanic.eventservice.client.event.domain.DomainFactory;

public class LiveStore {

	public interface UpdateObserver{
		public void broadcastReceived(ListStore<? extends ModelData> store, MvpDepartmentUpdate updateEvt);
	}

	private ListStore<? extends ModelData> store;
	private Set<UpdateObserver> observers;		
	
	public LiveStore(ListStore<? extends ModelData> listStore) {
		store = listStore;
		observers = new HashSet<LiveStore.UpdateObserver>();
		
		listenForCometBroadcast();
	}
	
	public ListStore<? extends ModelData> getStore() {
		return store;
	}
	
	public void addObserver(LiveStore.UpdateObserver observer){
		observers.add(observer);
	}

	private void listenForCometBroadcast(){			
		DataCenter.get().cometEventMgr().addListener(DomainFactory.getDomain(MvpDepartmentUpdate.DOMAIN), new MvpDepartmentCometListener() {					
			@Override
			public void onUpdate(MvpDepartmentUpdate evt) {
				// got fetch the updates;
				// especially for new records
				// that will need id.
				// The observers are called
				// before and after the data arrives
				// to make required UI updates if any.
				store.getLoader().addLoadListener(new UpdatesLoadListener(evt));
				store.getLoader().load();
			}
		});
	}
	
	private class UpdatesLoadListener extends LoadListener{
		
		private UpdatesLoadListener me;
		private MvpDepartmentUpdate evt;	
		
		public UpdatesLoadListener(MvpDepartmentUpdate event) {
			super();
			evt = event;
			me = this;
		}
		
		@Override
		public void loaderLoad(LoadEvent le) {
			super.loaderLoad(le);
			for (LiveStore.UpdateObserver observer : observers) {
				observer.broadcastReceived(store, evt);
			}			
			store.getLoader().removeLoadListener(me);
		}
	}

}
