package com.gxtcookbook.code.client;

import java.util.LinkedHashMap;
import java.util.Map;

import com.extjs.gxt.themes.client.Access;
import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gxtcookbook.code.client.chapters.Chapter11;
import com.gxtcookbook.code.client.chapters.Chapter12;
import com.gxtcookbook.code.client.chapters.Chapter9;
import com.gxtcookbook.code.client.chapters.Chapter8;
import com.gxtcookbook.code.client.chapters.Chapter5;
import com.gxtcookbook.code.client.chapters.Chapter4;
import com.gxtcookbook.code.client.chapters.Chapter1;
import com.gxtcookbook.code.client.chapters.Chapter7;
import com.gxtcookbook.code.client.chapters.Chapter6;
import com.gxtcookbook.code.client.chapters.Chapter10;
import com.gxtcookbook.code.client.chapters.Chapter3;
import com.gxtcookbook.code.client.chapters.Chapter2;
import com.gxtcookbook.code.client.chapters.Recipe;
import com.gxtcookbook.code.client.events.Events;
import com.gxtcookbook.code.client.ext.NavItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GxtCookBk implements EntryPoint {
	
	
	
	private ToolBar tBar;		
	public static TreePanel<ModelData> navTree;
	public static Map<String, Recipe> recipeMap = new LinkedHashMap<String, Recipe>();
	
	private static LayoutContainer appCenter = new LayoutContainer();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {					
		ThemeManager.register(Slate.SLATE);
		ThemeManager.register(Access.ACCESS);	
	//	GXT.setDefaultTheme(Theme.BLUE, false);
	
		GWT.log("onModuleLoad");
		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
				
		NavItem root = new NavItem(); 
		TreeStore<ModelData> navTreeStore = new TreeStore<ModelData>();		
		
		navTree = new TreePanel<ModelData>(navTreeStore);
		navTree.setWidth(300);
		navTree.setDisplayProperty("name");		
				
		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST);
		westData.setSize(320);
		westData.setMinSize(250);
		westData.setMaxSize(400);
		westData.setSplit(true);
		westData.setCollapsible(true);		
		westData.setMargins(new Margins(0, 5, 5, 0));
		
		ContentPanel westPanel = new ContentPanel(new FitLayout());
		westPanel.setHeaderVisible(false);
		westPanel.add(navTree);
		
		tBar = new ToolBar();
		westPanel.setBottomComponent(tBar);		
		viewport.add(westPanel, westData);

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(10, 5, 5, 0));
		appCenter.setStyleName("x-dasboard");
		appCenter.setStyleAttribute("backgroundColor", "white");
		appCenter.setStyleAttribute("padding", "8px");
		viewport.add(appCenter, centerData);				

		new QuickTip(viewport);
		RootPanel.get().add(viewport);
	
		
		cookChapters(root);
		navTreeStore.add(root.getChildren(), true);
		navTree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			@Override
			public void handleEvent(TreePanelEvent<ModelData> evt) {
				Events.getBus().fireEvent(Events.NavClick, evt);
			}
		});
		
		Events.getBus().addListener(Events.NavClick, new Listener<TreePanelEvent<ModelData>>(){
			@Override
			public void handleEvent(TreePanelEvent<ModelData> evt) {
				if (evt.getNode() != null && evt.getNode().isLeaf()) {
					doNavClick(evt);
				}
			}
		});
	}
	
	public static LayoutContainer getAppCenterPanel() {
		return appCenter;
	}

	private void doNavClick(TreePanelEvent<ModelData> evt) {
		ModelData data = evt.getNode().getModel();
		String nodeKey = data.get("key").toString();
		recipeMap.get(nodeKey).apply();
	}

	private void cookChapters(NavItem root) {
		root.add(Chapter1.get().getToc());
		root.add(Chapter2.get().getToc());
		root.add(Chapter3.get().getToc());
		root.add(Chapter4.get().getToc());
		root.add(Chapter5.get().getToc());
		root.add(Chapter6.get().getToc());		
		root.add(Chapter7.get().getToc());
		root.add(Chapter8.get().getToc());
		root.add(Chapter9.get().getToc());
		root.add(Chapter10.get().getToc());
		root.add(Chapter11.get().getToc());
		root.add(Chapter12.get().getToc());
		
	}

	
}
