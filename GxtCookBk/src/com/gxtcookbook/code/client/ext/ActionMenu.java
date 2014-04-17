package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.data.PropertyChangeEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.gxtcookbook.code.client.events.Action;

public class ActionMenu extends Menu {
	
	public boolean add(final Action action) {

		final MenuItem item = new MenuItem(action.getTitle(), action.getIcon());

		item.setToolTip(action.getTip());
		item.setEnabled(action.isEnabled());

		item.addSelectionListener(new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent evt) {
				action.actionPerformed();
			}
		});

		// make sure changes in the "enabled" state of 
		// the action are propagated to the MenuItem
		action.addChangeListener(new ChangeListener() {
			@Override
			public void modelChanged(ChangeEvent event) {
				PropertyChangeEvent evt = (PropertyChangeEvent) event;
				if (evt.getName().equals("enabled")) {
					boolean enabled = (Boolean) evt.getNewValue();
					item.setEnabled(enabled);
				}
			}
		});

		return add(item);
	}
}
