package com.gxtcookbook.code.client.chapters;
import java.util.LinkedList;
import java.util.List;


import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.Style.ButtonScale;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.button.ButtonGroup;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.events.Action;
import com.gxtcookbook.code.client.ext.ActionButton;
import com.gxtcookbook.code.client.ext.ActionMenu;
import com.gxtcookbook.code.client.icons.Icons;



public class Chapter3 extends ChapterAdapter {
	
	private static Chapter3 instance;
	
	public static Chapter3 get(){
		instance = instance == null ? new Chapter3() : instance;
		return instance;
	}
	
	protected Chapter3(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Clickware - Buttons, ToolBars, & Menus";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe defaultBtns = new Recipe("Creating Buttons With Text & Icons"){
			@Override
			public void onApply() {				
				ButtonBar btnBar = new ButtonBar();
				Icons ICONS = GWT.create(Icons.class);
				
				// Text button
				Button textBtn = new Button("Btn Text");
				textBtn.setToolTip("This is a simple text button");
				btnBar.add(textBtn);
				
				// Icon button
				Button iconBtn = new Button();
				iconBtn.setIcon(AbstractImagePrototype.create(ICONS.people()));
				btnBar.add(iconBtn);
				
				// Text and Icon button
				Button mixedBtn = new Button("Mixed Btn", AbstractImagePrototype.create(ICONS.orgchart()));
				btnBar.add(mixedBtn);
				
				// Real world button
				SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						Info.display("Message", "Clicked - " + evt.getButton().getText());					
					}
				};
				
				Button realBtn = new Button("Home Btn", AbstractImagePrototype.create(ICONS.home()), listener);
				btnBar.add(realBtn);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ButtonBar to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(btnBar);
			}			
		};
		recipes.add(defaultBtns);
		
		Recipe alignBtns =  new Recipe("Aligning Buttons"){
			@Override
			public void onApply() {
				/*
				 * We create a content panel and size it.
				 */
				ContentPanel ctPanel = new ContentPanel(new FitLayout());
				ctPanel.setSize(450, 200);
				ctPanel.setFrame(true);
				/*
				 * remove the header from the ContentPanel
				 */
				ctPanel.setHeaderVisible(false);
				/*
				 * set the ContentPanel's background and margin using CSS.
				 */
				ctPanel.setStyleAttribute("marginBottom", "15px");
				ctPanel.setStyleAttribute("backgroundColor", "white");
				
				/*
				 * Create an inner container for the text.
				 */
				LayoutContainer inner = new LayoutContainer();  
				inner.setStyleAttribute("backgroundColor", "white");
				inner.addText("<h1>Align to left</h1>");
				inner.setBorders(true);
				/*
				 * add the inner container to the ContentPanel.
				 */
				ctPanel.add(inner);
				
				/*
				 * Add the buttons to the ContentPanel 
				 * and align them to the left.
				 */
				ctPanel.addButton(new Button("Ok"));
				ctPanel.addButton(new Button("Cancel"));
				ctPanel.setButtonAlign(HorizontalAlignment.LEFT);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(ctPanel);
				

				/*
				 * We create a content panel and size it.
				 */
				ContentPanel ctPanel2 = new ContentPanel(new FitLayout());
				ctPanel2.setSize(450, 200);
				ctPanel2.setFrame(true);
				/*
				 * remove the header from the ContentPanel
				 */
				ctPanel2.setHeaderVisible(false);
				
				LayoutContainer inner2 = new LayoutContainer();
				/*
				 * set the ContentPanel's background and margin using CSS.
				 */
				inner2.setStyleAttribute("backgroundColor", "white");
				inner2.addText("<h1>Align to right</h1>");
				inner2.setBorders(true);
				
				/*
				 * add the inner container to the ContentPanel.
				 */
				ctPanel2.add(inner2);
				
				/*
				 * Add the buttons to the ContentPanel 
				 * and align them to the right.
				 */
				ctPanel2.addButton(new Button("Ok"));
				ctPanel2.addButton(new Button("Cancel"));
				ctPanel2.setButtonAlign(HorizontalAlignment.RIGHT);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(ctPanel2);
				
			}			
		};
		recipes.add(alignBtns);
		
		Recipe toggleBtns =  new Recipe("Creating On/Off Toggle Buttons"){
			@Override
			public void onApply() {
				
				/*
				 * We create a content panel and size it.
				 */
				final ContentPanel ctPanel = new ContentPanel(new FitLayout());
				ctPanel.setSize(450, 200);
				ctPanel.setFrame(true);
				ctPanel.setCollapsible(true);
				ctPanel.setHeaderVisible(false);
				/*
				 * set the ContentPanel's margin using CSS.
				 */
				ctPanel.setStyleAttribute("marginTop", "8px");
				
				final LayoutContainer innerPanel = new LayoutContainer();
				innerPanel.setBorders(true);
				innerPanel.setStyleAttribute("backgroundColor", "white");
				
				ctPanel.add(innerPanel);
				ctPanel.collapse();
				
				/*
				 * create a ButtonBar.
				 */
				ButtonBar buttonBar = new ButtonBar();
				/*
				 * create the ToggleButton
				 */
				ToggleButton toggleBtn = new ToggleButton("Turn On", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						ToggleButton btn = (ToggleButton) evt.getButton();
						String text = "Turn On";
						if(btn.isPressed()){
							text = "Turn Off";														
							ctPanel.expand();
						}else{
							innerPanel.addText("<h1>Switching Off ...</h1>");
							innerPanel.layout();
							new Timer(){
								@Override
								public void run() {
									innerPanel.removeAll();
									ctPanel.collapse();
								}
							}.schedule(1500);							
						}
						btn.setText(text);
					}
				});
				/*
				 * add the ToggleButton to the ButtonBar.
				 */
				buttonBar.add(toggleBtn);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ButtonBar and the ContentPanel to the main content panel. 
				 */				
				GxtCookBk.getAppCenterPanel().add(buttonBar);
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}			
		};
		recipes.add(toggleBtns);
		
		Recipe optionBtns =  new Recipe("Organizing Actions With Menu & Split Buttons"){
			@Override
			public void onApply() {
				// Give us icons
				Icons ICONS = GWT.create(Icons.class);
				
				// Listen for clicks on the menu items
				SelectionListener<MenuEvent> menuListener = new SelectionListener<MenuEvent>() {
					@Override
					public void componentSelected(MenuEvent evt) {
						MenuItem item = (MenuItem) evt.getItem();
						Info.display("Message", "You clicked - " + item.getText());			
					}
				};				
				
				// Setup the menu
				Menu btnMenu1 = new Menu();
				Menu btnMenu2 = new Menu();
				
				btnMenu1.add(new MenuItem("Home", AbstractImagePrototype.create(ICONS.home()), menuListener));
				btnMenu2.add(new MenuItem("Home", AbstractImagePrototype.create(ICONS.home()), menuListener));
				
				btnMenu1.add(new MenuItem("Clients", AbstractImagePrototype.create(ICONS.people()), menuListener));
				btnMenu2.add(new MenuItem("Clients", AbstractImagePrototype.create(ICONS.people()), menuListener));
				
				btnMenu1.add(new MenuItem("Reports", AbstractImagePrototype.create(ICONS.orgchart()), menuListener));
				btnMenu2.add(new MenuItem("Reports", AbstractImagePrototype.create(ICONS.orgchart()), menuListener));
				
				// Setup the buttons
				ButtonBar btnBar = new ButtonBar();
				Button menuBtn = new Button("Menu Button");
				menuBtn.setMenu(btnMenu1);
				menuBtn.setArrowAlign(ButtonArrowAlign.BOTTOM);
				btnBar.add(menuBtn);
				
				Button splitBtn = new SplitButton("Split Button", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						Info.display("Message", "This is the default action, click on the arrow to reveal others");
					}
				});
				splitBtn.setMenu(btnMenu2);
				splitBtn.setArrowAlign(ButtonArrowAlign.BOTTOM);
				btnBar.add(splitBtn);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ButtonBar to the main content panel. 
				 */
				GxtCookBk.getAppCenterPanel().add(btnBar);
			}			
		};
		recipes.add(optionBtns);
		
		Recipe toolbarBtns =  new Recipe("Building A Bar of 'Tools'"){
			@Override
			public void onApply() {						
				ToolBar tBar = new ToolBar();
				Icons ICONS = GWT.create(Icons.class);
				
				// Add the buttons				
				Button homeBtn = new Button("Home", AbstractImagePrototype.create(ICONS.home()));
				tBar.add(homeBtn);
				
				tBar.add(new SeparatorToolItem());
				Button clientsBtn = new Button("Clients", AbstractImagePrototype.create(ICONS.people()));
				tBar.add(clientsBtn);
				
				tBar.add(new FillToolItem());				
				Button reportsBtn = new Button("Reports", AbstractImagePrototype.create(ICONS.orgchart()));
				tBar.add(reportsBtn);
				
				// Build the container
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setSize(450, 200);
				ctPanel.setFrame(true);
				ctPanel.setHeaderVisible(false);
				
				LayoutContainer inner = new LayoutContainer();  
				inner.setStyleAttribute("backgroundColor", "white");
				inner.addText("<h1>Content Area</h1>");
				inner.setBorders(true);
				ctPanel.add(inner);
				ctPanel.setTopComponent(tBar);
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */		
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}			
		};
		recipes.add(toolbarBtns);
		
		Recipe btnGroups =  new Recipe("Crafting Multi-Column Buttons In ToolBar"){
		
			@Override
			public void onApply() {
				
				
				ToolBar tBar = new ToolBar();
				/*
				 * create a ButtonGroup with 3 rows.
				 */
				ButtonGroup group = new ButtonGroup(3);  
			    group.setHeading("File");
			    
			    /*
			     * add the ButtonGroup to the ToolBar
			     */
			    tBar.add(group);
			    
			    /*
			     * make a layout that will span 3 rows.
			     * This will be used to make the Open and New
			     * Buttons span 3 rows.
			     */
			    TableData data = new TableData();  
			    data.setRowspan(3);			    
			    
			    /*
			     * create the Open button 
			     * and add it to the group
			     */
			    Button openBtn = new Button("Open");
			    openBtn.setScale(ButtonScale.LARGE);  
			    openBtn.setIconAlign(IconAlign.TOP);  			    
			    openBtn.addStyleName("x-btn-as-arrow");
			    group.add(openBtn, data); 
			    
			   
			    /*
			     * create the New button 
			     * and add it to the group
			     */
			    Button createBtn = new Button("New");
			    createBtn.setScale(ButtonScale.LARGE);  
			    createBtn.setIconAlign(IconAlign.TOP);  
			    createBtn.setArrowAlign(ButtonArrowAlign.BOTTOM);
			    
			   /*
			    * create the menu and assign it to the New button.
			    */
			    Menu subMenu = new Menu();
				subMenu.add(new MenuItem("Gwt Module"));
				subMenu.add(new MenuItem("Gwt Project"));				
				subMenu.add(new MenuItem("Gwt RPC Service"));
				createBtn.setMenu(subMenu);
			    group.add(createBtn, data);
			    
			    /*
			     * add the icons.
			     */
			    Icons ICONS = GWT.create(Icons.class);
			    group.add(new Button("Home", AbstractImagePrototype.create(ICONS.home())));
			    group.add(new Button("Data", AbstractImagePrototype.create(ICONS.orgchart())));
			    group.add(new Button("Help", AbstractImagePrototype.create(ICONS.help())));
			    
			    /*
			     *  Build the container
			     */
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setSize(450, 250);
				ctPanel.setBorders(true);
				ctPanel.setHeading("Multi-Column Buttons");
				
				/*
				 * create an inner panel with some text.
				 */
				LayoutContainer inner = new LayoutContainer();  
				inner.setStyleAttribute("backgroundColor", "white");
				inner.addText("<h1>Content Area</h1>");
				ctPanel.add(inner);								
				ctPanel.setTopComponent(tBar);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */		
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}			
		};
		recipes.add(btnGroups);
		
		Recipe menuBar =  new Recipe("Navigating With Plain ol' Menus"){
			@Override
			public void onApply() {
				// Build the container
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setSize(450, 200);
				ctPanel.setBorders(true);
				ctPanel.setHeading("Plain ol' Menus");
				
				LayoutContainer inner = new LayoutContainer();  
				inner.setStyleAttribute("backgroundColor", "white");
				inner.addText("<h1>Content Area</h1>");
				ctPanel.add(inner);
				
				// Add the menubar
				MenuBar mBar = new MenuBar();
				Icons ICONS = GWT.create(Icons.class);
				mBar.setStyleAttribute("borderTop", "none");
				ctPanel.setTopComponent(mBar);
				
				// setup the file menu
				Menu fileMenu = new Menu();								
				MenuItem gwtItem = new MenuItem("New");				
				fileMenu.add(gwtItem);				
				fileMenu.add(new MenuItem("Open"));
				fileMenu.add(new MenuItem("Save", AbstractImagePrototype.create(ICONS.disk())));
				fileMenu.add(new SeparatorMenuItem());
				
				// attach a sample listener
				MenuItem exitItem = new MenuItem("Exit", new SelectionListener<MenuEvent>() {
					@Override
					public void componentSelected(MenuEvent evt) {
						Info.display("Message", "Where are U exiting to, huh.");
					}
				});
				fileMenu.add(exitItem);
				
				MenuBarItem fileItem = new MenuBarItem("File", fileMenu);
				mBar.add(fileItem);
				
				// setup  a sub-menu within file menu
				Menu subMenu = new Menu();
				subMenu.add(new MenuItem("Gwt Module"));
				subMenu.add(new MenuItem("Gwt Project"));				
				subMenu.add(new MenuItem("Gwt RPC Service"));
				gwtItem.setSubMenu(subMenu);				
				
				// setup the help menu
				Menu helpMenu = new Menu();				
				helpMenu.add(new MenuItem("Search"));
				helpMenu.add(new MenuItem("About Gxt"));
				helpMenu.add(new SeparatorMenuItem());
				helpMenu.add(new MenuItem("Help Contents", AbstractImagePrototype.create(ICONS.help())));
				MenuBarItem helpItem = new MenuBarItem("Help", helpMenu);
				mBar.add(helpItem);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */		
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}			
		};
		recipes.add(menuBar);	
		
		Recipe actions = new Recipe("Binding A Single Action To Several Click-wares"){
			@SuppressWarnings("serial")
			@Override
			public void onApply() {
				ToolBar tBar = new ToolBar();
				ActionMenu ctxMenu = new ActionMenu();
				Icons ICONS = GWT.create(Icons.class);
				
				// Setup the 'actions'
				Action homeActn = new Action("Home", AbstractImagePrototype.create(ICONS.home())) {					
					@Override
					public void actionPerformed() {
						Info.display("Message", "The 'Home' Action");					
					}
				};
				
				Action clientsActn = new Action("Clients", AbstractImagePrototype.create(ICONS.people())) {					
					@Override
					public void actionPerformed() {
						Info.display("Message", "The 'Clients' Action");
					}
				};								
				
				Action reportsActn = new Action("Reports", AbstractImagePrototype.create(ICONS.orgchart())) {					
					@Override
					public void actionPerformed() {
						Info.display("Message", "The 'Reports' Action");					
					}
				};
				
				// Add the buttons
				Button homeBtn = new ActionButton(homeActn);
				tBar.add(homeBtn);
				ctxMenu.add(homeActn);
				
				tBar.add(new SeparatorToolItem());
				Button clientsBtn = new ActionButton(clientsActn);				
				tBar.add(clientsBtn);
				ctxMenu.add(clientsActn);
				
				tBar.add(new FillToolItem());				
				Button reportsBtn = new ActionButton(reportsActn);
				tBar.add(reportsBtn);
				ctxMenu.add(reportsActn);
				
				// Bind widgets
				ContentPanel ctPanel = new ContentPanel();
				ctPanel.setSize(450, 200);
				ctPanel.setFrame(true);								
				ctPanel.setHeaderVisible(false);
				
				LayoutContainer inner = new LayoutContainer();  
				inner.setStyleAttribute("backgroundColor", "white");
				inner.addText("<h1>Content Area</h1>");
				inner.setBorders(true);
				ctPanel.add(inner);
						
				ctPanel.setTopComponent(tBar);
				inner.setContextMenu(ctxMenu);
				
				// some tests!
				clientsBtn.setText("");
				reportsActn.setEnabled(false);
				
				/*
				 * GxtCookbk is the application's entry point class.
				 * We access its main content panel using the 
				 * static GxtCookBk.getAppCenterPanel() call.
				 * We add the ContentPanel to the main content panel. 
				 */		
				GxtCookBk.getAppCenterPanel().add(ctPanel);
			}			
		};
		recipes.add(actions);
		
		return recipes;
	}

}
