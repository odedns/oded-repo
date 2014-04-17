/**
 * 
 */
package com.yakl;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * @author Oded Nissan
 *
 */
public class YAKLPrefsActivity extends PreferenceActivity {
	private final String TAG = "YAKL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.prefrences);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();	
		process();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		process();
	}
	
	
	
	private void process()
	{
	
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		boolean startListener = prefs.getBoolean("startListener", false);
		String locateString = prefs.getString("keyword", "");
		Log.d(TAG,"startListener = " + startListener);
		Log.d(TAG,"SMS keywork = " + locateString);
	}

}
