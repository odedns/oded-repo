package com.gxtcookbook.code.client.chapters;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.WindowManager;
import com.gxtcookbook.code.client.GxtCookBk;

public abstract class Recipe extends LayoutContainer {
			
	private Chapter chapter;
	private String recipeTitle;
	private LayoutContainer appCenter;
	
	public Recipe(String title){
		super();	
		
		chapter = null;
		recipeTitle = title;
		appCenter = GxtCookBk.getAppCenterPanel();
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}
	
	public void setChapter(Chapter chapter){
		this.chapter = chapter;
	}

	public Chapter getChapter() {
		return chapter;
	}
	
	public void apply() {
		if(appCenter.getItemCount() >= 1){
			appCenter.removeAll();
			appCenter.layout();
		}
		WindowManager.get().hideAll();
		
		onApply();
		afterApply();		
	}
	
	protected void afterApply() {
		appCenter.layout();
	}
	
	public abstract void onApply();

}
