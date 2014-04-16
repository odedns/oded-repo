
package onjlib.utils.compress;

import java.io.*;

/**
 * A compression algorithm interface.
 * This is a generic interface for compression algorithms.
 * It provides the basic compression decompression methods.
 *
 * <p>Copyright (c) 2002
 * @author      Oded Nissan
 * @version 1.0 12/4/2002
 */
public interface Compressor {

	/**
	 * compresses an input buffer into an output buffer.
	 * @param in the input buffer to compress.
	 * @return byte[] the compressed output buffer
	 */
	public byte[] compress(byte in[]);

	/**
	 * decompresses an input buffer into an output buffer.
	 * @param in the input buffer to decompress.
	 * @return byte[] the decompressed output buffer
	 */
	public byte[] decompress(byte in[]);

	/**
	 * compresses an inputStream into the output stream.
	 * @param in the InputStream to compress.
	 * @param out the compressed OutputStream
	 * @throws IOException in case of IO error.
	 */
	 public void compress(InputStream in, OutputStream out)
			throws IOException;

	/**
	 * decompresses an inputStream into the output stream.
	 * @param in the InputStream to decompress.
	 * @param out the decompressed OutputStream
	 * @throws IOException in case of IO error.
	 */
	public void decompress(InputStream in, OutputStream out)
			throws IOException;
}
