/*
 * Created on 06/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author P0006439
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertyReader {

	
	/**
	 * read properties from a properties file in the classpath.
	 * @param fName the name of the file
	 * @return the Properties object read.
	 * @throws IOException in case of error.
	 */
	public static Properties read(String fName) throws IOException
	{			
		Properties props = new Properties();
		InputStream is = fName.getClass().getResourceAsStream(fName);
		if(is == null) {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			is = cl.getResourceAsStream(fName);
		}
		props.load(is);
		return(props);
	}
	
	
}
