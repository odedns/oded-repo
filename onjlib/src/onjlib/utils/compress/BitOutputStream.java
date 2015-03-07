package onjlib.utils.compress;

import java.io.*;

public class BitOutputStream {
	static final int GROW_BITS = 255;
	static final int MAX_CACHE_BITS = 32;
	int m_bitLen = 8;
	long m_cache = 0;
	int m_bitsInCache = 32;
	long m_maxValue;

	BufferedOutputStream m_bos;

	BitOutputStream(String name)
		throws IOException
	{
		FileOutputStream fos = new FileOutputStream(name);
		m_bos = new BufferedOutputStream(fos);
		setBitLength(m_bitLen);
	}

	void setBitLength(int len)
	{
		m_maxValue = (1L << len) - 1;
		m_bitLen = len;
		System.out.println("bits: " + m_bitLen + " maxvalue: " + m_maxValue);
	}

	private void print(long c)
	{
		int mask = 0x80000000;
		int bit =0;

		for(int i=0; i<32; ++i) {
			bit = (long)(c & (mask >>> i)) > 0 ? 1 : 0;
			System.out.print(bit);
		}
		System.out.print('\n');
	}

	private int getBits(int c, int bits)
	{
		int mask = 0xFFFFFFFF;
		return(c & ~(mask << bits ));
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

	private void writeCache(long l)
		throws IOException
	{
		byte b[] = longToBytes(l);
		m_bos.write(b,0,4);
		System.out.println("writing to file:");
	}


	public void output(int c)
		throws IOException
	{
		int bitsToCache;
		int i=0;
		int bits = m_bitLen;
		long val = 0;

		while(i < bits) {
			if(m_bitsInCache == 0) {
			//	flush cache.
				print(m_cache);
				writeCache(m_cache);
				m_bitsInCache = MAX_CACHE_BITS;
				m_cache = 0;
				bitsToCache = Math.min(m_bitsInCache,bits-i);
			} else {
				bitsToCache = Math.min(m_bitsInCache,bits);
			}
			val = getBits(c,bitsToCache);
			c = c >>> bitsToCache;
			m_cache |= val << (MAX_CACHE_BITS - m_bitsInCache);
			m_bitsInCache-=bitsToCache;
			i+=bitsToCache;
			System.out.println("bitsToCache:" + bitsToCache);
			System.out.println("bitsInCache:" + m_bitsInCache);
			System.out.println("val:" + val);
			// print(val);
		}

	}
	public void close()
		throws IOException
	{
		flush();
		m_bos.close();
	}

	public void flush()
		throws IOException
	{
		if(m_bitsInCache < MAX_CACHE_BITS) {
			print(m_cache);
			writeCache(m_cache);
			m_bitsInCache = MAX_CACHE_BITS;
		}
		m_bos.flush();
	}

	/**
	 * writeBits to the file.
	 * @param c the char to write.
	 * if we have surpassed the maxValue then try to
	 * expand the number of bits until it is sufficient.
	 */
	void writeBits(int c)
		throws IOException
	{
		System.out.println("writeBits : " + c);
		if(c >= m_maxValue) {
			while(c>= m_maxValue) {
				setBitLength(++m_bitLen);
				output((int)m_maxValue);
			}
		}
		output(c);


	}

	public static void main(String argv[])
	{
		int a[] = { 0x01,0x00, 0x100,0xFF, 0x010000 };
		try {
			BitOutputStream b_out = new BitOutputStream("bitout.txt");
			b_out.setBitLength(8);

			for(int i=0; i<a.length; ++i) {
				b_out.writeBits(a[i]);
			}
			b_out.close();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}
}
