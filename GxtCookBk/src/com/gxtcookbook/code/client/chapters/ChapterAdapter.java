package com.gxtcookbook.code.client.chapters;

import java.util.List;

public abstract class ChapterAdapter extends Chapter {
	
	public ChapterAdapter(){
		super();
		setChapterTitle(getTitle());
		addRecipes(applyTheseRecipes());
	}
	
	protected abstract String getTitle();
	protected abstract List<Recipe> applyTheseRecipes();

}
