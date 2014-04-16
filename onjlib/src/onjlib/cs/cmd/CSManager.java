package onjlib.cs.cmd;

import java.net.*;
import java.io.*;

/**
 * @author Oded Nissan 01/03/2004
 *
 * The CSManager is a utility class that provides methods for 
 * client server communication.
 * The executeCommand method allows executing a server command on the server.
 */
public class CSManager {
	
	private static String m_url = null;
	
	
	/**
	 * cannot instantiate class.
	 */
	private CSManager()
	{
		
	}
	
	/**
	 * set the url of the command servlet.
	 * @param url a String containing the uri of the command servlet.
	 */
	public static void setUrl(String url)
	{
		m_url = url;	
	}
	
	/**
	 * get the url of the command servlet.
	 * @return String the url.
	 */
	public static String getUrl()
	{
		return(m_url);
	}
	
	
	/**
	 * execute a server command on the server.
	 * @param serverClassName the class name of the server command to execute.
	 * @param params the parameters to be passed to the server command.
	 * @throws Exception in case of error.
	 */
	public static CommandParams executeCommand(String serverClassName, CommandParams params)
		throws Exception
	{
        params.setCommandClassName(serverClassName) ;   
		URL url = new URL(m_url);
	    HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
        urlconn.setDoOutput(true);
        urlconn.setAllowUserInteraction(false);
        urlconn.setUseCaches(false);
        urlconn.setRequestMethod("POST");
        urlconn.setRequestProperty ("Content-Type", "application/x-java-serialized-object");            
        System.out.println("connected ...");        
        
        ObjectOutputStream os = new ObjectOutputStream(urlconn.getOutputStream());
        os.writeObject(params);
        os.flush();
        os.close();
        
  		ObjectInputStream is = new ObjectInputStream(urlconn.getInputStream());                               
  		CommandParams  outParams = (CommandParams) is.readObject();
   		is.close();	   		
   		urlconn.disconnect();
   		return(outParams);
	}

}
