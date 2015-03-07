package onjlib.utils;

import java.io.*;

/**
 * A class for performing console based IO.
 * The class supports reading a character String and numeric strings 
 * from the console.
 * <p>  Copyright (c) 2001
 * Company: NA
 * @author  Oded Nissan
 * @version 1.0 10/4/2002
 */

public abstract class Console {

	static BufferedReader m_br =  new BufferedReader(
						new InputStreamReader(System.in));

	/**
	 * read a String from the console.
	 * returns the string read or null if
	 * an error occured.
	 * @return String the string read.
	 */
	public static String readString()
	{
		String s = null;
		try {
			s = m_br.readLine();
		}
		catch (IOException ex) {
			s = null;
		}
		return(s);

	}

	/**
	 * Display a prompts and
	 * read a String from the console.
	 * returns the string read or null if
	 * an error occured.
	 * @param prompt the prompt String to be displayed.
	 * @return String the string read.
	 */
	public static String readString(String prompt)
	{
		String s = null;
		System.out.print(prompt);
		try {
			s = m_br.readLine();
		}
		catch (IOException ex) {
			s = null;
		}
		return(s);

	}

	/**
	 * reads an int from the console.
	 * returns the read int or throws a NumberFormatException
	 * in case of an error.
	 * @return int the read integer.
	 * @throws NumberFormatException in case an error occured.
	 */
	public static int readInt() throws NumberFormatException
	{
		String s = readString();
		if(null == s) {
			s = "";
		}
		return(Integer.parseInt(s));
	}
	/**
	 * Displays a prompt and
	 * reads an int from the console.
	 * returns the read int or throws a NumberFormatException
	 * in case of an error.
	 * @param prompt the prompt String to be displayed.
	 * @return int the read integer.
	 * @throws NumberFormatException in case an error occured.
	 */
	public static int readInt(String prompt) throws NumberFormatException
	{
		System.out.print(prompt);
		String s = readString();
		if(null == s) {
			s = "";
		}
		return(Integer.parseInt(s));
	}

	/**
	 * reads a long from the console.
	 * returns the read long or throws a NumberFormatException
	 * in case of an error.
	 * @return long the read integer.
	 * @throws NumberFormatException in case an error occured.
	 */
	public static long readLong() throws NumberFormatException
	{
		String s = readString();
		if(null == s) {
			s = "";
		}
		return(Long.parseLong(s));
	}

	/**
	 * Displays a prompt and
	 * reads a long from the console.
	 * returns the read long or throws a NumberFormatException
	 * in case of an error.
	 * @param prompt the prompt String to be displayed.
	 * @return long the read integer.
	 * @throws NumberFormatException in case an error occured.
	 */
	public static long readLong(String prompt) throws NumberFormatException
	{
		System.out.print(prompt);
		String s = readString();
		if(null == s) {
			s = "";
		}
		return(Long.parseLong(s));
	}
	/**
	 * main test.
	 */

	 public static void main(String argv[])
	 {
		String s = Console.readString("Enter Name: ");
		int n = Console.readInt("Enter id: ");
		System.out.println("name: " + s + " id: " + n);
	}

}
