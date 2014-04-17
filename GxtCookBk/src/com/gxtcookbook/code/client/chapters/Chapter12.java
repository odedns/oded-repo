package com.gxtcookbook.code.client.chapters;

import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.themes.client.Access;
import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.gxtcookbook.code.client.GxtCookBk;

public class Chapter12 extends ChapterAdapter {
	
	private static Chapter12 instance;
	private static LayoutContainer centerPanel = GxtCookBk.getAppCenterPanel();
	
	public static Chapter12 get(){
		instance = (instance == null ? new Chapter12() : instance);
		return instance;
	}
	
	protected Chapter12(){
		super();
	}

	@Override
	protected String getTitle() {
		return "Themeing & Custom Styling";
	}

	@Override
	protected List<Recipe> applyTheseRecipes() {
		List<Recipe> recipes = new LinkedList<Recipe>();
		
		Recipe setDefault = new Recipe("Setting A Default Theme") {
			
			@Override
			public void onApply() {
				GXT.setDefaultTheme(Theme.GRAY, true);
			}
		};
		recipes.add(setDefault);
		
		Recipe regtheme = new Recipe("Registering & Using A Theme") {
			
			@Override
			public void onApply() {
				ThemeManager.register(Slate.SLATE);
				ThemeManager.register(Access.ACCESS);
				GXT.setDefaultTheme(Slate.SLATE, true);
			
			
			}
		};
		recipes.add(regtheme);
		
		Recipe themeSwitchr = new Recipe("Switching Themes At Runtime") {
			
			@Override
			public void onApply() {
				// Although not needed but
				// if you ever call GXT.setDefaultTheme()
				// when you intend to switch themes
				// later with the ThemeSelector, then
				// make sure the GXT.setDefaultTheme() call
				// is given false as second parameter
				// because true as second parameter will
				// force the theme specified as first
				// parameter to be enabled even when the app
				// is reloading after a selection has been
				// made with the ThemeSelector.
				/*ThemeManager.register(Access.ACCESS);
				ThemeManager.register(Slate.SLATE);
				GXT.setDefaultTheme(Slate.SLATE, false);*/
				
			    ThemeSelector selector = new ThemeSelector();
			    selector.setWidth(125);
			    
			    // Equivalent to 
			    // RootPanel.get().add(selector);
			    centerPanel.add(selector);
			}
		};
		recipes.add(themeSwitchr);
		
	
		
		return recipes;
	}

}
