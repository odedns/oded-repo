/**
 * Copyright (c) 1994-2002 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */


package onjlib.utils;

import onjlib.utils.StringUtils;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Set;

/**
 * A Properties class which supports multiple values assigned to one key.
 * The property entry of key=value1, value2, value3 will be stored as
 * an array of values assigned to one key.
 * This class extends the Multihash class which allows storing multiple
 * values per key in a Hash table.
 * <p> Copyright (c) 2002
 * @author Oded Nissan
 * @version 1.0 30/3/2002
 */

public class MultiProperties extends Multihash {

	static final char COMMENT_CHAR='#';
	static final char EQUAL_CHAR='=';
	static final char DELIM_CHAR=',';
	/**
	 * Default ctor, calls super ctor.
	 */
	public MultiProperties()
	{
		super();
	}

	/**
	 * Searches for the property with the specified key in this property list.
	 *   The method returns null if the property is not found.
	 *   If there is more than one value assigned to the property key, then the
	 *   first value is returned.
	 *   @param key - the property key.
	 *   @returns the value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public String getProperty(String key)
	{
		return((String)super.get(key));
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
		String s = (String)super.get(key);
		if(null == s || s.trim().length() < 1) {
			s = defValue;
		}
		return(s);
	}

	/**
	 * Searches for the property with the specified key in this property list.
	 * The method returns null if the property is not found.
	 * @param key - the property key.
	 * @returns an array of values in this property list with the specified 
	 * key value.
	 * @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */

	public String[] getProperties(String key)
	{
		return((String[])super.getValues(key));
	}

	/**
	 * Searches for the property with the specified key in this property list.
	 * The method returns the default value if the property is not found.
	 * @param key - the property key.
	 * @param defValue the default value to assign to the property if
	 * the property is not found.
	 * @returns an array of values in this property list with the specified 
	 * key value.
	 * @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */

	public String[] getProperties(String key, String defValue)
	{
		ArrayList ar = (ArrayList)super.getValuesList(key);
		String v[] = new String[ar.size()];
		v = (String[]) ar.toArray(v);
		for(int i=0; i < v.length; ++i) {
			if(v[i] == null || v[i].trim().length() < 1) {
				v[i] = defValue;
			}
		}
		return(v);
	}

	/**
	 * loads property values from an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in the file is read into the MultiProperties Object.
	 * @param fname - the file name to load from.
	 * @throws IOException.
	 */
	public void load(String fname) throws IOException
	{
		FileReader fr = new FileReader(fname);
		load(fr);
	}

	/**
	 * loads property values from an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in the file is read into the MultiProperties Object.
	 * @param is - the InputStream to load from.
	 * @throws IOException.
	 */
	public void load(InputStream is) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(is);
		load(isr);
	}

	/**
	 * loads property values from an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in the file is read into the MultiProperties Object.
	 * @param r- the Reader to load from.
	 * @throws IOException.
	 */
	public void load(Reader r) throws IOException
	{
		BufferedReader br = new BufferedReader(r);
		String line, key=null, values=null;
		StringTokenizer st;
		String v[] = null;

		while(null != (line = br.readLine())) {
			line= line.trim();
			System.out.println(line);
			/* skip comments */
			if(COMMENT_CHAR == line.charAt(0) || '!' == line.charAt(0) ) {
				continue;
			}
			int pos = line.indexOf(EQUAL_CHAR);
			if(pos < 2) {
				continue;
			}
			key = line.substring(0,pos);
			values = line.substring(pos+1).trim();
			/* ignore empty values */
			if(!"".equals( values )) {
				v = StringUtils.toStringArray(values,DELIM_CHAR);
				put(key,v);
			}
		}


	}


	/**
	 * Stores property values in an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in MultiProperties Object is written to a file.
	 * @param os - the outputStream to write to.
	 * @param header - a String containing header data to write to the file
	 * as a comment.
	 * @throws IOException.
	 */
	public void store(OutputStream os, String header) throws IOException
	{
		PrintWriter pw = new PrintWriter(os);
		store(pw,header);
	}

	/**
	 * Stores property values in an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in MultiProperties Object is written to a file.
	 * @param os - the outputStream to write to.
	 * @param header - a String containing header data to write to the file
	 * as a comment.
	 * @throws IOException.
	 */
	public void store(String fname, String header) throws IOException
	{
		FileWriter pw = new FileWriter(fname);
		store(pw,header);
	}

	/**
	 * Stores property values in an external text file.
	 * The format of the file is:
	 * key=value1, value2, value3.
	 * The '#' character represents comments.
	 * The data in MultiProperties Object is written to a file.
	 * @param os - the outputStream to write to.
	 * @param header - a String containing header data to write to the file
	 * as a comment.
	 * @throws IOException.
	 */
	public void store(Writer wr, String header) throws IOException
	{
		PrintWriter pw = new PrintWriter(wr);
		StringBuffer sb = new StringBuffer(80);
		Set keySet = keySet();
		String values[] = null;
		String keys[] = new String[keySet.size()];

		pw.println("#" + header);
		keys = (String[]) keySet.toArray(keys);

		for(int i=0; i < keys.length; ++i) {
			sb.append(keys[i]);
			sb.append('=');
			values = getProperties(keys[i],"");
			int j=0;
			while(j < values.length ) {
				sb.append(values[j++]);
				if(j < values.length) {
					sb.append(',');
				}
			} // while
			pw.println(sb.toString());
			sb.delete(0,sb.length());
		} // for
		pw.close();

	}

	/**
	 * set a property in the MultiProperties object.
	 * @param key the property key.
	 * @param value - the property value, it will be stored int the
	 * Multihash table as an array with one entry.
	 * @return Object previous mapping associated with the key,
	 * or null if no value was associated with the key.
	 */
	public Object setProperty(String key, String value)
	{
		return(super.put(key, value));
	}

	/**
	 * set a property in the MultiProperties object.
	 * @param key the property key.
	 * @param values - an array of property values, it will be stored int the
	 * Multihash table as an array.
	 * @return Object previous mapping associated with the key,
	 * or null if no value was associated with the key.
	 */
	public Object setProperties(String key, String values[])
	{
		return(super.put(key,(Object[])values));
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
		String s = getProperty(key);
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
		String s = getProperty(key);
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
		String s = getProperty(key);
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
		String s = getProperty(key);
		if(null == s) {
			return(defValue);
		}
		try {
			value = Boolean.getBoolean(s);
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
	 *   @returns the float value in this property list with the specified key value.
	 *   @see also: setProperty(java.lang.String, java.lang.String), defaults
	 */
	public float getFloatProperty(String key, float defValue)
	{
		float value;
		String s = getProperty(key);
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
		String s = getProperty(key);
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

	public static void main(String argv[])
	{
		MultiProperties props = new MultiProperties();
		try {
			props.load("c:/tmp/a.properties");
			System.out.println(props.toString());
			System.out.println("server=" + props.getProperty("server"));
			System.out.println("user=" + props.getProperty("user"));
			System.out.println("password=" + props.getProperty("password"));
			props.store("c:/tmp/b.properties","My Property file");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}



}
