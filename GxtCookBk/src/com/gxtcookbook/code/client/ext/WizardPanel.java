package com.gxtcookbook.code.client.ext;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;

/**
 * implement FormPanel as a wizard in WizardPanel.
 *
 */
public class WizardPanel extends FormPanel  {
	enum DIR{
		NEXT, BACK
	}
	
	private Button nextBtn, prevBtn;
	protected CardLayout cardLayout;
	protected FormButtonBinding btnBind;
	protected List<LayoutContainer> cards;	
	
	
	/**
	 * constructor for WizardPanel
	 */
	public WizardPanel() {
		super();
		// create a CardLayout.
		cardLayout = new CardLayout();		
		setLayout(cardLayout);
		cards = new ArrayList<LayoutContainer>();
		setButtonAlign(HorizontalAlignment.RIGHT);	
		
		// add a Back button
		prevBtn = new Button("Back", new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				navigate(DIR.BACK);
			}
		});
		// disable the Back button and add it to the panel.
		prevBtn.setEnabled(false);
		addButton(prevBtn);
		
		// create the Next button.
		nextBtn = new Button("Next", new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				navigate(DIR.NEXT);
			}
		});		
		btnBind = new FormButtonBinding(this);
		btnBind.addButton(nextBtn);
		addButton(nextBtn);
	}		

	public Button getNextBtn() {
		return nextBtn;
	}

	public Button getPrevBtn() {
		return prevBtn;
	}


	/**
	 * check if there is a next card.
	 * @return true if there is a next card.
	 */
	public boolean hasNext() {
		boolean has = false;
		LayoutContainer active = getActive();
		if(!cards.isEmpty() && cards.indexOf(active)+1 < cards.size()){	
			has = true;
		}		
		return has;
	}

	/**
	 * check if there is a previous card.
	 * @return true if there is a previous card.
	 */
	public boolean hasPrevious() {
		boolean has = false;
		LayoutContainer active = getActive();
		if(!cards.isEmpty() && cards.indexOf(active) >= 1){	
			has = true;
		}		
		return has;
	}

	/**
	 * get the next card.
	 * @return LayoutContainer the next card.
	 */
	public LayoutContainer getNext() {
		LayoutContainer active = getActive();
		LayoutContainer next = cards.get( cards.indexOf(active)+1 );
		return next;
	}

	/**
	 * get the previous card.
	 * @return LayoutContainer the previous card.
	 */
	public LayoutContainer getPrevious() {
		LayoutContainer active = getActive();
		LayoutContainer next = cards.get( cards.indexOf(active)-1 );
		return next;
	}

	
	/**
	 * navigate between the cards according to the DIR
	 * @param dir DIR enum. 
	 */
	public void navigate(DIR dir) {
		LayoutContainer target = null;
		if(DIR.NEXT.equals(dir)){
			target = getNext();
		} else if(DIR.BACK.equals(dir)){
			target = getPrevious();
		}
		
		cardLayout.setActiveItem(target);
		
		// don't confuse our navigation sequence
		boolean hasNext = hasNext();
		if(hasNext){
			btnBind.startMonitoring();
		}else{
			btnBind.stopMonitoring();
		}
		
		getNextBtn().setEnabled(hasNext);
		getPrevBtn().setEnabled(hasPrevious());
	}

	/**
	 * add a card to the panel
	 * @param card the card to add.
	 */
	public void addCard(LayoutContainer card) {
		cards.add(card);
		add(card);
	}

	
	/**
	 * add a list of cards.
	 * @param cards List of cards.
	 */
	public void addCards(List<LayoutContainer> cards) {
		for (LayoutContainer card : cards) {
			addCard(card);
		}
	}

	
	/**
	 * get the active card
	 * @return LayoutContainer the active card.
	 */
	public LayoutContainer getActive() {
		LayoutContainer active = (LayoutContainer) cardLayout.getActiveItem();
		return active;
	}
	
	@Override
	public boolean isValid(boolean preventMark) {
		boolean valid = true;
		for (Field<?> f : getFields()) {
		  if (f.isRendered() && f.isVisible() && !f.isValid(preventMark)) {
		    valid = false;
		  }
		}
		return valid;
	}


}
