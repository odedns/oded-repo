package com.gxtcookbook.code.client.mvp;

import com.gxtcookbook.code.client.events.Action;

public interface MutableDisplay extends ModelDisplay {
	public Action addAction();
	public Action saveAction();
	public Action deleteAction();
}
