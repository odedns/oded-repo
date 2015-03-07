/**
 * 
 */
package com.mts.nrtrde.client.presenter;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.mts.nrtrde.client.event.NavigationEvent;

/**
 * @author Oded Nissan
 *
 */
public class TreePresenter implements Presenter, Listener<TreePanelEvent<BaseTreeModel>> {
	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	private EventBus eventBus;
	private Display display;
	
	public TreePresenter(EventBus eventBus,Display display)
	{
		this.eventBus = eventBus;
		this.display = display;
		bind();
		
	}
	
	
	public void bind()
	{
		TreePanel<BaseTreeModel> tree = this.display.getTree();
		if(tree == null) {
			GWT.log("Error Tree is null");
		//	MessageBox.alert("Error", "TreePanel is null !", null);
		}
		tree.addListener(Events.OnClick, this);
	}
	/* (non-Javadoc)
	 * @see com.mts.nrtrde.client.presenter.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

	}
	
	public interface Display {
		
		TreePanel<BaseTreeModel> getTree();
		Widget asWidget();
	}

	public void onTreeItemSelected(TreeItem item) {
		// TODO Auto-generated method stub
		GWT.log("fired Event: " + item.getText());
		this.eventBus.fireEvent(new NavigationEvent(item.getText()));
	}

	public void onTreeItemStateChanged(TreeItem item) {
		// TODO Auto-generated method stub
		
	}

	public void handleEvent(TreePanelEvent<BaseTreeModel> be) {
		// TODO Auto-generated method stub
		String item = be.getNode().getModel().get("name");
		String parent = null;
		TreePanel.TreeNode parentNode = be.getNode().getParent();
		if(parentNode != null) {
			parent = parentNode.getModel().get("name");
		}  
		GWT.log("got: "+ item + "\tparent: " + parent);
		this.eventBus.fireEvent(new NavigationEvent(item,parent));
		
	}

	
}
