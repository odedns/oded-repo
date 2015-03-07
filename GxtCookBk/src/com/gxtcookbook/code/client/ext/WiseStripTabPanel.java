package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.Element;
import com.gxtcookbook.code.client.events.Events;

public class WiseStripTabPanel extends TabPanel {
	
	public WiseStripTabPanel(){
		super();
	}
	
	@Override
	protected void onRender(Element parent, int index){
		super.onRender(parent, index);
		
		hideTabStrip();
		addListener(Events.Add, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent evt) {
				if(getItemCount() >= 2){
					showTabStrip();
				}
			}
			
		});
		addListener(Events.Remove, new Listener<TabPanelEvent>() {
			@Override
			public void handleEvent(TabPanelEvent evt) {
				if(getItemCount() == 1){
					hideTabStrip();
				}
			}
			
		});
				
	}

	private void hideTabStrip() {
		TabItem lastMan = getItem(0);
		String cls = ".x-tab-strip-wrap";
		El stripWrap = lastMan.getHeader().el().findParent(cls, 10);
		stripWrap.hide();
	}

	private void showTabStrip() {
		TabItem lastMan = getItem(0);
		String cls = ".x-tab-strip-wrap";
		El stripWrap = lastMan.getHeader().el().findParent(cls, 10);
		stripWrap.show();		
	}

}
