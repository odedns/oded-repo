package com.gxtcookbook.code.client.ext;

import java.util.List;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

public interface Wizard {
	
	public enum DIR{
		NEXT, BACK
	}
			
	boolean hasNext();		
	boolean hasPrevious();
	void navigate(DIR dir);		
	LayoutContainer getNext();
	LayoutContainer getActive();
	LayoutContainer getPrevious();
	void addCard(LayoutContainer card);
	void addCards(List<LayoutContainer> cards);

}
