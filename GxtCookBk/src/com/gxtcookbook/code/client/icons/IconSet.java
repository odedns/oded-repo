package com.gxtcookbook.code.client.icons;

import com.google.gwt.core.client.GWT;
import com.gxtcookbook.code.client.icons.fugue.FugueIcons;

public interface IconSet {

	Misc misc = GWT.create(Misc.class);
	FugueIcons fugue = GWT.create(FugueIcons.class);
}
