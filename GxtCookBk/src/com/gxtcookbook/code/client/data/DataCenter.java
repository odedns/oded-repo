package com.gxtcookbook.code.client.data;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadConfig;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseObservable;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gxtcookbook.code.client.RemoteGateway;
import com.gxtcookbook.code.client.RemoteGatewayAsync;
import com.gxtcookbook.code.client.comet.CometGateway;
import com.gxtcookbook.code.client.comet.CometGatewayAsync;
import com.gxtcookbook.code.server.model.persisted.Course;
import com.gxtcookbook.code.server.model.persisted.Department;
import com.gxtcookbook.code.server.model.persisted.Student;
import com.gxtcookbook.code.server.model.persisted.mvp.DepartmentModel;

import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;

public class DataCenter extends BaseObservable {
	
	public enum VType{
		
		EMAIL("^(\\w+)([\\-+.][\\w]+)*@(\\w[\\-\\w]*\\.){1,5}([A-Za-z]){2,6}$");
		
		private String type;
		
		private VType(String t){
			type = t;
		}
		
		public String get(){
			return type;
		}
	}

	public enum StoreKeys{
		STUDENT_LIST_STORE, COURSE_LIST_STORE, DEPARTMENT_LIST_STORE, MVP_DEPARTMENT_STORE;
		
		public String encode(){
			return this.name();
		}
	};
	
	private RemoteGatewayAsync rpcService;
		
	private RemoteEventService cometEventMgr;
	private CometGatewayAsync cometRpcService;
	
	private static DataCenter instance;

    public static void init(){
    	instance = instance == null ? new DataCenter() : instance;
    }

	public static DataCenter get() {
		init();
		return instance;
	}
	
	private DataCenter() {
		super();
		
		rpcService = (RemoteGatewayAsync) GWT.create(RemoteGateway.class);
		
		RemoteEventServiceFactory serviceFctry = RemoteEventServiceFactory.getInstance();
		cometEventMgr = serviceFctry.getRemoteEventService();
		cometRpcService = (CometGatewayAsync) GWT.create(CometGateway.class);
		
		buildStores();
	}		

	public RemoteGatewayAsync rpcService() {
		return rpcService;
	}
	
	public RemoteEventService cometEventMgr() {
		return cometEventMgr;
	}
	
	public CometGatewayAsync cometRpcService(){
		return cometRpcService;
	}

	private void buildStores() {		
		courseStore();
		studentStore();
		departmentStore();
		mvpDepartmentStore();
	}

	private void studentStore() {
		RpcProxy<ListLoadResult<Student>> rpcProxy = new RpcProxy<ListLoadResult<Student>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<ListLoadResult<Student>> callback) {
                rpcService.listStudents((ListLoadConfig) loadConfig, callback);
            }
        };

        ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
        Registry.register(StoreKeys.STUDENT_LIST_STORE.encode(), store);
	}

	private void courseStore() {
		RpcProxy<ListLoadResult<Course>> rpcProxy = new RpcProxy<ListLoadResult<Course>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<ListLoadResult<Course>> callback) {
                rpcService.listCourses((ListLoadConfig) loadConfig, callback);
            }
        };

        ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
        Registry.register(StoreKeys.COURSE_LIST_STORE.encode(), store);
	}

	protected void departmentStore(){
		RpcProxy<ListLoadResult<Department>> rpcProxy = new RpcProxy<ListLoadResult<Department>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<ListLoadResult<Department>> callback) {
                rpcService.listDepartments((ListLoadConfig) loadConfig, callback);
            }
        };

        ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
        Registry.register(StoreKeys.DEPARTMENT_LIST_STORE.encode(), store);
	}
	
	private void mvpDepartmentStore() {
		RpcProxy<ListLoadResult<DepartmentModel>> rpcProxy = new RpcProxy<ListLoadResult<DepartmentModel>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<ListLoadResult<DepartmentModel>> callback) {
            	DataCenter.get().rpcService().listDepartmentModels((ListLoadConfig) loadConfig, callback);
            }
        };

        ListLoader<ListLoadResult<ModelData>> loader = new BaseListLoader<ListLoadResult<ModelData>>(rpcProxy, new BeanModelReader());
        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
        Registry.register(StoreKeys.MVP_DEPARTMENT_STORE.encode(), store);
	}

}
