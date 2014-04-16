

package onjlib.net;

import java.net.*;
import java.io.*;


/**
 * This class implements the Ftp protocol.
 * It serves as an ftp client class which performs
 * operations on the ftp server.
 */
public class FtpClient extends ProtocolClient {

	static final int PORT 		= 21;
	static final int DATA_PORT 	= 2000;
	static final char ASCII_TYPE 	= 'A';
	static final char BINARY_TYPE 	= 'I';

	static final String LIST_CMD = "LIST";
	static final String NLIST_CMD = "NLST";
	static final String USER_CMD = "USER";
	static final String PASS_CMD = "PASS";
	static final String PWD_CMD  = "PWD";
	static final String MKDIR_CMD  = "MKD";
	static final String RMDIR_CMD  = "RMD";
	static final String TYPE_CMD = "TYPE";
	static final String CWD_CMD  = "CWD";
	static final String RETR_CMD  = "RETR";
	static final String DELETE_CMD = "DELE";
	static final String STOR_CMD  = "STOR";
	static final String APPEND_CMD  = "APPEND";
	static final String ALLOCATE_CMD  = "ALLO";
	static final String QUIT_CMD = "QUIT";

	ServerSocket m_dataSock;
	/**
	 * constructor creates the FtpClient object.
	 */
	public FtpClient()
	{
	}

	/**
	 * constructor creates the FtpClient object.
	 * @param host the ftp server to connect to.
	 * @exception IOException.
	 */
	public FtpClient(String host)
		throws IOException
	{
		open(host);
	}

	/**
	 * close the connection to the ftp server.
	 * @exception IOException
	 */
	void close()
		throws IOException
	{
		super.close();
		m_dataSock.close();
	}
	/**
	 * open the connection to the ftp server.
	 * @param host the ftp server host name.
	 * @exception IOException
	 */
	void open(String host)
		throws IOException
	{
		connect(host,PORT);
		handleReply();
		m_dataSock = new ServerSocket(DATA_PORT);
	}

	/**
	 * list the files on the server directory.
	 * @param dir the directory to list.
	 * @fullListing a boolean variable specifying whether a short
	 * or a long list should be created.
	 * @return String a string holding the directory listing.
	 */
	String listFiles(String dir, boolean fullListing )
	{
		StringBuffer sb = new StringBuffer(1024);
		String s = null;
		String cmd = (fullListing  ? LIST_CMD : NLIST_CMD);
		try {
			m_dos.write(cmd  + " " + dir + '\n');
			handleReply();
			Socket sock = m_dataSock.accept();
			BufferedReader br =
				new BufferedReader(new InputStreamReader(sock.getInputStream()));
			while(null != (s = br.readLine())) {
				sb.append(s);
				sb.append('\n');
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return(sb.toString());
	}


	void port(int dataPort)
	{
		InetAddress in = null;
		byte b[] = null;
		short s = (short)dataPort;
		try {
			in = InetAddress.getLocalHost();
			b = in.getAddress();
			m_dos.write("PORT " + (int)(b[0]&0xff) + "," +
					(int)(b[1]&0xff) + "," +
					(int)(b[2]&0xff) + "," +
					(int)(b[3]&0xff) + "," +
					(int)((s & 0xff00) >> 8) + "," +
					(int) (s & 0x00ff) + '\n');
			handleReply();
		} catch (UnknownHostException ue) {
			ue.printStackTrace();
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	void putFile(String file)
	{
		byte buf[] = new byte[4096];
		FileInputStream fis = null;
		int len = 0;
		try {
			fis = new FileInputStream(file);
			m_dos.write(STOR_CMD + " " + file + '\n');
			handleReply();
			Socket sock = m_dataSock.accept();
			DataOutputStream dos =
				new DataOutputStream(sock.getOutputStream());
			while(-1 != (len = fis.read(buf))) {
				dos.write(buf,0,len);
			}
			fis.close();
			dos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	void getFile(String file)
	{
		byte buf[] = new byte[4096];
		FileOutputStream fos = null;
		int len = 0;

		try {
			fos = new FileOutputStream(file);
			m_dos.write(RETR_CMD + " " + file + '\n');
			handleReply();
			Socket sock = m_dataSock.accept();
			DataInputStream dis =
				new DataInputStream(sock.getInputStream());
			while(-1 != (len = dis.read(buf))) {
				fos.write(buf,0,len);
			}
			fos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}

	}
	void login(String user, String pass)
	{
		try {
			m_dos.write(USER_CMD + " " + user + '\n');
			handleReply();
			m_dos.write(PASS_CMD + " " + pass + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}


	/**
	 * print the current directory on the server.
	 * @return String the current directory.
	 */
	String pwd()
	{
		String s = null;
		try {
			m_dos.write(PWD_CMD + '\n');
			s = m_dis.readLine();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return(s);
	}


	void append()
	{
		try {
			m_dos.write(APPEND_CMD + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	void lcd()
	{
	}

	void allocate()
	{
		try {
			m_dos.write(ALLOCATE_CMD  + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	void deleteFile(String file)
	{
		try {
			m_dos.write(DELETE_CMD + " " + file + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	void setType(char type)
	{
		try {
			m_dos.write(TYPE_CMD + " " + type + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * create a directory on the server.
	 * @param dir the directory to create.
	 */
	void mkDir(String dir)
	{
		try {
			m_dos.write(CWD_CMD + " " + dir + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * change directory on the server.
	 * @dir the directory to change to.
	 */
	void chDir(String dir)
	{
		try {
			m_dos.write(CWD_CMD + " " + dir + '\n');
			handleReply();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * handle server reply.
	 * print out the reply.
	 */
	void handleReply()
	{
		String s = null;
		try {
			s = m_dis.readLine();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		System.out.println("reply = " + s);
	}


	public static void main(String argv[])
	{
		try {
			FtpClient fc = new FtpClient("sne451");

			fc.login("odedn","odedn0004");
			System.out.println(fc.pwd());
			fc.port(DATA_PORT);
			fc.chDir("work");
			System.out.println("list = " + fc.listFiles("*.java", false));
			// fc.putFile("jdk12.bat");
			fc.close();
		} catch(IOException ie) {
			ie.printStackTrace();
		}


	}
} /* class FtpClient */
