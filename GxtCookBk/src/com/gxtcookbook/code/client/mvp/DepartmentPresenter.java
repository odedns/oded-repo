package com.gxtcookbook.code.client.mvp;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.data.DataCenter;
import com.gxtcookbook.code.client.data.Response;
import com.gxtcookbook.code.server.model.persisted.mvp.DepartmentModel;
import com.gxtcookbook.code.server.model.persisted.mvp.MvpDepartment;

public class DepartmentPresenter extends AbstractPresenter {
		
	public interface DepartmentDisplay extends MutableDisplay{
		public DepartmentDisplay display();
		public ListStore<BeanModel> store();
	}

	private static DepartmentPresenter instance;	
	public static DepartmentPresenter get(){
		instance = (instance == null ? new DepartmentPresenter() : instance);
		return instance;
	}
	
	private DepartmentDisplay view;
	private ActionsLoadListener actnsLoadListener; 
	
	protected DepartmentPresenter() {
		super();		
		actnsLoadListener = new ActionsLoadListener();
	}
	
	@Override
	protected void onBindActions() {
		if(view != null){
			setActionsManager(view.display().actionsManager());
			
			BaseAction actn = (BaseAction) view.display().refreshAction();
			if(actn != null && !actn.hasProvider()){
				actn.setProvider(new BaseAction.ActionProvider() {				
					@Override
					public void perform() {
						view.display().store().getLoader().load();
					}
				});
			}
			
			actn = (BaseAction) view.display().saveAction();
			if(actn != null && !actn.hasProvider()){
				actn.setProvider(new BaseAction.ActionProvider() {				
					@Override
					public void perform() {
						runSave();
					}
				});
			}			
		}
	}
	
	// setup the initial state of the actions
	// some may have to start disabled
	protected void beginActions(){
		if(view != null){
			view.actionsManager().enableAll();
			BaseAction actn = (BaseAction) view.display().saveAction();
			if(actn != null){
				actn.setEnabled(false);
			}
		}			
	}

	private void runSave() {
		List<Record> modified = view.display().store().getModifiedRecords();
		if(modified.size() >= 1){
			MvpEvents.getBus().fireEvent(MvpEvents.SaveDepartment);
			
			ArrayList<DepartmentModel> changes = new ArrayList<DepartmentModel>();
	        for (Record record : modified) {
	        	MvpDepartment dept = (MvpDepartment) ((BeanModel) record.getModel()).getBean();
	        	changes.add(dept);			            
	        }
	        
	        view.actionsManager().disableAll();
			DataCenter.get().rpcService().saveDepartments(changes, new AsyncCallback<Response>() {
	        	@Override
	        	public void onFailure(Throwable caught) {
	        		MvpEvents.getBus().fireEvent(MvpEvents.DepartmentSaveERR);
	        		Info.display("RPC Error", caught.getMessage());
	        	}
	        	
	        	@Override
	        	public void onSuccess(Response result) {	        		
	        		if(Response.STATUS_OK.equals(result.getStatus())){
	        			view.display().store().commitChanges();
						beginActions();
						MvpEvents.getBus().fireEvent(MvpEvents.DepartmentSaveOK);
						
						// used for comet
						// request the server to notify
						// others of the changes made
						pushChanges();
	        		}else{
	        			Info.display("Server Error", result.getMessages().toString());
	        			MvpEvents.getBus().fireEvent(MvpEvents.DepartmentSaveERR);
	        		}	        		
	        	}
			});										
		}
	}

	public DepartmentDisplay display() {
		return view;
	}

	public void setDisplay(DepartmentDisplay display) {
		this.view = display;
		bindActions();
		if(!view.display().store().getLoader().getListeners(Loader.Load).contains(actnsLoadListener)){
			view.display().store().getLoader().addLoadListener(actnsLoadListener);
		}		
	}
	
	private void pushChanges(){
		DataCenter.get().cometRpcService().mvpDepartmentsUpdated(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {}
			
			@Override
			public void onSuccess(Void result) {
																						
			}
		});
	}
	
	private class ActionsLoadListener extends LoadListener{
		public ActionsLoadListener() {
			super();
		}
		
		@Override
		public void loaderBeforeLoad(LoadEvent le) {
			super.loaderBeforeLoad(le);			
			view.actionsManager().disableAll();
		}
		
		@Override
		public void loaderLoad(LoadEvent le) {
			super.loaderLoad(le);
			beginActions();
		}
	}

}
