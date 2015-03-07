package com.gxtcookbook.code.server.comet.event;

import de.novanic.eventservice.client.event.Event;

public class MvpDepartmentUpdate implements Event {
	
	private static final long serialVersionUID = 1L;
	
	public static final String DOMAIN = "mvp_dept_domain";
	
	public MvpDepartmentUpdate() {
		super();
	}
}
