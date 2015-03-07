/**
 * HttpInvoker.java
 * Mar 19, 2008
 */
package remoting;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;


/**
 * @author Odedn
 *
 */
public class HttpInvoker {

	private final static Logger log = Logger.getLogger(HttpInvoker.class);
	private static String url;
		
		
		public static RemoteResponse invokeServer(String className, String methodName,
							ArrayList<?> args) throws Exception
		{
			log.debug("got invoker invoking class=" + className + "\tmethod = " + methodName);
			RemoteRequest req = new RemoteRequest(className, methodName, args);
			URL url = new URL(HttpInvoker.url);
		    HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
	        urlconn.setDoOutput(true);
	        urlconn.setAllowUserInteraction(false);
	        urlconn.setUseCaches(false);
	        urlconn.setRequestMethod("POST");
	        urlconn.setRequestProperty ("Content-Type", "application/x-java-serialized-object");            
	        log.debug("connected ...");        
	        
	        ObjectOutputStream os = new ObjectOutputStream(urlconn.getOutputStream());
	        os.writeObject(req);
	        os.flush();
	        os.close();
	        
	  		ObjectInputStream is = new ObjectInputStream(urlconn.getInputStream());                               
	  		RemoteResponse resp = (RemoteResponse) is.readObject();
	   		is.close();	   		
	   		urlconn.disconnect();
	   		return(resp);
		}


		/**
		 * get the service url
		 * @return String the url
		 */
		public static String getUrl()
		{
			return url;
		}


		/**
		 * set the url.
		 * 
		 * @param url String the url
		 */
		public static void setUrl(String url)
		{
			HttpInvoker.url = url;
		}
}
