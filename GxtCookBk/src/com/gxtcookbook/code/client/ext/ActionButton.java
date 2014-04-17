package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.data.PropertyChangeEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.gxtcookbook.code.client.events.Action;

public class ActionButton extends Button {

	public ActionButton(final Action action) {
		super(action.getTitle(), action.getIcon());

		setToolTip(action.getTip());
		setEnabled(action.isEnabled());

		addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent evt) {
				action.actionPerformed();
			}
		});

		// make sure changes in the "enabled" state of 
		// the action are propagated to the Button
		action.addChangeListener(new ChangeListener() {
			@Override
			public void modelChanged(ChangeEvent event) {
				PropertyChangeEvent evt = (PropertyChangeEvent) event;
				if (evt.getName().equals("enabled")) {
					boolean enabled = (Boolean) evt.getNewValue();
					setEnabled(enabled);
				}
			}
		});

	}
}
