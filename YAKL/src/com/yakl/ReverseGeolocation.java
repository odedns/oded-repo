/**
 * 
 */
package com.yakl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Oded Nissan
 *
 */
public class ReverseGeolocation {
	
	private static final String urlPrefix = "http://api.geonames.org/findNearbyStreetsOSMJSON?";
	
	
	public static String getReverseGeolocation(double lat, double lng) throws ClientProtocolException, IOException
	{
		
		
		StringBuffer sb = new StringBuffer(urlPrefix);
		sb.append("lat=");
		sb.append(Double.toHexString(lat));
		sb.append("&lng=" + Double.toString(lng));
		sb.append("&user=demo");
		String url = sb.toString();
		System.out.println("url = " + url);
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        
        HttpResponse response = client.execute(request);
        InputStream is  = response.getEntity().getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb2 = new StringBuffer();
        String t;
        while(null != (t=br.readLine())) {
        	sb2.append(t);
        }
        System.out.println("sb2 = " + sb2.toString());

        return(sb2.toString());
        
	}
	
	
	
	public static void main(String args[]) {
		
			System.out.println("calling getReverseGeo...");
			try {
				String s = getReverseGeolocation(32.196368833334, 34.88385276666);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
