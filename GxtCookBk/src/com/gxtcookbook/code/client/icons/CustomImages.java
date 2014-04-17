package com.gxtcookbook.code.client.icons;

import com.extjs.gxt.ui.client.image.XImages;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public interface CustomImages extends XImages {

	@Resource("sort-asc.gif")
	AbstractImagePrototype grid_sortAsc();

	@Resource("sort-desc.gif")
	AbstractImagePrototype grid_sortDesc();

	@Resource("columns.gif")
	AbstractImagePrototype grid_columns();

	@Resource("group-by.gif")
	AbstractImagePrototype grid_groupBy();
}
