package onjlib.net;
import java.io.*;

public class EncryptedOutputStream extends OutputStream {
	OutputStream m_os;
	public EncryptedOutputStream(OutputStream os)
	{
		m_os = os;
	}

	public void flush() throws IOException
	{
		m_os.flush();
	}

	public void close() throws IOException
	{
		m_os.close();
	}


	public void write (int b) throws IOException
	{
		System.out.println("Encrypted: write()");
		m_os.write(b);
	}

	public void write(byte b[]) throws IOException
	{
		System.out.println("Encrypted: write() b = " + b);
		m_os.write(b);
	}

	public void write(byte b[], int off, int len) throws IOException
	{
		System.out.println("Encrypted: write() b = " + b);
		m_os.write(b,off,len);
	}

}

