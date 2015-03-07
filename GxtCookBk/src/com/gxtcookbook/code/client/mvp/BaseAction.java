package com.gxtcookbook.code.client.mvp;

import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.gxtcookbook.code.client.events.Action;

@SuppressWarnings("serial")
public class BaseAction extends Action {
	
	public interface ActionProvider{
		public void perform();
	}
	
	private ActionProvider provider;
	
	public BaseAction(){
		super();
		provider = null;
	}
	
	public BaseAction(String title) {
		super(title);
    }

    public BaseAction(String title, AbstractImagePrototype icon){
    	super(title, icon);
    }

    public BaseAction(String title, AbstractImagePrototype icon, ToolTipConfig tip){
        super(title, icon, tip);
    }        

	public void setProvider(ActionProvider provider) {
		this.provider = provider;
	}
	
	public boolean hasProvider(){
		return !(this.provider == null);
	}

	@Override
	public void actionPerformed() {
		if(provider == null){
			return;
		}
		provider.perform();
	}

}
