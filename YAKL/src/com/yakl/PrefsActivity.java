/**
 * 
 */
package com.yakl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Oded Nissan
 *
 */
public class PrefsActivity extends Activity  implements LocationListener {
	private final String TAG = "YAKL";
	
	private LocationManager locationManager;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prefs);
		  
		  
		  
			    
		Button okButton = (Button) findViewById(R.id.okButton);
	    okButton.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG,"onclick pressed");
				PrefsActivity.this.dialog = ProgressDialog.show(PrefsActivity.this, "", "Locating... . Please wait...");
				// Acquire a reference to the system Location Manager
				testLocation();
				
			}
		});
	      
	      
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(this.locationManager != null) {
			this.locationManager.removeUpdates(this);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(this.locationManager != null) {
			this.locationManager.removeUpdates(this);
		}

	}
	
	
	/**
	 * save all options.	
	 */
	/*
	private void save()
	{
		
		final SharedPreferences prefs = getPreferences(MODE_WORLD_WRITEABLE);				
		Editor editor = prefs.edit();
		editor.putString("keyword", editText.getEditableText().toString());
		
		boolean startListener = startCheckbox.isChecked();
		editor.putBoolean("startListener",startListener );
		editor.commit();
		
		
	}
	*/

	/**
	 * test the getLocation functionality 
	 * print the location in the text view.
	 */
	private void testLocation()
	{
		this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	    // Called when a new location is found by the network location provider.
     	double latitude = location.getLatitude();
    	double longitude = location.getLongitude();
    	StringBuffer sb = new StringBuffer();
    	sb.append("http://maps.google.com/maps?q=");
    	sb.append(Double.toString(latitude));
    	sb.append(',');
    	sb.append(Double.toString(longitude));
    	sb.append("&hl=en");
    	Log.i(TAG,"location : " + sb.toString());
    
    	
    	TextView textView = (TextView) findViewById(R.id.textView2);
    	textView.setText(Html.fromHtml("<a href=\"" + sb.toString() + "\">" + sb.toString() + "</a>"));
    	textView.setMovementMethod(LinkMovementMethod.getInstance());
    	 
    	// maps url
		// http://maps.google.com/maps?q=37.422005,-122.084095&hl=he
    	locationManager.removeUpdates(this);
    	this.dialog.cancel();
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
