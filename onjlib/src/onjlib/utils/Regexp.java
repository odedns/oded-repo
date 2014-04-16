/**
 * Copyright (c) 1994-1999 Oded Nissan.
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
 * Regexp class evaluates regular expressions.
 * This is a regular expression class.
 * The following regular expression symbols are supported:
 * the asterisk '*' - matches any one or more characters.
 * the wildcard '?' - matches any single character.
 * the set  -
 * sets are grouped within brackets ([]).
 * a set can be any combination of the following:
 * A group of characters :  	[abcdrttt]
 * A range of characters: 	[a-z] [a-z0-9]
 * Any combination of both: 	[a-z#$%&0-9]
 * Sets can be negated using the '^' or '!' chars :   [!abc] or [!a-z]
 *
 * In order to use the '[' ']' '^' and '!' chars in the set as regular
 * characters (ignore their special meaning) use the escape char '\'
 * for example :  [abc\^\!\]]
 * uses special characters as regular characters in the set.
 * The asterisk and wildcard can also be escaped using the '\' character.
 *
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 10/4/1999.
 */
public class Regexp {
	static final char ESCAPE 	= '\\';
	static final char ASTERISK 	= '*';
	static final char WILDCARD 	= '?';
	static final char SET_START 	= '[';
	static final char SET_END   	= ']';
	static final char SET_RANGE 	= '-';
	/**
	 * number of chars in the string matched by regular chars
	 * in the pattern.
	 */
	int m_matchedChars;
	/**
	 * number of chars in the string matched by wildcard chars in
	 * the pattern.
	 */
	int m_matchedWildcard;
	/**
	 * number of chars in the string matched by asterisk chars in
	 * the pattern.
	 */
	int m_matchedAsterisk;

	/**
	 * String containing the regular expression pattern.
	 */
	String m_regexp;

	/**
	 * Regular expression constructor.
	 */
	public Regexp()
	{
		m_matchedChars = 0;
		m_matchedWildcard = 0;
		m_matchedAsterisk = 0;
	}

	/**
	 * Regular expression constructor.
	 * @param regexp String containing the regular expression
	 * pattern.
	 */
	public Regexp(String regexp)
	{
		this();
		m_regexp = regexp;
	}


	/**
	 * @return int the number of chars in the string matched by regular
	 * chars in the pattern.
	 */
	public int getMatchedChars()
	{
		return(m_matchedChars);
	}

	/**
	 * @return int the number of chars in the string matched by wildcard
	 * chars in the pattern.
	 */
	public int getMatchedWildcard()
	{
		return(m_matchedWildcard);
	}

	/**
	 * @return int the number of chars in the string matched by asterisk
	 * chars in the pattern.
	 */
	public int getMatchedAsterisk()
	{
		return(m_matchedAsterisk);
	}

	/**
	 * matches a string to the regulat expression pattern.
	 * @param s the String to be matched.
	 * @return boolean true in case the string was matched
	 * false otherwise.
	 * @exception RegexpException thrown in case the regular expression
	 * pattern is invalid.
	 */
	public boolean match(String s)
		throws RegexpException
	{
		return(match(m_regexp,s));
	}

	/**
	 * matches a string to the regulat expression pattern.
	 * @param pattern the regular expression pattern we are trying to
	 * match.
	 * @param s the String to be matched.
	 * @return boolean true in case the string was matched
	 * false otherwise.
	 * @exception RegexpException thrown in case the regular expression
	 * pattern is invalid.
	 */
	public boolean match(String pattern, String s)
		throws RegexpException
	{
		char c, pc;
		int i = 0,j = 0;
		int patlen = pattern.length();
		int slen = s.length();
		boolean cont = true;
		int matchedChars = 0;

		while(i < patlen && j < slen && cont) {

			pc = pattern.charAt(i);
			c = s.charAt(j);

			switch(pc) {
				case WILDCARD:
					++matchedChars;
					++i;
					++j;
					++m_matchedWildcard;
					break;
				case ASTERISK:
				/**
				 * recursive call check if the rest
				 * of the string matches.
				 */
					if(match(pattern.substring(i+1),s.substring(j))) {
						return(true);
					}

					++m_matchedAsterisk;
					++matchedChars;
					++j;
					break;
				case ESCAPE:
					++i;
					if(i == patlen) {
						cont = false;
						throw new  RegexpException(
							RegexpException.FAILED_INVALID_PATTERN);
					}
					pc = pattern.charAt(i);
					if(c == pc) {
						++matchedChars;
						++i;
						++j;
						++m_matchedChars;
					} else {
						cont = false;
					}
					break;
				case SET_START:
					int set_end=pattern.indexOf(SET_END,i);
					if(set_end <  0) {
						throw new
						RegexpException(
					RegexpException.FAILED_INVALID_PATTERN);
					} else {
						String sSet =
							pattern.substring(++i,
								set_end);
						if(matchSet(sSet,c)) {
							++matchedChars;
							++j;
							++m_matchedChars;
							i = ++set_end;
						} else {
							cont = false;
						}
					}
					break;
				default:
					if(c == pc) {
						++matchedChars;
						++i;
						++j;
					} else {
						cont = false;
					}
					break;
			}
		}
		/**
		 * If we reached the end of the pattern then
		 * we have a match.
		 */

		return(matchedChars  == s.length() && (++i >= patlen)  ? true : false);
	}


	boolean matchSet(String pattern, char c)
		throws RegexpException
	{
		boolean match = false;
		boolean done = false;
		int patlen = pattern.length();
		char pc;
		int i = 0;

		while(i < patlen && !done) {
			pc = pattern.charAt(i);
			switch(pc) {

				case ESCAPE:
					++i;
					if(c == pc) {
						match = !match;
						done = true;
					}
					++i;
					break;
				case SET_RANGE:
					char rangeStart = pattern.charAt(i-1);
					++i;
					if(i == patlen) {
						done = true;
						throw new  RegexpException(
							RegexpException.FAILED_INVALID_PATTERN);
					}
					char rangeEnd = pattern.charAt(i);
					if(c >= rangeStart && c <= rangeEnd) {
						match = !match;
						done = true;
					}
					++i;
					break;
				case '!':
				case '^':
					match = !match;
					++i;
					break;

				default:
					if(c == pc) {
						match = !match;
						done = true;
					}
					++i;
					break;
			}
		}
		return(match);
	}


	/**
	 * main test driver.
	 * takes command line parameter.
	 * Regexp <patter> <string>
	 */
	public static void main(String argv[])
	{
		Regexp r = new Regexp();

		String pattern = Console.readString("Enter pattern:");
		String s = Console.readString("Enter String:");

		try {
			boolean  rv = r.match(pattern,s);
			System.out.println("rv = " + rv);
		} catch(RegexpException re) {
			re.printStackTrace();
		}

	}

}
