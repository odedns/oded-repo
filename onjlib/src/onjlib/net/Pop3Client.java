
package onjlib.net;
import java.io.*;


public class Pop3Client extends ProtocolClient {

	/**
	 * the POP3 port.
	 */
	static final int POP3_PORT	= 110;
	/**
	 * POP3 HELO command.
	 */
	static final String HELO_CMD 	= "HELO";
	/**
	 * POP3 LIST command.
	 */
	static final String LIST_CMD 	= "LIST";
	/**
	 * POP3 DELE command.
	 */
	static final String DELETE_CMD 	= "DELE";
	/**
	 * POP3 STAT command.
	 */
	static final String STAT_CMD 	= "STAT";
	/**
	 * POP3 QUIT command.
	 */

	/**
	 * POP3 USER command.
	 */
	static final String USER_CMD 	= "USER";
	/**
	 * POP3 PASS command.
	 */
	static final String PASS_CMD 	= "PASS";
	/**
	 * POP3 QUIT command.
	 */
	static final String QUIT_CMD 	= "QUIT";
	static final String OK_REPLY	= "+OK";
	static final String ERR_REPLY	= "-ERR";

	/**
	 * connect to the pop3 server on the localhost.
	 * @exception  IOException in case the connection failed.
	 */
	public Pop3Client()
		throws ProtocolClientException
	{
		try {
			super.connect("localhost",POP3_PORT);
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}

	}

	/**
	 * connect to the pop3 server on the specifie host.
	 * @param host the POP3 host to connect to.
	 * @exception  IOException in case the connection failed.
	 */
	public Pop3Client(String host)
		throws ProtocolClientException
	{
		try {
			super.connect(host,POP3_PORT);
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}
	}
	public void login(String user, String password)
		throws ProtocolClientException
	{
		try {
			m_dos.write(USER_CMD + user + '\n');
			m_dos.write(PASS_CMD + password + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}

	}

	public int stat()
		throws ProtocolClientException
	{
		try {
			m_dos.write(STAT_CMD + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}
		return(0);
	}

	public String list(int msgNum)
		throws ProtocolClientException
	{
		try {
			m_dos.write(LIST_CMD + msgNum + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}
		return(null);
	}

	public String retrieve(int msgNo)
	{
		return(null);
	}

	public void delete(int msgNum)
		throws ProtocolClientException
	{
		try {
			m_dos.write(DELETE_CMD + msgNum + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}
	}

	void handleReply()
		throws ProtocolClientException
	{
		String reply = null;

		try {
			reply = m_dis.readLine();
			if(reply.startsWith(ERR_REPLY)) {
				throw new ProtocolClientException(reply);
			}
		} catch (IOException ie) {
			throw new ProtocolClientException(ie.getMessage());
		}
	}

}
