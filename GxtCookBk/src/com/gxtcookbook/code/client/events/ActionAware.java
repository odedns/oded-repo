package com.gxtcookbook.code.client.events;

import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public interface ActionAware extends ActionListener {
	public void setName(String name);

	public String getName();

	public void setTitle(String title);

	public String getTitle();

	public void setIcon(AbstractImagePrototype icon);

	public AbstractImagePrototype getIcon();

	public void setTip(ToolTipConfig tipCfg);

	public ToolTipConfig getTip();

	public void setEnabled(boolean b);

	public boolean isEnabled();

	public void addChangeListener(ChangeListener... listener);

	public void removeChangeListener(ChangeListener... listener);
}
