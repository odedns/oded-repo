package com.gxtcookbook.code.client.comet;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CometGatewayAsync {
	void mvpDepartmentsUpdated(AsyncCallback<Void> callback);
}
