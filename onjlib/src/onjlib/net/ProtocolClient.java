package onjlib.net;

import java.net.*;
import java.io.*;

abstract class ProtocolClient {
	protected BufferedReader m_dis;
	protected BufferedWriter m_dos;
	protected Socket m_sock;

	ProtocolClient(String host, int port)
		throws IOException
	{
		connect(host,port);
	}

	ProtocolClient()
	{
	}

	void connect(String host, int port)
		throws IOException
	{
		m_sock = new Socket(host, port);
		m_dis = new BufferedReader(new InputStreamReader(m_sock.getInputStream()));
		m_dos = new BufferedWriter(new OutputStreamWriter(m_sock.getOutputStream()));
	}

	void close()
		throws IOException
	{
		m_dis.close();
		m_dos.close();
		m_sock.close();
	}
	//{{DECLARE_CONTROLS
	//}}
}
