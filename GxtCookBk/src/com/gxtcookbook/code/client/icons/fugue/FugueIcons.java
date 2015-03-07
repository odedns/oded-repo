package com.gxtcookbook.code.client.icons.fugue;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

import com.gxtcookbook.code.client.icons.fugue.x32.FugueX32;


public interface FugueIcons extends ClientBundle {
	
	FugueX32 x32 = GWT.create(FugueX32.class);		
	
	ImageResource acorn();

	ImageResource address_book();

	ImageResource address_book_arrow();

	ImageResource address_book_blue();

}
