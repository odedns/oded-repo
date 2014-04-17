package com.gxtcookbook.code.client.chapters;

import java.util.LinkedList;
import java.util.List;

import com.gxtcookbook.code.client.GxtCookBk;
import com.gxtcookbook.code.client.ext.NavItem;

public class Chapter {
	
	private String chapterTitle;
	private List<Recipe> recipes;
	private NavItem chapterHead;	
	
	private static int chapterKey = 0;
	
	public Chapter(){
		super();
		recipes = new LinkedList<Recipe>();
	}
	
	public Chapter(String title, List<Recipe> recipeList, NavItem nav){
		this();
				
		setChapterTitle(title);				
		addRecipes(recipeList);
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	protected void setChapterTitle(String title) {
		this.chapterTitle = title;
		chapterHead = new NavItem(title, title.toLowerCase());
	}

	public NavItem getToc() {
		return chapterHead;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}
		
	private void addRecipe(Recipe recipe){
		if(!recipes.contains(recipe)){
			recipe.setChapter(this);
			recipes.add(recipe);
			
			String key = ++chapterKey + "_recipe_" + recipes.size();
			chapterHead.add(new NavItem(recipe.getRecipeTitle(), key));	
			GxtCookBk.recipeMap.put(key, recipe);
		}
	}
	
	protected void addRecipes(List<Recipe> recipeList){
		for (Recipe recipe : recipeList) {
			addRecipe(recipe);
		}
	}

}
