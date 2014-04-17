package com.gxtcookbook.code.client.ext;

import com.extjs.gxt.ui.client.util.Theme;

public class Chrome extends Theme {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Theme CHROME = new Chrome(); 
	
	public Chrome() {
		super("chrome", "Chrome", "gxt/themes/chrome/css/xtheme-chrome.css");
	}
	
	public Chrome(String name){
		super("chrome", name, "gxt/themes/chrome/css/xtheme-chrome.css");
	}

}
