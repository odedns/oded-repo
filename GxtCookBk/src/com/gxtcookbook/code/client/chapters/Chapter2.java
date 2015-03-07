package com.gxtcookbook.code.client.chapters;

import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.events.Events;
import com.gxtcookbook.code.client.ext.BlinkTabItem;
import com.gxtcookbook.code.client.ext.WiseStripTabPanel;
import com.gxtcookbook.code.client.icons.Icons;

public class Chapter2 extends ChapterAdapter {
	
	private static Chapter2 instance;
	
	public static Chapter2 get(){
		instance = instance == null ? new Chapter2() : instance;
		return instance;
	}
	
	protected Chapter2(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Doing Hide n' Seek On Tabs";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> chapterTwoRecipes = new LinkedList<Recipe>();
		Recipe simpleTabs = new Recipe("Build Tabbed Content using TabPanel") {			
			@Override
			public void onApply() {												
				TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				//tabPanel.setCloseContextMenu(true);
				for(int i = 1; i <= 3; ++i){
					TabItem aTab = new TabItem("TabItem <b>" + i + "</b>");
				
					aTab.setClosable(true);
					aTab.add(new HtmlContainer("<h1>Tab " + i + "</h1>"));
					tabPanel.add(aTab);
				}
				Menu menu = new Menu();
				menu.setWidth(140);
				menu.add(new MenuItem("Add Tab"));
				tabPanel.setContextMenu(menu);
				menu = tabPanel.getContextMenu();
				if(menu == null) {
					GWT.log("menu is null");
				}
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tabPanel);

				
			
				
			}
		};
		chapterTwoRecipes.add(simpleTabs);
		
		
		
		
		Recipe customIconTabs = new Recipe("Build Tabbed Content With Custom Tab Icons") {			
			@Override
			public void onApply() {												
				TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setCloseContextMenu(true);
				
				
				/*
				 * Our Icons interface extends ImageBundle and declares three methods, 
				 * each named with the exact name of an image placed in the same package 
				 * as the Icons interface. Having created the interface,
				 * preferably in its own java file, 
				 * we proceed to used it with tabs and everywhere 
				 * 	else AbstractImagePrototype icons are used in GXT.
				 * 
				 */
				Icons ICONS = GWT.create(Icons.class);
				
				String title = "DashBoard";
				TabItem homeTab = new TabItem(title);
				homeTab.setIcon(AbstractImagePrototype.create(ICONS.home()));
				homeTab.getHeader().setToolTip("Our " + title);
				homeTab.add(new HtmlContainer("<h1>Dashboard Tab</h1>"));
				tabPanel.add(homeTab);
				
				title = "Valued Customers";
				TabItem customersTab = new TabItem(title);
				customersTab.setIcon(AbstractImagePrototype.create(ICONS.people()));
				customersTab.getHeader().setToolTip("Our Really " + title);
				customersTab.setClosable(true);
				customersTab.add(new HtmlContainer("<h1>Customers Tab</h1>"));
				tabPanel.add(customersTab);
				
				title = "Data Reports";
				TabItem reportsTab = new TabItem(title);
				reportsTab.setIcon(AbstractImagePrototype.create(ICONS.orgchart()));
				reportsTab.getHeader().setToolTip("The customer " + title);
				reportsTab.setClosable(true);
				reportsTab.add(new HtmlContainer("<h1>Reports Tab</h1>"));
				tabPanel.add(reportsTab);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				
			}
		};
		chapterTwoRecipes.add(customIconTabs);
		
		Recipe bottomTabs = new Recipe("Creating Bottom Navigation Tabs") {			
			@Override
			public void onApply() {
				TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setCloseContextMenu(true);
				
				// the magic line	
				tabPanel.setTabPosition(TabPanel.TabPosition.BOTTOM);
				
				for(int i = 1; i <= 5; ++i){
					TabItem aTab = new TabItem("TabItem <b>" + i + "</b>");
					aTab.setClosable(true);
					aTab.add(new HtmlContainer("<h1>Tab " + i + "</h1>"));
					tabPanel.add(aTab);
				}	
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tabPanel);
			}
		};
		chapterTwoRecipes.add(bottomTabs);
		
		Recipe scrollableTabs = new Recipe("Creating TabPanels With Scrollable Tabstrip") {			
			@Override
			public void onApply() {
				final TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setTabScroll(true);
				tabPanel.setAnimScroll(true);
				tabPanel.setCloseContextMenu(true);	
				
				for(int i = 1; i <= 10; ++i){
					TabItem aTab = new TabItem("TabItem <b>" + i + "</b>");
					aTab.setClosable(true);
					aTab.add(new HtmlContainer("<h1>Tab " + i + "</h1>"));
					tabPanel.add(aTab);
				}
				
				ButtonBar buttonBar = new ButtonBar();
				buttonBar.add(new ToggleButton("Scroll To Last Tab", new SelectionListener<ButtonEvent>(){
					@Override
					public void componentSelected(ButtonEvent evt) {																		
						int index = 0;
						String title = "Scroll To Last Tab";
						ToggleButton btn = (ToggleButton) evt.getButton();
						if(btn.isPressed()){
							index = tabPanel.getItemCount()-1;
							title = "Scroll To First Tab";
						} else {
							 title = "Scroll To Last Tab";
							 index = 0;
						}
						
						TabItem target = tabPanel.getItem(index);
						if(tabPanel.getSelectedItem() != target){
							tabPanel.scrollToTab(target, true);
							tabPanel.setSelection(target);
							btn.setText(title);
						}						
					}					
				}));				
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel and the buttonBar to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				GxtCookBk.getAppCenterPanel().add(buttonBar);
			}
		};
		chapterTwoRecipes.add(scrollableTabs);
		
		Recipe addRemoveTabs = new Recipe("Programmatically Add/Remove A Tab") {			
			@Override
			public void onApply() {				
				
				final TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setCloseContextMenu(true);
				
				TabItem homeTab = new TabItem("DashBoard");
				homeTab.setClosable(true);
				tabPanel.add(homeTab);
				
				ButtonBar btnBar = new ButtonBar();
				btnBar.add(new Button("Add Tab", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						TabItem tab = new TabItem("TabItem " + (tabPanel.getItemCount()+1));
						tab.setClosable(true);
						tabPanel.add(tab);		
					}
				}));
				btnBar.add(new Button("Remove Tab", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						TabItem tab = tabPanel.getSelectedItem();
						if(tab.isClosable()){
							tabPanel.remove(tab);
						}
						
					}
				}));
				
				tabPanel.addListener(Events.BeforeRemove, new Listener<TabPanelEvent>() {
					@Override
					public void handleEvent(TabPanelEvent evt) {
						if(evt.getItem().getTabPanel().getItemCount() == 1){
							evt.setCancelled(true);
							MessageBox.alert("Error", "But there's only one tab left, Y remove it", null);
						}
					}
					
				});
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel and the buttonBar to the main content panel. 
				 */			
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				GxtCookBk.getAppCenterPanel().add(btnBar);
			}
		};
		chapterTwoRecipes.add(addRemoveTabs);
		
		Recipe tabNotify = new Recipe("Tab Notification") {			// Plugin Candidate	
			@Override
			public void onApply() {
				final TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);				
				tabPanel.setCloseContextMenu(true);				
				
				TabItem aTab;
				for(int i = 1; i <= 3; ++i){
					aTab = new BlinkTabItem("TabItem <b>" + i + "</b>");
					aTab.setClosable(true);
					aTab.add(new HtmlContainer("<h1>Tab " + i + "</h1>"));
					tabPanel.add(aTab);
				}				
				
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				Timer wait = new Timer() {					
					@Override
					public void run() {
						BlinkTabItem tab = (BlinkTabItem) tabPanel.getItem(2);
						tab.startBlinking();
					}
				};
				wait.schedule(2000);
			}
		};
		chapterTwoRecipes.add(tabNotify);
		
		Recipe searchForTab = new Recipe("How To Search, Locate, & Select A Particular Tab") {			
			@Override
			public void onApply() {
				final TabPanel tabPanel = new TabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setCloseContextMenu(true);	
				
				for(int i = 0; i < 5; ++i){
					TabItem aTab = new TabItem("TabItem <b>" + i + "</b>");
					aTab.setItemId("tab_" + i);
					aTab.setClosable(true);
					aTab.add(new HtmlContainer("<h1>Tab " + i + "</h1>"));
					tabPanel.add(aTab);
				}
										
				SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>(){
					@Override
					public void componentSelected(ButtonEvent evt) {
						String btnId = evt.getButton().getItemId();
						String tabId = "tab" + btnId.substring(btnId.indexOf("_"), btnId.length());
						TabItem result = tabPanel.findItem(tabId, true);
						if(result != null){
							if(result.equals(tabPanel.getSelectedItem())){
								Info.display("Message", "already selected");
							}else{
								tabPanel.setSelection(result);
							}
						}						
					}					
				};
				
				ButtonBar buttonBar = new ButtonBar();
				Button btn1 = new Button("Gimme tab_0", listener);
				btn1.setItemId("btn_0");
				buttonBar.add(btn1);
				
				Button btn2 = new Button("Gimme tab_3", listener);
				btn2.setItemId("btn_3");
				buttonBar.add(btn2);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel and the buttonBar to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				GxtCookBk.getAppCenterPanel().add(buttonBar);
			}
		};
		chapterTwoRecipes.add(searchForTab);
		
		Recipe stripOnTwoOrMoreTabs = new Recipe("Only Show Tabstrip For 2+ Tabs") {		// Plugin Candidate		
			@Override
			public void onApply() {
				final TabPanel tabPanel = new WiseStripTabPanel();
				tabPanel.setHeight(250);
				tabPanel.setWidth(450);
				tabPanel.setCloseContextMenu(true);
				
				TabItem homeTab = new TabItem("DashBoard");
				homeTab.add(new HtmlContainer("<h1>DashBoard Tab</h1>"));
				tabPanel.add(homeTab);
				
				ButtonBar btnBar = new ButtonBar();
				btnBar.add(new Button("Add Tab", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						int pos = tabPanel.getItemCount()+1;
						TabItem tab = new TabItem("TabItem " + pos);						
						tab.add(new HtmlContainer("<h1>Tab " + pos + "</h1>"));
						tab.setClosable(true);
						tabPanel.add(tab);		
					}
				}));
				btnBar.add(new Button("Remove Tab", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						TabItem tab = tabPanel.getSelectedItem();
						if(tab.isClosable()){
							tabPanel.remove(tab);
						}						
					}
				}));				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the tabPanel and the buttonBar to the main content panel. 
				 */			
				GxtCookBk.getAppCenterPanel().add(tabPanel);
				GxtCookBk.getAppCenterPanel().add(btnBar);
			}
		};
		chapterTwoRecipes.add(stripOnTwoOrMoreTabs);
		
		return chapterTwoRecipes;
	}

}
