/**
 * 
 */
package com.mts.nrtrde.client.view;


import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.mts.nrtrde.client.NRTRDE;
import com.mts.nrtrde.client.presenter.TreePresenter;

/**
 * @author Oded Nissan
 *
 */
public class TreeView extends LayoutContainer implements  TreePresenter.Display  {

	private TreePanel<BaseTreeModel>menuTree;
	private TreePresenter presenter;
	TreeStore<BaseTreeModel> store; 
	 
	public TreeView()
	{
		 this.store = new TreeStore<BaseTreeModel>();
		 this.menuTree = new TreePanel<BaseTreeModel>(store);
		
	}
	
	 private TreePanel<BaseTreeModel> createTree() {
		  

		 BaseTreeModel nrtrde = new BaseTreeModel();
		 nrtrde.set("name",NRTRDE.constants.nrtrde());
		 
		 
		 BaseTreeModel generate = new BaseTreeModel();
		 generate.set("name",NRTRDE.constants.generation());		 
		 nrtrde.add(generate);
		 
		 BaseTreeModel collect = new BaseTreeModel();
		 collect.set("name",NRTRDE.constants.collect());
		 nrtrde.add(collect);
		 

		 BaseTreeModel params = new BaseTreeModel();
		 params.set("name",NRTRDE.constants.params());
		 nrtrde.add(params);
	
		 BaseTreeModel alerts = new BaseTreeModel();
		 alerts.set("name", NRTRDE.constants.alerts());
		 nrtrde.add(alerts);
		 
		 
		 BaseTreeModel fileDeliver = new BaseTreeModel();
		 fileDeliver.set("name",NRTRDE.constants.fileDeliveryReport());
		 BaseTreeModel errorReport = new BaseTreeModel();		 
		 errorReport.set("name",NRTRDE.constants.errorReport());
		 
		 nrtrde.add(fileDeliver);
		 nrtrde.add(errorReport);
		 ArrayList<BaseTreeModel> modelList = new ArrayList<BaseTreeModel>();
		 modelList.add(nrtrde);
		 
		
		 store.add(modelList, true);
		 
		
		 menuTree.setDisplayProperty("name");
		 AbstractImagePrototype leaf = AbstractImagePrototype.create(NRTRDE.images.leaf());
		 menuTree.getStyle().setLeafIcon(leaf);
		 AbstractImagePrototype folder = AbstractImagePrototype.create(NRTRDE.images.folder());
		 menuTree.getStyle().setNodeCloseIcon(folder);
		 AbstractImagePrototype folderOpen = AbstractImagePrototype.create(NRTRDE.images.folderopen());
		 menuTree.getStyle().setNodeOpenIcon(folderOpen);
		
		return(menuTree); 
		 
		 
		 
	 }


	public TreePanel<BaseTreeModel> getTree() {
		// TODO Auto-generated method stub
		return this.menuTree;
	}

	@Override
	protected void onRender(Element parent, int index) {
		// TODO Auto-generated method stub
		super.onRender(parent, index);
		menuTree = createTree();
		menuTree.setSize("100%", "50%");
		add(menuTree);
		addStyleName("tree-panel");
		setSize("100%", "99%");
		layout();
	 	
	}

		
	 
	 
}
