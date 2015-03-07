package com.gxtcookbook.code.client.mvp;

import com.extjs.gxt.ui.client.event.BaseObservable;
import com.extjs.gxt.ui.client.event.EventType;



public class MvpEvents extends com.extjs.gxt.ui.client.event.Events {
	
	public static final EventType SaveDepartment = new EventType();
	public static final EventType DepartmentSaveOK = new EventType();
	public static final EventType DepartmentSaveERR = new EventType();
	public static final EventType DepartmentChanged = new EventType();

	private static BaseObservable instance;

	public static BaseObservable getBus() {
		instance = (instance == null ? new BaseObservable() : instance);
		return instance;
	}
}