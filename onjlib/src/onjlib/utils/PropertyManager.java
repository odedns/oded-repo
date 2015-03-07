/*
 * Created on 01/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author Oded Nissan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PropertyManager {

	private static String m_configFile = "config.properties";
	private static final String PROPERTY_FILES = "property.files";
	private static PropertyManager m_pm = null;
	private Properties m_props;
	/**
	 * Cannot instantiate class
	 */
	private PropertyManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * set the name of the configuration file
	 * to read proeprty files from.
	 * @param configFile the name of the configuration file.
	 */
	public void setConfigFile(String configFile)
	{
		m_configFile = configFile;
	}
	
	/**
	 * initialize all properties by reading them into
	 * one big Propertyfile.
	 *
	 */
	public void init() throws IOException
	{
		Properties p = null;
		m_props = new Properties();
		Properties propFiles = PropertyReader.read('/' + m_configFile);
		String s = propFiles.getProperty(PROPERTY_FILES);
		String fNames[] = StringUtils.toStringArray(s,',');
		for(int i=0; i < fNames.length; ++i) {
			StringBuffer sb = new StringBuffer();
			sb.append('/');
			sb.append(fNames[i]);
		 	sb.append(".properties");
		 	p = PropertyReader.read(sb.toString());
		 	m_props.putAll((Map) p);
		}
		
		System.out.println("props =" + m_props.toString());
		 
	}
	
	/**
	 * Get the sindleton PropertyManager object.
	 * Calls init the first time.
	 * @return PropertyManager the singleton object.
	 */
	public static PropertyManager getInstance() throws IOException
	{
		if(null == m_pm) {
			m_pm = new PropertyManager();
			m_pm.init();			
		}
		return(m_pm);
	}

	
	/**
	 * Searches for the property with the specified key in this property list.
	 * If there is more than one value assigned to the property key, then the
	 * first value is returned.
	 * The method returns the default value if the property is not found.
	 * @param key - the property key.
	 * the property is not found.
	 * @returns the value in this property list with the specified key value.
	 * @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */

	public String getProperty(String key)
	{
		String s = m_props.getProperty(key);
		if(null == s || s.trim().length() < 1) {
			s = null;
		}
		return(s);
	}

	/**
	 * Searches for the property with the specified key in this property list.
	 * If there is more than one value assigned to the property key, then the
	 * first value is returned.
	 * The method returns the default value if the property is not found.
	 * @param key - the property key.
	 * @param defValue the default value to assign to the property if
	 * the property is not found.
	 * @returns the value in this property list with the specified key value.
	 * @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */

	public String getProperty(String key, String defValue)
	{
		String s = m_props.getProperty(key);
		if(null == s || s.trim().length() < 1) {
			s = defValue;
		}
		return(s);
	}
	
	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the int value in this property list with the specified 
	 *   key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */

	public int getIntProperty(String key, int defValue)
	{
		int value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Integer.parseInt(s);
		}
		catch (NumberFormatException ex) {
			value = defValue;
		}
		return(value);

	}

	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the long value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public long getLongProperty(String key, long defValue)
	{
		long value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Long.parseLong(s);
		}
		catch (NumberFormatException ex) {
			value = defValue;
		}
		return(value);

	}

	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the short value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public short getShortProperty(String key, short defValue)
	{
		short value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Short.parseShort(s);
		}
		catch (NumberFormatException ex) {
			value = defValue;
		}
		return(value);

	}

	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the boolean value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public boolean getBooleanProperty(String key, boolean defValue)
	{
		boolean value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		if("TRUE".equalsIgnoreCase(s)) {
			value = true;
		} else {
			value = false;
		}
		
		return(value);

	}


	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the float value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public float getFloatProperty(String key, float defValue)
	{
		float value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Float.parseFloat(s);
		}
		catch (NumberFormatException ex) {
			value = defValue;
		}
		return(value);

	}


	/**
	 * Searches for the property with the specified key in this property list.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   The method returns the default value if the property is not found.
	 *   @param key - the property key.
	 *   @param defValue the default value to assign to the property if
	 *   the property is not found.
	 *   @returns the double value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public double getDoubleProperty(String key, double defValue)
	{
		double value;
		String s = m_props.getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Double.parseDouble(s);
		}
		catch (NumberFormatException ex) {
			value = defValue;
		}
		return(value);

	}

	
	

}
