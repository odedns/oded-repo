/**
 * 
 */
package com.yakl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @author Oded Nissan
 *
 */
public class AboutActivity extends Activity {
	private final String TAG = "YAKL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.about);
			String keyword = getPreferences(MODE_PRIVATE).getString("keyword", "");
			Log.d(TAG,"from prefrences got keyword = " + keyword);

			
		  
		  // Read raw file into string and populate TextView
	        InputStream iFile = getResources().openRawResource(R.raw.about);
	        try {
	            TextView helpText = (TextView) findViewById(R.id.textView1);
	            String strFile = inputStreamToString(iFile);
	            helpText.setMovementMethod(ScrollingMovementMethod.getInstance());

	            helpText.setText(strFile);
	        } catch (Exception e) {
	            Log.e("YAKL", "InputStreamToString failure", e);
	        }
	    
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	 /**
     * Converts an input stream to a string
     * 
     * @param is
     *            The {@code InputStream} object to read from
     * @return A {@code String} object representing the string for of the input
     * @throws IOException
     *             Thrown on read failure from the input
     */
    public String inputStreamToString(InputStream is) throws IOException {
        StringBuffer sBuffer = new StringBuffer();
        DataInputStream dataIO = new DataInputStream(is);
        String strLine = null;
        while ((strLine = dataIO.readLine()) != null) {
            sBuffer.append(strLine + "\n");
        }
        dataIO.close();
        is.close();
        return sBuffer.toString();
    }

}
