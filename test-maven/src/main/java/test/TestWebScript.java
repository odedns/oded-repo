/**
 * 
 */
package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author I070659
 *
 */
public class TestWebScript {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8080/alfresco/service/dbhost.json";
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try {
        	
        
        HttpResponse response = client.execute(request);
        
        InputStream is  = response.getEntity().getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = null;
        while(null != (s=br.readLine())) {
        	System.out.println("s = " +s);
        }
        br.close();
        is.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
	}

}
