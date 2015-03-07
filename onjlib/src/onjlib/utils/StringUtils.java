/**
 * Copyright (c) 1998-2002 Oded Nissan.
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
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A String utilities class
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 11/06/1999.
 * @version 2.00 7/4/2002 - changed name to StringUtils
 * @version 2.1 20/7/2002 - added replace and compressSpaces.
 **/
public abstract class StringUtils {

	/**
	 * trims trailing whitespaces from the string.
	 * @param s the string to trim from.
	 * @return a trimmed string.
	 **/
	public static String trimRight(String s)
	{
		int len = s.length();
		int nlen = len;
		char v[] = new char [len];

		s.getChars(0,len, v,0);
		for(int i = len-1; i >=0 && Character.isWhitespace(v[i]); --i) {
			nlen--;
		}
		return (new String(v,0,nlen));

	}

	/**
	 * trims leading whitespaces from the string.
	 * @param s the string to trim from.
	 * @return a trimmed string.
	 **/
	public static String trimLeft(String s)
	{
		int len = s.length();
		int offs = 0;
		char v[] = new char [len];

		s.getChars(0,len, v,0);
		for(int i = 0; i < v.length && Character.isWhitespace(v[i]); ++i) {
			offs++;
		}
		return (new String(v,offs,len - offs));
	}


	/**
	 * trims leading and trailing whitespaces from the string.
	 * @param s the string to trim from.
	 * @return a trimmed string.
	 **/
	public static String trim(String s)
	{
		int len = s.length();
		int nlen = len;
		int offs = 0;
		char v[] = new char [len];

		s.getChars(0,len, v,0);
		for(int i = len-1; i >=0 && Character.isWhitespace(v[i]); --i) {
			nlen--;
		}
		for(int i = 0; i < nlen && Character.isWhitespace(v[i]); ++i) {
			offs++;
		}
		return (new String(v,offs,nlen - offs));
	}


	/**
	 * pads a string with trailing spaces.
	 * @param sb an input StringBuffer.
	 * @param length the length to pad the String to.
	 * @return a new padded string.
	 **/
	public static String padString(StringBuffer sb, int length)
	{
		int slen = sb.length();
		int padLen = length - slen;

		if(slen >= length) {
			return(sb.toString());
		}
		for(int i = 0; i < padLen; ++i) {
			sb.append(' ');
		}
		return(sb.toString());
	}

	/**
	 * pads a string with trailing spaces.
	 * @param s an input String.
	 * @param length the length to pad the String to.
	 * @return a new padded string.
	 **/
	public static String padString(String s, int length)
	{
		StringBuffer sb = new StringBuffer(s);
		return(padString(sb,length));
	}

	/**
	 * convert a String containing serveral Strings seperated by
	 * the delimiter char into a String array.
	 * @param s the String to split.
	 * @param delim the delimiter character in the String.
	 * @return String[] a String array.
	 */
	public static String[] toStringArray(String s, char delim)
	{
		ArrayList<String> ar = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s, new Character(delim).toString());
		String token=null;
		while(st.hasMoreTokens()) {
			token = st.nextToken();
			ar.add(token.trim());
		}
		String v[] = new String[ar.size()];
		return((String[])ar.toArray(v));
	}

	/**
	 * Replace a pattern in the string with a new pattern.
	 * @param s the string containing the old url.
	 * @return String a the input String with new pattern replacing
	 * the old pattern.
	 */
	public static String replace(String s, String oldPat, String newPat)
	{
		int currIndex = 0;
		int index;
		int oldPatLen = oldPat.length();
		StringBuffer sb = new StringBuffer(s.length());
		String before=null;
		while(-1 < (index = s.indexOf(oldPat,currIndex))) {
			before=s.substring(currIndex,index);
			sb.append(before);
			sb.append(newPat);
			currIndex=index+ oldPatLen;
		}
		String after=s.substring(currIndex);
		sb.append(after);

		return(sb.toString());

    }

	/**
	 * remove multi spaces from a string.
	 * @param s the String to process.
	 * @return String a String with only single spaces.
	 */
	public static String compressSpaces(String s)
	{
		boolean gotSpace = false;
		char c;
		StringBuffer sb = new StringBuffer(s.length());
		for(int i=0; i < s.length(); ++i) {
			c = s.charAt(i);
			if(Character.isSpaceChar(c)) {
			    if(!gotSpace) {
					sb.append(c);
					gotSpace = true;
			    }
			} else {
				sb.append(c);
				gotSpace = false;
			}

		}
		return(sb.toString());
	}

  /**
   * escape commas for sql strings.
   * @param s the String to process.
   * @return a String with commas escaped
   * by using double commas - sql style.
   */
  public static String handleCommas(String s)
  {
		  StringBuffer sb = new StringBuffer(s);
		  sb = handleCommas(sb);
		  return(sb.toString());
  }

  /**
   * Convert single commas to double commas, so that sql can handle
   * the input string.
   *
   */
  public static StringBuffer handleCommas(StringBuffer sb)
  {
    char c;
    StringBuffer sb2 = new StringBuffer(sb.length());
    for(int i=0; i < sb.length(); ++i) {
      c = sb.charAt(i);
      if(c == '\'') {
        sb2.append('\'');
      }
      sb2.append(c);
    } // for
    return(sb2);

  }

	/**
	 * check if a String comtains a given substring.
	 * @param s the String to search.
	 * @param subs the substring to find.
	 * @return boolean true if a match is found or false
	 * otherwise.
	 */
	public static boolean contains(String s , String subs)
	{
		boolean res = false;
		int slen = s.length();
		int subLen = subs.length();
		String tmp = null;
		for(int i=0; i <= slen - subLen; ++i) {
			tmp = s.substring(i, i + subLen);
			if(tmp.equals(subs)) {
				res = true; 
				break;	
			}
			
		}
		return(res);		
	}
	
	/*
	 * main test program.
	 */
	public static void main(String argv[])
	{

		System.out.println("starting");
		String s = "       oded      ";
		System.out.println("len = " + s.length());
		System.out.println("|" + s + "|");

		try {
		/*
			s = trimRight(s);
			s = trimLeft(s);
		   */
			s= trim(s);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("len = " + s.length());
		System.out.println("|" + s + "|");
		s = padString(s,40);
		System.out.println("|" + s + "|");
		System.out.println("len =  " + s.length());

	}


}
