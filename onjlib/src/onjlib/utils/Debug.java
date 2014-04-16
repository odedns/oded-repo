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

/**
 * Debug class, supports printing of debug messages to stdout.
 * The messages can be turned on or off by setting the debug flag
 * calling setDebug(boolean flag).
 * Assertions are also supported by the Debug class. If an 
 * evaluated assertion fails an Error object is thrown.
 * @author Oded Nissan
 * @version 1.0 11/4/2002
 */
public abstract class Debug {
	static boolean m_debugFlag = false;

	/**
	 * set the debug flag.
	 * @param flag true means debug is on.
	 */
	public static void setDebug(boolean flag)
	{
		m_debugFlag = flag;
	}

	/**
	 * prints a String to stdout with a line feed.
	 * @param s the String to print.
	 */
	public static void println(String s)
	{
		if(m_debugFlag) {
			System.out.println(s);
		}
	}

	/**
	 * prints an Object to stdout with a line feed.
	 * @param o the Object to print.
	 */
	public static void println(Object o)
	{
		if(m_debugFlag) {
			System.out.println(o.toString());
		}
	}

	/**
	 * prints a String to stdout with no line feed.
	 * @param s the String to print.
	 */
	public static void print(String s)
	{
		if(m_debugFlag) {
			System.out.print(s);
		}
	}


	/**
	 * prints an Object to stdout with no line feed.
	 * @param o the Object to print.
	 */
	public static void print(Object o)
	{
		if(m_debugFlag) {
			System.out.print(o.toString());
		}
	}

	/**
	 * Assert a condition. Throws an Error if the
	 * condition is false.
	 * @param cond the condition to evaluate.
	 */
	 /*
	 public void assert(boolean cond)
	 {
		if(cond) {
			throw new Error("Assertion failed.");
		}
	}
	*/

	/**
	 * Assert a condition. Throws an Error if the
	 * condition is false.
	 * @param cond the condition to evaluate.
	 * @param msg the message to print.
	 */
	 /*
	 public void assert(boolean cond, String msg)
	 {
		if(cond) {
			throw new Error(msg);
		}
	}
	*/


}
