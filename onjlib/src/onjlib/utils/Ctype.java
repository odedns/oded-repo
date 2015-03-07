/**
 * Copyright (c) 1998-2001 Oded Nissan.
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
 * Clib style ctype library.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 1/10/1998.
 */
public abstract class Ctype {

	/**
	 * checks if character is alphanumeric.
	 */
	static boolean isalnum(char c)
	{
		return(c >= 0x30 && c <= 0x7a);
	}
	static boolean isalpha(char c)
	{
		return(c >= 0x41 && c <= 0x7a);
	}
	static boolean isascii(char c)
	{
		return(c < 128);
	}
	static boolean iscntrl(char c)
	{
		return(true);
	}
	static boolean isdigit(char c)
	{
		return(c >= 0x30 && c <= 0x39);
	}
	static boolean islower(char c)
	{
		return(c >= 0x61 && c <= 0x7a);
	}
	static boolean isupper(char c)
	{
		return(c >= 0x41 && c <= 0x5a);
	}
	static boolean isprint(char c)
	{
		return(c <= 0x7e);
	}

	static boolean ispunct(char c)
	{
		return(true);
	}
	static boolean isspace(char c)
	{
		if(c == ' ' || c == '\r' || c == '\n' ||
		   c == '\t' || c == '\b') {
			return(true);
		}

		return(false);
	}

	static boolean isgraph(char c)
	{
		return((c) >= 0x21 && (c) <= 0x7e);
	}

	static int toupper(char c)
	{
		if(islower(c)) {
			return(c - 0x32);
		}
		return(c);
	}
	static int tolower(char c)
	{
		if(isupper(c)) {
			return(c + 0x32);
		}
		return(c);
	}

} /* class Ctype */
