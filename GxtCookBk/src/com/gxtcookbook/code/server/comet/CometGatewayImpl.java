package com.gxtcookbook.code.server.comet;

import com.gxtcookbook.code.client.comet.CometGateway;
import com.gxtcookbook.code.server.comet.event.MvpDepartmentUpdate;

import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.service.RemoteEventServiceServlet;

public class CometGatewayImpl extends RemoteEventServiceServlet implements
		CometGateway {

	private static final long serialVersionUID = 1L;

	public CometGatewayImpl() {
		super();
	}

	@Override
	public void mvpDepartmentsUpdated() {
		addEvent(DomainFactory.getDomain(MvpDepartmentUpdate.DOMAIN), new MvpDepartmentUpdate());
	}

}
