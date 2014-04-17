package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class NavItem extends BaseTreeModel {

	private static final long serialVersionUID = 2322703650193448307L;
	private static int ID = 0;

	public NavItem() {
		set("id", ID++);
	}

	public NavItem(String title, String key) {
		this();

		set("key", key);
		set("name", title);
	}

	public NavItem(String name, BaseTreeModel[] children) {
		set("name", name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}
}
