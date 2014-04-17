package com.gxtcookbook.code.client.comet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("remote/evtgateway")
public interface CometGateway extends RemoteService {

	public void mvpDepartmentsUpdated();
	
}
