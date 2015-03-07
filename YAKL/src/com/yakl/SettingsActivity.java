/**
 * 
 */
package com.yakl;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * @author Oded Nissan
 *
 */
public class SettingsActivity extends TabActivity {
	private final String TAG = "YAKL";
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Log.d(TAG, "in SettingsActivity");
	    setContentView(R.layout.tabs);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, YAKLPrefsActivity.class);
	    spec = tabHost.newTabSpec("settings").setIndicator("Settings",
	                      null)
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, PrefsActivity.class);
	    spec = tabHost.newTabSpec("test").setIndicator("Test",
	                      null)
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    
	    intent = new Intent().setClass(this, AboutActivity.class);
	    spec = tabHost.newTabSpec("about").setIndicator("About",
                null)
            .setContent(intent);
	    tabHost.addTab(spec);
	    
	    
	}	
	
}
