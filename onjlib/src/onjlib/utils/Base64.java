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

import java.io.*;

/**
 * A Base64 encoder and decoder class.
 * <p> Copyright (c) 2001
 * @author      Oded Nissan
 * @version 1.0 10/4/2001
 */

public abstract class Base64 {
	private static final int BUF_SIZE=1024;
	private static final char PAD_CHAR = '=';
	private final static byte B64[] = {
	//       0   1   2   3   4   5   6   7
		'A','B','C','D','E','F','G','H', // 0
		'I','J','K','L','M','N','O','P', // 1
		'Q','R','S','T','U','V','W','X', // 2
		'Y','Z','a','b','c','d','e','f', // 3
		'g','h','i','j','k','l','m','n', // 4
		'o','p','q','r','s','t','u','v', // 5
		'w','x','y','z','0','1','2','3', // 6
		'4','5','6','7','8','9','+','/'  // 7
	};


	/**
	 * encode bytes in BASE64 format.
	 * @param b array of bytes to encode.
	 * @return encoded byte array.
	 */
	public static byte[] encode(byte b[])
	{
		return(encode(b,b.length));
	}

	/**
	 * encode bytes in BASE64 format.
	 * @param b array of bytes to encode.
	 * @param len the length of the array to encode.
	 * @return encoded byte array.
	 */
	public static byte[] encode(byte b[], int len)
	{
		int newlen = len / 3;
		if((len % 3 )> 0) {
			++newlen;
		}
		newlen *=4;
		byte out[] = new byte[newlen];
		int offset = 0, noffset = 0;
		while(len > 0) {
			if(len == 1) {
				out[noffset++] = B64[(b[offset] >>> 2) & 0x3F];
				out[noffset++] = B64[((b[offset] << 4) & 0x30)
								 + ((0 >>> 4) & 0xf)];
				out[noffset++]=PAD_CHAR;
				out[noffset++]=PAD_CHAR;
			} else {
				if(len == 2) {
					out[noffset++] = B64[(b[offset] >>> 2)
						& 0x3F];
					out[noffset++] = B64[((b[offset] << 4)
							& 0x30) + ((b[offset+1] >>> 4) & 0xf)];
					out[noffset++]=B64[(((b[offset+1] << 2)
						& 0x3c) + ((0 >>> 6) & 0x3))];
					out[noffset++]=PAD_CHAR;
				} else {
					out[noffset++] = B64[(b[offset] >>> 2)
						& 0x3F];
					out[noffset++] = B64[((b[offset] << 4)
							& 0x30) + ((b[offset+1] >>> 4) & 0xf)];
					out[noffset++]=B64[(((b[offset+1] << 2)
						& 0x3c) + ((b[offset+2] >>> 6)
						& 0x3))];
					out[noffset++]=B64[b[offset+2] & 0x3F];

				}
			}
			len-=3;
			offset+=3;

		}
		return(out);

	}

	/**
	 * decode bytes from BASE64 format.
	 * @param b array of bytes to decode.
	 * @return the decoded byte array.
	 */
	public static byte[] decode(byte b[])
	{
		return(decode(b,b.length));
	}

	/**
	 * decode bytes from BASE64 format.
	 * @param b array of bytes to decode.
	 * @param len the length of the array to decode.
	 * @return the decoded byte array.
	 */
	public static byte[] decode(byte b[], int len)
	{
		int newlen = (len / 4) * 3;
		byte out[] = new byte[newlen];
		/*
		 * decode buffer.
		 */
		byte dbuff[] = new byte[4];
		byte bb[] = new byte[4];
		for(int i=0; i<4; ++i) {
			bb[i] = -1;
		}

		int noffset = 0, offset = 0;
		while(len > 0) {
			for(int i=0; i < 4; ++i) {
					dbuff[i] = b[offset++];
			}
				fillDecodeBuffer(dbuff,bb);
				if(PAD_CHAR == dbuff[2]) {
					out[noffset++]=( (byte)(((bb[0] << 2)
						& 0xfc) | ((bb[1] >>> 4) & 3)));
			} else {
					if(PAD_CHAR == dbuff[3]) {
							out[noffset++]=( (byte) (((bb[0] << 2) & 0xfc)
								| ((bb[1] >>> 4) & 3)) );
							out[noffset++]=( (byte) (((bb[1] << 4) & 0xf0)
								| ((bb[2] >>> 2) & 0xf)) );
				} else {
						out[noffset++]=( (byte) (((bb[0] << 2)
						& 0xfc) | ((bb[1] >>> 4) & 3)));
					out[noffset++]=((byte) (((bb[1] << 4)
						& 0xf0)|((bb[2] >>> 2) & 0xf)));
					out[noffset++]=((byte) (((bb[2] << 6)
						& 0xc0)|(bb[3]  & 0x3f)));
				}
			}
			len-=4;

		}
		byte out1[] = new byte[noffset];
		for(int i=0; i< noffset; ++i) {
			out1[i] = out[i];
		}
		return(out1);

	}

	/**
	 * encode bytes to BASE64 format.
	 * @param is the InputStream to encode.
	 * @param  os the OutputStream to write the encoded data to.
	 * @throws IOException on error.
	 */
	public static void encode(InputStream is, OutputStream os)
		throws IOException
	{
		byte inbuf[] = new byte[BUF_SIZE];
		byte outbuf[];
		int nbytes = 0;

		while(-1 != (nbytes=is.read(inbuf))) {
			outbuf = encode(inbuf,nbytes);
			os.write(outbuf,0,outbuf.length);
		}
		is.close();
		os.close();

	}

	/**
	 * decode bytes from BASE64 format.
	 * @param is the InputStream to decode.
	 * @param  os the OutputStream to write the decoded data to.
	 * @throws IOException on error.
	 */
	public static void decode(InputStream is, OutputStream os)
		throws IOException
	{
		byte inbuf[] = new byte[BUF_SIZE];
		byte outbuf[];
		int nbytes = 0;

		while(-1 != (nbytes=is.read(inbuf))) {
			outbuf = decode(inbuf,nbytes);
			os.write(outbuf,0,outbuf.length);
		}
		is.close();
		os.close();
	}

	private static void fillDecodeBuffer(byte dbuff[], byte b[])
	{
		for (int i = 0; i < 64; i++) {
				if (dbuff[0] == B64[i]) {
			b[0] = (byte) i;
			}
				if (dbuff[1] == B64[i]) {
			b[1] = (byte) i;
			}
				if (dbuff[2] == B64[i]) {
			b[2] = (byte) i;
			}
				if (dbuff[3] == B64[i]) {
				b[3] = (byte) i;
			}
			}
	}

	public static void main(String argv[])
	{
		String s = "1234567";


		byte b1[] = Base64.encode(s.getBytes());

		System.out.println("encoded String : " + new String(b1));
		byte b2[] = Base64.decode(b1);
		System.out.println("decoded String : " + new String(b2) );

		try {
			FileInputStream fis = new FileInputStream("/dev/src/Y1");
				FileOutputStream fos = new FileOutputStream("/dev/src/Y1.encode");
			Base64.encode(fis,fos);
			fis = new FileInputStream("/dev/src/Y1.encode");
				fos = new FileOutputStream("/dev/src/Y1.decode");
			Base64.decode(fis,fos);
		} catch(IOException ie) {
			ie.printStackTrace();
		}



	}
}
