/**
 * 
 */
package com.yakl;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * @author Oded Nissan
 *
 */
public class SMSReceiver extends BroadcastReceiver {
	private final String TAG = "YAKL";
	private boolean serviceStarted = false;
	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Bundle bundle = intent.getExtras();
		
		String keyword = PreferenceManager.getDefaultSharedPreferences(context).getString("keyword", "");
		this.serviceStarted  = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("startListener", false);
		Log.d(TAG,"from prefrences got keyword = " + keyword);
		Log.d(TAG,"from prefrences got startListener = " + serviceStarted);
		if(this.serviceStarted) {
			Log.d(TAG,"Checking messages");
			Object messages[] = (Object[]) bundle.get("pdus");
			SmsMessage smsMessage[] = new SmsMessage[messages.length];
			for(int i = 0; i < messages.length; i++) {
				smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
				String txt = smsMessage[i].getMessageBody();
				if(txt.equalsIgnoreCase(keyword)) {
					processMsg(context,smsMessage[i]);
					break;
				}
			}
			Log.d(TAG,"SMSReceiver got message: " + smsMessage[0].getMessageBody());
		}
		
		
		
		
	}
	
	
	private void processMsg(Context context,SmsMessage msg)
	{
		String address = msg.getOriginatingAddress();
		
		Log.d(TAG,"processMsg : " + msg.getMessageBody());
		Log.d(TAG,"Address : " + address);
	
		Intent intent = new Intent(context,LocationService.class);
		intent.putExtra("address", address);
			//context.startService(intent);
		AlarmManager mgr=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent pi = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			
			
			
		long now = SystemClock.elapsedRealtime();
		/*
		 * schedule just one run.
		 */
		mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, now, pi);
			
		Log.d(TAG,"AlarmManager scheduled");
			
	}
	

}
