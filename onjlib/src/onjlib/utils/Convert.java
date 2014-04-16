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
 * The Convert class performs conversions of binary
 * numbers to and from byte arrays.
 */
public class Convert {

	/**
	 * convert a short into a byte array.
	 * @param n the short to convert.
	 * @return a byte array.
	 */
	public static byte[] shortToBytes(short n)
	{
		byte b[] = new byte[2];
		b[0] = (byte)(n & 0x00FF);
		b[1] = (byte) ((n & 0xFF00) >>> 8);
		return(b);
	}

	/**
	 * convert a byte array into a short.
	 * @param b byte array to convert.
	 * @return short.
	 */
	public static short bytesToShort(byte b[])
	{
		short n;
		n = (short)b[0];
		n |= (short) b[1] << 8;
		return(n);
	}

	/**
	 * convert an int into a byte array.
	 * @param n the int to convert.
	 * @return a byte array.
	 */
	public static byte[] intToBytes(int n)
	{
		byte b[] = new byte[4];
		b[0] = (byte)(n & 0x000000FF);
		b[1] = (byte) ((n & 0xFF00) >>> 8);
		b[2] = (byte) ((n & 0x00FF0000) >>> 16);
		b[3] = (byte) ((n & 0xFF000000) >>> 24);
		return(b);
	}

	/**
	 * convert a byte array into an int.
	 * @param b byte array to convert.
	 * @return int.
	 */
	public static int bytesToInt(byte b[])
	{
		int n;
		n = (int )b[0];
		n |= (int ) b[1] << 8;
		n |= (int ) b[2] << 16;
		n |= (int ) b[3] << 24;
		return(n);
	}


	/**
	 * convert a long into a byte array.
	 * @param n the int to convert.
	 * @return a byte array.
	 */
	public static byte[] longToBytes(long n)
	{
		byte b[] = new byte[8];
		b[0] = (byte)(n & 0x000000FF);
		b[1] = (byte) ((n & 0xFF00) >>> 8);
		b[2] = (byte) ((n & 0x00FF0000) >>> 16);
		b[3] = (byte) ((n & 0xFF000000) >>> 24);
		b[4] = (byte) ((n & (0xFF000000 << 8)) >>> 32);
		b[5] = (byte) ((n & (0xFF000000 << 16)) >>> 40);
		b[6] = (byte) ((n & (0xFF000000 << 32)) >>> 48);
		b[7] = (byte) ((n & (0xFF000000 << 40)) >>> 56);

		return(b);
	}

	/**
	 * convert a byte array into a long.
	 * @param b byte array to convert.
	 * @return long.
	 */
	public static long bytesToLong(byte b[])
	{
		long n;
		n = (long)b[0];
		n |= (long) b[1] << 8;
		n |= (long) b[2] << 16;
		n |= (long) b[3] << 24;
		n |= (long) b[4] << 32;
		n |= (long) b[5] << 40;
		n |= (long) b[6] << 48;
		n |= (long) b[7] << 56;
		return(n);
	}
	
	/**
	 * Convert a byte array into a hex string
	 * @param b the byte array to convert.
	 * @return String the hext string.
	 */
	public static String bytesToHex(byte b[])
	{	
		final char hexTbl[] = {'0','1','2','3','4','5','6','7','8','9',
										'A','B','C','D','E','F' };
		
		byte ch = 0x00;
		int i = b.length-1;
		StringBuffer sb = new StringBuffer(b.length * 2);
		
		if(b == null || b.length <=  0) {
			return(null);
		}
		/*
		 * loop over byte array
		 */
		while(i >= 0 ) {
			ch = (byte) (b[i] & 0xF0);
			ch = (byte) (ch >>> 4);
			ch = (byte) (ch & 0x0F);
			sb.append(hexTbl[(int) ch]);
			ch = (byte) (b[i] & 0x0F);
			sb.append(hexTbl[(int) ch]);
			--i;
			
		} // while
		
		return(sb.toString());
		
	}

	
	/**
	 * create a hex String for a long
	 * @param l long 
	 * @return String the hex String
	 */
	public static String longToHex(long l)
	{
		byte b[] = longToBytes(l);
		return(bytesToHex(b));
	}

	/**
	 * create a hex String for an int
	 * @param n int
	 * @return String the hex String
	 */
	public static String intToHex(int n)
	{
		byte b[] = intToBytes(n);
		return(bytesToHex(b));
	}
	/**
	 * create a hex String for a short
	 * @param s short 
	 * @return String the hex String
	 */
	public static String shortToHex(short s)
	{
		byte b[] = shortToBytes(s);
		return(bytesToHex(b));
	}

	
	
	/*
	 * test main
	 */
	public static void main(String argv[])
	{
		short n = 0x1010;
		int num = 0x10101010;
		long l = 0x10101010  << 8;

		/*
		System.out.println("n = " + n);
		byte b[] = shortToBytes(n);
		n = bytesToShort(b);
		System.out.println("n = " + n);

		System.out.println("num = " + num);
		b = intToBytes(num);
		num = bytesToInt(b);
		System.out.println("num = " + num);

		*/
		System.out.println("l = " + l);
		byte b[] = longToBytes(l);
		for(int i=0; i<8; ++i) {
			System.out.println("b[" +i + "]= " + b[i]);
		}
		l = bytesToLong(b);
		System.out.println("l = " + l);
	}
}
