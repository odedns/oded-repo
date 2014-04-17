package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.gxtcookbook.code.client.events.Events;


public class BlinkTabItem extends TabItem {
	
	private Timer blinker;
	
	protected boolean blinking;
	protected int blinkInterval;
	
	public BlinkTabItem(){
		super();
		initBlink();
	}
	
	public BlinkTabItem(String text) {
		super(text);
		initBlink();
	}
	
	@Override
	protected void onRender(Element parent, int index){
		super.onRender(parent, index);
		this.getTabPanel().addListener(Events.Select, new Listener<TabPanelEvent>(){
			@Override
			public void handleEvent(TabPanelEvent be) {
				if(isBlinking()){
					stopBlinking();					
				}
			}			
		});
	}	
	
	private void initBlink(){
		blinking = false;
		blinkInterval = 800;		
	}
	
	public boolean isBlinking() {
		return blinking;		
	}

	public void stopBlinking() {
		blinker.cancel();
		blinking = false;		
	}
	
	public void startBlinking(){
		startBlinking(blinkInterval);
	}

	public void startBlinking(int interval) {
		TabItem active = this.getTabPanel().getSelectedItem();
		if(isBlinking() || this.equals(active) || !header.isEnabled()){
			return;
		}
		
		final El headerEl = header.el();
		blinker = new Timer() {							
			@Override
			public void run() {								
				String style = "x-tab-strip-over";
				if(headerEl.hasStyleName(style)){
					headerEl.setStyleName(style, false);
				}else{
					headerEl.setStyleName(style, true);
				}	
			}
		};						
		blinker.scheduleRepeating(interval);
		blinking = true;
	}

}
