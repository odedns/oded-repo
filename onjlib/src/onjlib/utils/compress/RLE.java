
package onjlib.utils.compress;

import onjlib.utils.Debug;
import java.io.*;

/**
 * Run length encoding compression algorithm.
 * The algorithm compresses the input stream by compressing
 * repeating chars.
 * The manager byte signals a repeating char (Upper two bits set 0xC0).
 * The lower 6 bits in the  manager byte count the number of repetitions
 * for the next byte. The  byte to repeat follows the manager byte.
 *
 * <p>Copyright (c) 2002
 * @author      Oded Nissan
 * @version 1.0 15/4/2002
 */
public class RLE implements Compressor {
	static final int MNG_BYTE =  0x000000C0;

	/**
	 * compresses an input buffer into an output buffer.
	 * @param in the input buffer to compress.
	 * @return byte[] the compressed output buffer
	 */
	public byte[] compress(byte in[])
	{
		byte out[] = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(in);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(in.length);
		try {
			compress(bis,bos);
			out = bos.toByteArray();
		}
		catch (IOException ex) {

		}
		return(out);
	}

	/**
	 * decompresses an input buffer into an output buffer.
	 * @param in the input buffer to decompress.
	 * @return byte[] the decompressed output buffer
	 */
	public byte[] decompress(byte in[])
	{
		byte out[] = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(in);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(in.length);
		try {
			decompress(bis,bos);
			out = bos.toByteArray();
		}
		catch (Exception ex) {

		}
		return(out);
	}

	/**
	 * compresses an inputStream into the output stream.
	 * @param in the InputStream to compress.
	 * @param out the compressed OutputStream
	 * @throws IOException in case of IO error.
	 */
	public void compress(InputStream in, OutputStream out)
		throws IOException
	{
		int b;
		int last = -1;
		int counter = 0;
		while(-1 != (b = in.read())) {

		/**
		 * if the byte is equal to the manager byte
		 * then
		 */

		/**
		 * verifiy that counter does not overflow.
		 */
			if(b == last && counter < 0x3F ) {
				++counter;
			} else {
				if (counter > 0) {
				/**
				 * write the manager byte. and
				 * zero the counter.
				 */
					out.write(MNG_BYTE | counter);
					counter = 0;
				}
				if(last != -1) {
					out.write(last);
				}
				last = b;
			}

		} // while
		out.write(last);
		in.close();
		out.close();
	}

	/**
	 * decompresses an inputStream into the output stream.
	 * @param in the InputStream to decompress.
	 * @param out the decompressed OutputStream
	 * @throws IOException in case of IO error.
	 */
	public void decompress(InputStream in, OutputStream out)
			throws IOException
	{
		int b;
		int counter=0;
		while(-1 != (b = in.read())) {
			if( (b & MNG_BYTE) == MNG_BYTE ) {
				counter = b & 0x3F;
			//	Debug.println("counter = " + counter);

			} else {
				if(counter > 0) {
				/**
				 * output the number of duplications + 1
				 * for the original char.
				 */
					for(int i=0; i <= counter; ++i) {
						out.write(b);
					}
					counter = 0;
				} else {
					out.write(b);
				}
			}// if

		} // while
		in.close();
		out.close();

	}

	/**
	 * main test program.
	 */

	public static void main(String argv[])
	{
		Debug.setDebug(true);
		RLE rle = new RLE();
		try {
			rle.compress(new FileInputStream("c:/tmp/test"),
					new FileOutputStream("c:/tmp/test.comp"));
			rle.decompress(new FileInputStream("c:/tmp/test.comp"),
					new FileOutputStream("c:/tmp/test.decomp"));
		}
		catch (Exception ex) {

		}



	}

}
