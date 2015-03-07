package com.gxtcookbook.code.client.mvp;

import com.gxtcookbook.code.client.events.Action;

public interface ModelDisplay {
	public Action refreshAction();
	public ActionsManager actionsManager();
}
