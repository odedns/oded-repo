package com.gxtcookbook.code.client.comet;

import com.gxtcookbook.code.server.comet.event.MvpDepartmentUpdate;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

public abstract class MvpDepartmentCometListener implements RemoteEventListener {

	@Override
	public void apply(Event anEvent) {
		onUpdate((MvpDepartmentUpdate) anEvent);
	}
	
	public abstract void onUpdate(MvpDepartmentUpdate evt);

}
