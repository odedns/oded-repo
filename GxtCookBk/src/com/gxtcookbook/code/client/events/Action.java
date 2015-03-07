package com.gxtcookbook.code.client.events;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

@SuppressWarnings("serial")
public abstract class Action extends BaseModel implements ActionAware {

	protected String name;
    protected String title;
    protected ToolTipConfig tipCfg;
    protected boolean enabled = true;
    protected AbstractImagePrototype icon;

    public Action() {}

    public Action(String title) {
        setTitle(title);
    }

    public Action(String title, AbstractImagePrototype icon){
        this(title);
        setIcon(icon);
    }

    public Action(String title, AbstractImagePrototype icon, ToolTipConfig tip){
        this(title, icon);
        setTip(tip);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean newValue) {
        boolean oldValue = this.enabled;

        if (oldValue != newValue) {
            this.enabled = newValue;
            notifyPropertyChanged("enabled", newValue, oldValue);
        }
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return icon;
    }

    @Override
    public ToolTipConfig getTip() {
        return tipCfg;
    }

    @Override
    public final void setIcon(AbstractImagePrototype icon) {
        this.icon = icon;
    }

    @Override
    public final void setTip(ToolTipConfig tipCfg) {
        this.tipCfg = tipCfg;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public final void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void runAction() {
        this.actionPerformed();
    }

}
