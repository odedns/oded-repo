package com.gxtcookbook.code.client.events;

import com.extjs.gxt.ui.client.event.BaseObservable;
import com.extjs.gxt.ui.client.event.EventType;


public class Events extends com.extjs.gxt.ui.client.event.Events {

	public static final EventType NavClick = new EventType();	

	private static BaseObservable instance;

	public static BaseObservable getBus() {
		instance = (instance == null ? (instance = new BaseObservable())
				: instance);
		return instance;
	}

}
