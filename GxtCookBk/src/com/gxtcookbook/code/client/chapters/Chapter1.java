package com.gxtcookbook.code.client.chapters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.WindowManager;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.gxtcookbook.code.client.GxtCookBk;

public class Chapter1 extends ChapterAdapter {
	
	private static Chapter1 instance;
	private static LayoutContainer centerPanel = GxtCookBk.getAppCenterPanel();
	
	public static Chapter1 get(){
		instance = instance == null ? new Chapter1() : instance;
		return instance;
	}
	
	protected Chapter1(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Playing With Panels & Windows";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe cookBasicWindows = new Recipe("Creating A Basic Window"){			
			@Override
			public void onApply() {
				// create and setup window
				Window basicWindow = new Window();
				basicWindow.setHeading("GXT CookBook | Recipe One");
				basicWindow.setClosable(true);
				basicWindow.setSize(250, 50);					
				
				// prepare content to show
				LayoutContainer textPanel = new VerticalPanel();
				textPanel.setStyleAttribute("padding", "15px");
				textPanel.addText("This is our first recipe from GXT Cookbok, how are we doing so far ...");
				
				// place content on the window
				// and display it.
				basicWindow.add(textPanel);				
				basicWindow.show();
			}
		};				
		recipes.add(cookBasicWindows);
		
		Recipe cookXWindow = new Recipe("Building Windows That Can Be Modal, Maximized, Resized & Dragged"){ // update docs
			@Override
			public void onApply() {
				// basic window setup
				Window xWindow = new Window();				
				xWindow.setHeading("GXT CookBook | Recipe Two");
				xWindow.setClosable(true);
				xWindow.setSize(350, 170);
				
				// add the 'x' features
				xWindow.setDraggable(true);
				// actually defaults to true
				xWindow.setResizable(true); 	
				xWindow.setMaximizable(true);				
				xWindow.setModal(true);
				xWindow.setBlinkModal(true);
				
				// constrain the maximize operation
				// such that when maximized the window
				// will expand to fill the box defined by
				// the dimensions of centerPanel instead
				// of the entire browser viewable area.
				// centerPanel is a standard GXT Panel
				xWindow.setContainer(centerPanel.getElement());
				
				// constrain drag actions to a specific
				// container (centerPanel, a standard GXT Panel)
				// instead of the browser's viewable area.
				// Thus U can't drag the window outside
				// the bounds of centerPanel.
				xWindow.getDraggable().setContainer(centerPanel);
				
				// prepare some content to show,
				// you've got to have something to show!
				LayoutContainer textPanel = new VerticalPanel();
				textPanel.setStyleAttribute("padding", "15px");
				StringBuilder msg = new StringBuilder();
				msg.append("This Window can do lots of stuff,");				
				msg.append("now we are no longer guessing or ");
				msg.append("bragging over 'defaults'. <p>You can ");
				msg.append("move the mouse over the corners to ");
				msg.append("reveal the resize handles, ");
				msg.append("moving the mouse over the title also ");				
				msg.append("reveals the 'move' / 'drag' handle.</p>");
				msg.append("<p>The window is 'modal' thus you can't interact ");
				msg.append("with the rest of the application until you close it, ");
				msg.append("if you try, the window will blink to ");
				msg.append("'remind' you of its apparent presence.</p>");				
				textPanel.addText(msg.toString());
				
				// attach the content to
				// the window and show it
				xWindow.add(textPanel);
				xWindow.show();				
			}			
		};
		recipes.add(cookXWindow);
		
		Recipe cookDialogWindows = new Recipe("Creating Dialog Windows"){
			@Override
			public void onApply() {
				Dialog dialog = new Dialog();							
				dialog.setBodyBorder(false);
				dialog.setClosable(false);
				dialog.setHideOnButtonClick(true);
				dialog.setButtons(Dialog.OKCANCEL);
				dialog.setScrollMode(Scroll.NONE); 
				dialog.setHeading("GXT CookBook : Recipe Three");
				dialog.addText("Dialogs are descendants of the Window class, so they are windows that can do even more.");
				dialog.show();
						
				/*
				 * A special Listener that can be used with buttons.
				 * When attached to a Button with the button's 
				 * addSelectionListener() and the button is clicked,
				 * the componentSelected() method of the listener
				 * gets called. Here we use the Info class to display
				 * a message in the bottom right region of the browser for a specified 
				 * few seconds.
				 */
				SelectionListener<ButtonEvent> listener = new SelectionListener<ButtonEvent>() {					
					@Override
					public void componentSelected(ButtonEvent evt) {
						String text = evt.getButton().getText();
						String format = "You clicked the {0} button";
						Info.display("Recipe Three", format, text);
					}
				};
				
				Button okBtn = dialog.getButtonById(Dialog.OK);
				okBtn.addSelectionListener(listener);
				
				Button cancelBtn = dialog.getButtonById(Dialog.CANCEL);
				cancelBtn.addSelectionListener(listener);
				
			}			
		};
		recipes.add(cookDialogWindows);
		
		Recipe cookMsgDialogs = new Recipe("Preempt The User With Messages"){
			@Override
			public void onApply() {
				// So we can handle your button clicks
				Listener<MessageBoxEvent> listener = new Listener<MessageBoxEvent>(){
					@Override
					public void handleEvent(MessageBoxEvent evt) {
						Button btn = evt.getButtonClicked();												
						Info.display("Recipe Four", "The '{0}' button was pressed", btn.getText());
						
						MessageBoxType msgBoxType = evt.getMessageBox().getType();
						if(msgBoxType != null && 
								(msgBoxType.equals(MessageBoxType.PROMPT) || 
										msgBoxType.equals(MessageBoxType.MULTIPROMPT))){
							Info.display("Recipe Four : Prompt", evt.getValue());
						}
					}
					
				};
				
				// Show alert message
				MessageBox.alert("Alert", "Invalid Login Credentials", listener);
				
				// Show confirm message
				MessageBox.confirm("Confirm", "Do you intend to logout", listener);
				
				// Show prompt message
				MessageBox.prompt("Prompt", "Please tell us your name 'promptly'", listener);
				
				// Show progress message
				final MessageBox pBar = MessageBox.progress("Progress", "Calculating your comprehension so far", "wait ...");
				pBar.getProgressBar().auto();
				Timer pBarTimer = new Timer(){
					@Override
					public void run() {
						pBar.close();
					}					
				};
				pBarTimer.schedule(5000);
			}			
		};
		recipes.add(cookMsgDialogs);
		
		Recipe windowMgmnt = new Recipe("Building A Window Management System"){
			@Override
			public void onApply() {	
				// set up some "global" variables
				final Menu toolMenu = new Menu();
				ButtonBar buttonBar = new ButtonBar();
				final WindowManager mgr = WindowManager.get();												
				final List<Window> windowList = new ArrayList<Window>();
				
				final WindowListener windowListener = new WindowListener(){
					@Override
					public void windowMinimize(WindowEvent we) {								
						final Window window = we.getWindow();
						
						// make a menu-item for this window,
						// but only once, so we'll search first
						boolean found = false;
						Iterator<Component> it = toolMenu.getItems().iterator();
						while (it.hasNext()) {
							Component cmp = (Component) it.next();
							if(cmp instanceof MenuItem){
								MenuItem item = (MenuItem) cmp;
								if(item.getText().equals(we.getWindow().getHeading())){
									found = true;
									break;
								}
							}										
						}
						
						if(!found){
							toolMenu.insert(new MenuItem(we.getWindow().getHeading(), new SelectionListener<MenuEvent>() {
								@Override
								public void componentSelected(MenuEvent ce) {
									if(!window.isVisible()){
										window.show();
									}
									mgr.bringToFront(window);
								}					
							}), 0);
						}								
						window.hide();
					}
				};
				
				// we'll use this to generate the windows
				Button addWindowBtn = new Button("Add Window", new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent evt) {
						int randInt = Random.nextInt(20);
						Window dummy = new Window();												
						dummy.setClosable(false);
						dummy.setSize(200, 120);
						dummy.setMinimizable(true);
						dummy.setMaximizable(true);
						dummy.setId("win_" + randInt);
						dummy.setHeading("Window " + randInt);
						dummy.setContainer(centerPanel.getElement());
						dummy.addWindowListener(windowListener);						
						
						dummy.show();
						windowList.add(dummy);						
					}					
				});
				buttonBar.add(addWindowBtn);				
				toolMenu.add(new SeparatorMenuItem());
				
				// add the menu-items to handle hide/show/cascade all				
				// hide-all is easy anyways
				toolMenu.add(new MenuItem("Hide All", new SelectionListener<MenuEvent>() {
					@Override
					public void componentSelected(MenuEvent evt) {
						mgr.hideAll();
					}					
				}));
			
				// show-all only works because we kept
				// a local list of the windows we've made
				toolMenu.add(new MenuItem("Show All", new SelectionListener<MenuEvent>() {
					@Override
					public void componentSelected(MenuEvent evt) {
						// mgr.getWindows() || mgr.getStack() returns only visible windows
						// so we always have an empty list after calling mgr.hideAll()
						for(Window window : windowList){
							if(window != null && !window.isVisible()){
								window.show();
							}
						}
					}					
				}));
				// cascade is tricky, yeah.
				// cascade is implemented by positioning
				// the windows atop each other, but 25x29 pixels
				// "more" from the last one
				toolMenu.add(new MenuItem("Cascade All", new SelectionListener<MenuEvent>() {
					@Override
					public void componentSelected(MenuEvent evt) {
						List<Window> windows = mgr.getWindows();
						Window reference = null;						
						for (Window window : windows) {							
							window.show();							
							mgr.bringToFront(window);
							window.center();							
							if(reference != null){
								window.setPosition(reference.getPosition(true).x + 25, reference.getPosition(true).y + 29);
							}							
							reference = window;
						}
					}					
				}));
				
				// create a menu button and attach the menu to it
				Button toolBtn = new Button("Window Tools");	// correct book from SplitButton to this
				toolBtn.setMenu(toolMenu);
				buttonBar.add(toolBtn);				
				centerPanel.add(buttonBar, new FlowData(10));
			}			
		};
		recipes.add(windowMgmnt);
		
		return recipes;
	}

}
