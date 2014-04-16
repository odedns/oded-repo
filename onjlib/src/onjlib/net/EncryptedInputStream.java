package onjlib.net;
import java.io.*;


public class EncryptedInputStream extends InputStream {
	InputStream m_is;
	public EncryptedInputStream(InputStream is)
	{
		m_is =is;
	}

	public int available() throws IOException
	{
		return(m_is.available());
	}

	public void close() throws IOException
	{
		m_is.close();
	}

	public void mark(int readLimit)
	{
		m_is.mark(readLimit);
	}
	public void reset() throws IOException
	{
		m_is.reset();
	}

	public boolean markSupported()
	{
		return(m_is.markSupported());
	}


	public int read() throws IOException
	{
		System.out.println("Encrypted: read()");
		return(m_is.read());
	}

	public int read(byte b[]) throws IOException
	{
		System.out.println("Encrypted: read()");
		return(m_is.read(b));
	}

	public int read(byte b[], int off, int len) throws IOException
	{
		System.out.println("Encrypted: read()");
		return(m_is.read(b,off,len));
	}

	public long skip(long n) throws IOException
	{
		return(m_is.skip(n));
	}

}

