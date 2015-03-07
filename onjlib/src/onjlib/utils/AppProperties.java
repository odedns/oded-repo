package onjlib.utils;

import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * This class stores properties objects in a hash table,
 * mapped by name.
 * The properties related to a specific properties file
 * can be retrieved from the table by the property name.
 * This class enables the application to access more than one
 * property file at any point in the application, without reloading
 * the property files or searching for them.
 *  @author <a href="mailto:odedns@yahoo.com">Oded Nissan</a>
 */

public class AppProperties {
	static AppProperties m_appProps = null;

	/**
	 * Hashtable to store the property object and
	 * the property name.
	 */
	static Hashtable m_propTable 	= null;

	/**
	 * virtual constructor, return an instance of the class object.
	 * @return AppProperties.
	 */
	public static AppProperties getInstance()
	{
		if(null == m_appProps) {
			m_propTable = new Hashtable();
			m_appProps = new AppProperties();
		}
		return(m_appProps);
	}

	/**
	 * loads a property file and stores the properties in
	 * the hashtable.
	 * @param name the key under which the properties will be stored
	 * in the hastable.
	 * @param fName the filename the properties will be loaded from.
	 * @exception IOException.
	 */
	public void addProperties(String name,String fName)
		throws IOException
	{

		Properties props = new Properties();
		props.load(new FileInputStream(fName));
		m_propTable.put(name,props);
	}

	/**
	 * Retrieve properties from the properties hashtable
	 * by name.
	 * @param propsName the name assigned to the properties object.
	 * @return Properties the properties object associated with the name.
	 */
	public Properties getProperties(String propsName)
	{
		return((Properties) m_propTable.get(propsName));
	}


	/**
	 * Retrieve a token value from a properties object in
	 * the properties hashtable.
	 * @param propsName the name assigned to the properties object.
	 * @param key the properties key.
	 * @return String the value associated with key in the properties.
	 */
	public String getProperty(String propsName,String key)
	{
		Properties props = getProperties(propsName);
		return(props.getProperty(key));
	}

	/**
	 * Retrieve a token value from a properties object in
	 * the properties hashtable.
	 * @param propsName the name assigned to the properties object.
	 * @param key the properties key.
	 * @param defValue the default value to return in case a property for
	 * key is not found in the properties object.
	 * @return String the value associated with key in the properties.
	 */
	public String getProperty(String propsName,String key, String defValue)
	{
		Properties props = getProperties(propsName);
		return(props.getProperty(key,defValue));
	}

	public static void main(String argv[])
	{

		AppProperties ap = AppProperties.getInstance();
		try {
			ap.addProperties("server",
					"../dtv05/properties/server.ini");
			ap.addProperties("adsftp", "../dtv05/properties/adsftp.ini");
		} catch(Exception e) {
			e.printStackTrace();
		}
		Properties props = ap.getProperties("server");
		System.out.println("props = " + props.toString());
		props = ap.getProperties("adsftp");
		System.out.println("props = " + props.toString());
	}


}

