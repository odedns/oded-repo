package onjlib.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * <p>Description:
 * This class expands wild card command line arguments into 
 * a list of files matching the wildcard pattern.
 * This is useful when processing command line arguments
 * containing wildcard arguments. Pass the wildcard to the
 * expand() method, which returns a list of files matching 
 * the wildcard pattern.
 *
 * <p>Copyright (c) 2002
 * @author Oded Nissan
 * @version 1.0  21/4/2002
 */

public abstract class Wildargs {

	/**
	 * Expand a wildcard string into a list of filenames matching
	 * the wildcard string. The '*' and '? wildcard chars are supported.
	 * This method checks the match by using the Regexp class.
	 * @param pattern the string to evaluate.
	 * @return String[] an array of strings containing the matched
	 * filenames.
	 */
	public static String[] expand(String pattern)
	{

		String path = null;
		File f = new File(pattern);
		String dir = f.getParent();
		String pat = f.getName();
		if(null != dir) {
			path = dir;
		} else {
			path = pattern;
		}
		f = new File(path);
		if(!f.isDirectory()) {
			return(null);
		}
		String v[] = f.list();
		ArrayList ar = new ArrayList(v.length);
		Regexp reg = new Regexp();
		try {
			for(int i=0; i < v.length; ++i) {
				if(reg.match(pat,v[i])) {
				//	Debug.println("adding : " + v[i]);
					ar.add(v[i]);
				}
			} // for
		}   catch (RegexpException ex) {
			ex.printStackTrace();
			return(null);
		}

		String v1[] = new String[ar.size()];
		v1 = (String[]) ar.toArray(v1);
		return(v1);
	}

	/**
	 * Checks to see if the pattern string contains
	 * any wildcard characters like '*' or '?'.
	 * @param pattern the string to evaluate.
	 * @return true is the string contains wildargs, false if it doesn't.
	 */
	public static boolean isWildarg(String pattern)
	{
		return( ( 0 <=  pattern.indexOf(Regexp.ASTERISK) ||
					0 <= pattern.indexOf(Regexp.WILDCARD) ) ? true : false);
	}


	/**
	 * main test program.
	 */
	 public static void main(String args[])
	 {
		Debug.setDebug(true);
		String s = Console.readString("Enter pattern:");
		System.out.println("\n results: " + isWildarg(s));
		String v[] = expand(s);
		for(int i=0; i < v.length; ++i) {
			System.out.println(v[i]);
		}
	 }

}
