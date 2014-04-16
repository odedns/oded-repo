package onjlib.net;
import java.net.*;
import java.io.*;

public class EncryptedSocket extends Socket {
	Socket m_socket;

	public EncryptedSocket(Socket s)
	{
		m_socket = s;
	}
	public EncryptedSocket(String host, int port)
		throws UnknownHostException, IOException
	{
		m_socket = new Socket(host,port);
	}
	public InputStream getInputStream() throws IOException
	{
		InputStream is;
		is = new EncryptedInputStream(m_socket.getInputStream());
		return(is);
	}

	public OutputStream getOutputStream() throws IOException
	{
		OutputStream os;
		os = new EncryptedOutputStream(m_socket.getOutputStream());
		return(os);
	}

	public static void main(String argv[])
	{
		try {
			EncryptedSocket es=new EncryptedSocket("localhost",7707);
			DataInputStream dis=new DataInputStream(es.getInputStream());
			DataOutputStream dos=new DataOutputStream(es.getOutputStream());

			DataInputStream stdin = new DataInputStream(System.in);
			String s =null;
			while (true) {
				System.out.print("Enter data: ");
				s = stdin.readLine();
				dos.writeBytes(s + "\r\n");
				s = dis.readLine();
				System.out.println("got : " + s);
			}


		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
