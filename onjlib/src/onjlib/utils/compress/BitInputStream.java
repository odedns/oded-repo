
package onjlib.utils.compress;

import java.io.*;

public class BitInputStream extends InputStream {
	static final int GROW_BITS = 255;
	static final int MAX_CACHE_BITS = 32;
	int m_bitLen = 8;
	long m_cache = 0;
	int m_bitsInCache = 32;
	BufferedInputStream m_bis;
	double m_maxValue;

	BitInputStream(String name)
		throws IOException
	{
		FileInputStream fin = new FileInputStream(name);
		m_bis = new BufferedInputStream(fin);
		setBitLength(m_bitLen);
	}

	void setBitLength(int len)
	{
		m_maxValue = (1L << len) - 1;
		m_bitLen = len;
		System.out.println("bits: " + m_bitLen + " maxvalue: " + m_maxValue);
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

	void readCache()
		throws IOException
	{
		byte b[] = new byte[8];
		m_bis.read(b,0,4);
		m_cache = bytesToLong(b);
	}


	void input()
		throws IOException
	{
		int bitsToCache;
		int i=0;
		int bits = m_bitLen;
		long val = 0;
		int c=0;

		while(i < bits) {
			if(m_bitsInCache == 0) {
			//	flush cache.
				print(m_cache);
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

	public int read() throws IOException
	{
		return(0);
	}
}
