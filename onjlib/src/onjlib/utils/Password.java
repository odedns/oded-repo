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
 * Class Name: Password
 *
 * Purpose: class for password management.
 *
 * The Password class provides methods for encrypting, generating and
 * verifying passwords.
 * This class can use several password encryption algorithms.
 * It can also apply different rules for password verification.
 * The system should read the following values from an ini file and
 * initialize the class with them:
 * password.verify.minchars
 * password.verify.maxchars
 * password.verify.alphachars
 * password.verify.numericchars
 * password.verify.numspecialchars
 * password.verify.specialchars
 * password.verify.forbidden
 * password.generate.length
 * password.encryption.algorithm
 *
 * @author Oded Nissan
 * @version 1.1 01/02/2000
 *
 */



import java.security.*;

 /**
  * The Password class provides a method for encrypting passwords.
  * This class can use several password encryption algorithms.
  */

public class Password {
	static final char SEMICOLON = ':';
	static Password m_password = null;

	/**
	 * The algorithm used for the password encryption.
	 * Possible values are:
	 * MD2
	 * MD5
	 * SHA
	 * SHA-1
	 */
	static String m_algorithm;
	static int m_minChars = 8;
	static int m_maxChars = 32;
	static int m_specialChars = 0;
	static int m_alphaChars = 1;
	static int m_numericChars = 1;
	static char m_chars[] = null;
	/*
	 * list of forbidden strings that are not allowed in the password.
	 */
	static String m_forbidden[] = null;



	/**
	 * virtual constructor, creates a static instance of
	 * a password object with the default initialization values.
	 * @return Password a reference to a Password object.
	 */
	public static Password getInstance()
	{
		if(null == m_password) {
			m_password = new Password();
			m_password.setAlgorithm("MD5");
		}

		if (m_specialChars < 0) m_specialChars	= 0;
		if (m_alphaChars   < 0)   m_alphaChars	= 0;
		if (m_numericChars < 0) m_numericChars	= 0;

		return(m_password);
	}


	/**
	 * virtual constructor, creates a static instance of
	 * a password object with specific encryption algorithm
	 * and default values for password verification.
	 * @param algorithm the algorithm to be used for password
	 * encryption.
	 * @return Password a reference to a Password object.
	 */
	public static Password getInstance(String algorithm)
	{
		if(null == m_password) {
			m_password = new Password();
			m_password.setAlgorithm("MD5");
		}

		if (m_specialChars < 0) m_specialChars	= 0;
		if (m_alphaChars   < 0)   m_alphaChars	= 0;
		if (m_numericChars < 0) m_numericChars	= 0;

		return(m_password);
	}

	/**
	 * virtual constructor, creates a static instance of
	 * a password object with specific encryption algorithm
	 * and default values for password verification.
	 * @param minChars the minimum number of chars allowed in a password.
	 * @param maxChars the maximum number of chars allowed in a password.
	 * @param alphaChars the minimum number of alpha chars allowed in
	 * a password.
	 * @param numericChars the minimum number of digit chars allowed in
	 * a password.
	 * @param specialChars the minimum number of special chars allowed in
	 * a password.
	 * @param chars a vector of special characters to be used for matching
	 * special characters.
	 * @param forbidden an array of strings that cannot appear in the
	 * password.
	 * @return Password a reference to a Password object.
	 */

	public static Password getInstance(int minChars,int maxChars,
			int alphaChars, int numericChars, int specialChars,
			char chars[], String forbidden[])
	{
		if(null == m_password) {
			setVars(minChars,maxChars,alphaChars,numericChars,
					specialChars,chars, forbidden);
			m_password = new Password();
			m_password.setAlgorithm("MD5");
		}

		if (m_specialChars < 0) m_specialChars	= 0;
		if (m_alphaChars   < 0)   m_alphaChars	= 0;
		if (m_numericChars < 0) m_numericChars	= 0;

		return(m_password);
	}

	/**
	 * sets the rule values for password verification.
	 * @param minChars the minimum number of chars allowed in a password.
	 * @param maxChars the maximum number of chars allowed in a password.
	 * @param alphaChars the minimum number of alpha chars allowed in
	 * a password.
	 * @param numericChars the minimum number of digit chars allowed in
	 * a password.
	 * @param specialChars the minimum number of special chars allowed in
	 * a password.
	 * @param chars a vector of special characters to be used for matching
	 * special characters.
	 * @param forbidden an array of strings that cannot appear in the
	 * password.
	 */
	public static void setVars(int minChars,int maxChars,int alphaChars,
			int numericChars, int specialChars, char chars[],
			String forbidden[])
	{
		m_minChars = minChars;
		m_maxChars = maxChars;
		m_alphaChars = alphaChars;
		m_numericChars = numericChars;
		m_specialChars = specialChars;
		m_chars = chars;
		m_forbidden = forbidden;
	}
	/**
	 * set the algorithm to be used for encryption.
	 * @param algorithm the name of the message digest algorithm to use.
	 * Possible values are:
	 * MD2
	 * MD5
	 * SHA
	 * SHA-1
	 */
	public void setAlgorithm(String algorithm)
	{
		m_algorithm = algorithm;
	}

	/**
	 * Encrypts a password.
	 * @param clearPass the password in clear text.
	 * @return String the encrypted password.
	 * @throws NoSuchAlgorithmException
	 */
	public String encrypt(String clearPass)
		throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance(m_algorithm);
		byte b[] = clearPass.getBytes();
		md.update(b);
		byte b1[] = md.digest();
		byte b2[] = Base64.encode(b1);
		return(new String(b2));
	}
	/**
	 * @generatePassword
	 * generates a random password.
	 * @return String the password.
	 **/
	public static String generate()
	{
		return(generate(m_minChars));
	}
	/**
	 * @generatePassword
	 * generates a random password.
	 * @param passwordChars the number of chars in the generated password.
	 * @return String the password.
	 **/
	public static String generate2(int passwordChars) {

		StringBuffer buff = new StringBuffer(passwordChars);
		int alphaChars = passwordChars - (m_specialChars + m_numericChars);

		int numberOfSpecialChars;
		if (m_chars == null) numberOfSpecialChars = 0;
		else numberOfSpecialChars = m_chars.length;

		int index;
		int rand;
		int i;
		double size = 0.99;
		double arraySize = numberOfSpecialChars - 0.01;

		for (i=0 ; i < alphaChars ; i++) {

			rand = (int)(Math.random() * 51.99);
			rand += 65;
			if (rand >= 91) rand += 6;

			index = (int)(Math.random() * size++);
			buff.insert( index , (char)rand );
		}
		for (i=0 ; i < m_numericChars ; i++) {

			rand = (int)(Math.random() * 9.99);
			rand += 48;

			index = (int)(Math.random() * size++);
			buff.insert( index , (char)rand );
		}
		for (i=0 ; i < m_specialChars ; i++) {

			rand = (int)(Math.random() * arraySize);

			index = (int)(Math.random() * size++);
			buff.insert( index , m_chars[rand]);
		}
		return (new String(buff));
	}

	public static String generate(int passwordChars)
	{
		char c[] = new char[passwordChars];
		int n = 0;

		for(int i = 0; i < passwordChars; ++i) {
			n =  (int) (Math.random() * 62 );
			if(n >= Character.MAX_RADIX) {
				n = n % Character.MAX_RADIX;
				c[i] = Character.forDigit(n,
						Character.MAX_RADIX);
				c[i] = Character.toUpperCase(c[i]);
			} else {
				c[i] = Character.forDigit(n,
						Character.MAX_RADIX);
			}
		}
		return(new String(c));
	}

	/**
	 * verify a password by making sure that the password
	 * contains the required number of characters, alpha chars,
	 * numeric chars and special chars.
	 * @param clearPass the password in clear text.
	 * @return boolean true in case the password is verified, false
	 * otherwise.
	 */
	public boolean verify(String clearPass)
	{
		int numChars = 0;
		int alphaChars = 0;
		int numericChars = 0;
		int specialChars = 0;
		char c;
		int len = clearPass.length();

		if( len < m_minChars || len > m_maxChars) {
			return(false);
		}

		for(int i=0; i < len; ++i) {
			c = clearPass.charAt(i);
			if(c == SEMICOLON || c == '\r' || c == '\n') {
				return(false);
			}
			if(Character.isLetter(c)) {
				++alphaChars;
			}
			if(Character.isDigit(c)) {
				++numericChars;
			}
			if(m_chars != null) {
				for(int j=0; j < m_chars.length; ++j) {
					if(m_chars[j] == c) {
						++specialChars;
						break;
					}
				}
			}
		}
		if(alphaChars < m_alphaChars ||
		   numericChars < m_numericChars ||
		   specialChars < m_specialChars) {
			return(false);
		}
		return(true);
	}

	/**
	 * verify a password by making sure that the password
	 * contains the required number of characters, alpha chars,
	 * numeric chars and special chars.
	 * The function also check that the password does not contain
	 * the userid and other forbidden strings.
	 * @param clearPass the password in clear text.
	 * @param userId the userid of the user. The password cannot contain
	 * this userid in it.
	 * @return boolean true in case the password is verified, false
	 * otherwise.
	 */
	public boolean verify(String clearPass, String userId)
	{
		/*
		 * if the password contains the userid then
		 * the password failed verification.
		 */
		if(checkForbidden(clearPass,userId)) {
			return(false);
		}
		/*
		 * if the password contains any of the forbidden
		 * strings the password failed verification.
		 */
		if(null != m_forbidden &&
			checkForbidden(clearPass,m_forbidden)) {
			return(false);
		}
		/*
		 * call the other verify method.
		 */
		return(verify(clearPass));

	}

	private boolean checkForbidden(String pass, String forbidden)
	{
		String sub;
		boolean matched = false;
		int fLen = forbidden.length();
		int passLen = pass.length();

		for(int i=0; i <= (passLen - fLen); ++i) {
			sub = pass.substring(i,i+fLen);
			if(0 == sub.compareTo(forbidden)) {
				matched = true;
				break;
			}
		}
		return(matched);
	}


	private boolean checkForbidden(String pass, String forbidden[])
	{
		String sub;
		boolean matched = false;

		int passLen = pass.length();
		for(int j=0; !matched && j < forbidden.length; ++j) {
			System.out.println("trying to match to "+forbidden[j]);
			matched = checkForbidden(pass,forbidden[j]);
		}
		return(matched);

	}

	/*
	 * main test driver
	 */
	public static void main(String argv[])
	{

		String pass = null;
		if(argv.length < 1) {
			System.out.println("usage PwEncrypted <password>");
			System.exit(1);
		}
		Password pw = null;
		System.out.println("password = " + argv[0]);
			pw = Password.getInstance();
		try {
		pass = pw.encrypt(argv[0]);
		} catch (NoSuchAlgorithmException ne) {
			ne.printStackTrace();
		}
		System.out.println("encrypted pas  = " + pass);
		System.out.println('\n');
		System.out.println("verify = " + pw.verify(argv[0]));
		for(int i=0; i < 5; ++i) {
			System.out.println("password = " + Password.generate());
		}
	}
}
