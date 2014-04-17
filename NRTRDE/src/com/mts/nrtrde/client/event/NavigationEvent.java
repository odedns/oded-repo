/**
 * 
 */
package com.mts.nrtrde.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Oded Nissan
 *
 */
public class NavigationEvent extends GwtEvent<NavigationEventHandler> {

	private String id;
	private String parent;
	
	 public static Type<NavigationEventHandler> TYPE = new Type<NavigationEventHandler>();
	 
	 public NavigationEvent(String id) {
		    this.setId(id);
	  }
	 
	 public NavigationEvent(String id, String parent) {
		    this.setId(id);
		    this.setParent(parent);
	  }
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<NavigationEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(NavigationEventHandler handler) {
		// TODO Auto-generated method stub
		handler.doNavigation(this);
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent;
	}

}
