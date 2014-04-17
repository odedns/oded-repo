/**
 * 
 */
package com.yakl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Oded Nissan
 *
 */
public class LocationService extends Service {
	private final String TAG = "YAKL";
	
	
	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this,"LocationService created", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this,"LocationService destroyed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Toast.makeText(this,"LocationService started", Toast.LENGTH_LONG).show();
			
		final String address = intent.getExtras().getString("address");
		sendSMS(address, "YAKL Received your message and is trying to locate the phone for you.");
		
		Log.d(TAG,"LocationTask running address = " + address);
		final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		/**
		 * try to enable GPS
		 */
		try {
			  Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
		} catch (Exception e) {
			  Log.e(TAG, e.getMessage());
		}

		
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		     	double latitude = location.getLatitude();
		    	double longitude = location.getLongitude();
		    	StringBuffer sb = new StringBuffer();
		    	sb.append(Double.toString(latitude));
		    	sb.append(',');
		    	sb.append(Double.toString(longitude));
		    	sb.append("\nhttp://maps.google.com/maps?q=");
		    	sb.append(Double.toString(latitude));
		    	sb.append(',');
		    	sb.append(Double.toString(longitude));
		    	sb.append("&hl=en\n");
		    	Log.i(TAG,"location sms: " + sb.toString());
		    	// maps url
				// http://maps.google.com/maps?q=37.422005,-122.084095&hl=he
		    	Log.d(TAG,"sending sms to "+ address);
		    	sendSMS(address, "Here is location info:\n" + sb.toString());
		    	locationManager.removeUpdates(this);
		    	
		    	
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
		try {
			playSound();
			
		} catch(Exception e) {
			Log.e(TAG,e.getMessage());
		}
			
		
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	 private void sendSMS(String phoneNumber, String message)   {        
		 
		 	Toast msg = Toast.makeText(this, "YAKL Sending SMS to: " + phoneNumber, Toast.LENGTH_LONG);
		 	msg.show();
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, message, null, null);        
	        
	  }
	 
	 


	private void playSound() throws IllegalArgumentException, IllegalStateException, IOException, URISyntaxException
	{
		
		String strRingtonePreference = PreferenceManager.getDefaultSharedPreferences(this).getString("ringtone", "DEFAULT_RINGTONE_URI");
		String t = PreferenceManager.getDefaultSharedPreferences(this).getString("ringTime", "10");
		final int playDuration = Integer.parseInt(t);
		Log.d(TAG,"playing sound: " + strRingtonePreference + "\t ringTime=" + playDuration);
		final MediaPlayer player = new MediaPlayer();
		Uri uri = Uri.parse(strRingtonePreference);
		 
		player.setDataSource(this,uri);
		player.prepare();
		player.setLooping(true);
		player.start();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				player.stop();
				Log.d(TAG,"Stopping player");
			}
		},playDuration * 1000 );
		
	}

	}
