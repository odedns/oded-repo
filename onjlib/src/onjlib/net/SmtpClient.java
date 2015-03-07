
package onjlib.net;
import java.io.*;
import java.util.*;


/**
 * this class implements the SMTP protocol.
 * It serves as an SMTP client for sending SMTP messages.
 * @author Oded Nissan
 */
public class SmtpClient extends ProtocolClient {
	/**
	 * the SMTP server port.
	 */
	static final int SMTP_PORT 	= 25;
	/**
	 * SMTP RCPT command.
	 */
	static final String RCPT_TO 	= "RCPT TO:";
	/**
	 * SMTP MAIL command.
	 */
	static final String MAIL_FROM  	= "MAIL FROM:";
	/**
	 * these header fields should appear after
	 * the DATA command.
	 */
	static final String SUBJECT    	= "Subject:";
	static final String CC		= "CC:";
	static final String BCC		= "BCC:";
	/**
	 * SMTP DATA command.
	 */
	static final String DATA	= "DATA\n";
	/**
	 * SMTP QUIT command.
	 */
	static final String QUIT	= "QUIT\n";
	/**
	 * SMTP success reply status.
	 */
	static final int HELLO_CODE		= 220;
	static final int OK_CODE		= 250;
	static final int ENTER_MAIL_CODE	= 354;
	static final int QUIT_CODE		= 221;

	/*
	 * 211 System status, or system help reply
	 * 214 Help message
	 * 220 <domain>     Service ready
	 */


	/**
	 * connect to the SMTP server, which is assumed to be
	 * the localhost.
	 * @exception IOException
	 */
	public SmtpClient()
		throws ProtocolClientException
	{
		try {
			super.connect("localhost",SMTP_PORT);
		} catch(IOException ie) {
			throw new ProtocolClientException(
					ProtocolClientException.ERR_IOERROR,
					ie.getMessage());
		}
		handleReply();

	}

	/**
	 * connect to the SMTP server.
	 * @param host the SMTP server to connect to.
	 * the localhost.
	 * @exception IOException
	 */
	public SmtpClient(String host)
		throws ProtocolClientException
	{
		try {
			super.connect(host,SMTP_PORT);
		} catch(IOException ie) {
			throw new ProtocolClientException(
					ProtocolClientException.ERR_IOERROR,
					ie.getMessage());
		}
		handleReply();
	}


	/**
	 * specify the sender of the message.
	 * @param sender the email address of the sender.
	 * @exception IOException
	 */
	public void setSender(String sender)
		throws ProtocolClientException
	{
		try {
			m_dos.write(MAIL_FROM + sender + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(
					ProtocolClientException.ERR_IOERROR,
					ie.getMessage());
		}
		handleReply();
	}

	/**
	 * add a receipient to the message.
	 * @param receipient the receipient of the message.
	 * @exception IOException
	 */
	public void addReceipient(String receipient)
		throws ProtocolClientException
	{
		try {
			m_dos.write(RCPT_TO + receipient + '\n');
		} catch(IOException ie) {
			throw new ProtocolClientException(
					ProtocolClientException.ERR_IOERROR,
					ie.getMessage());
		}
		handleReply();

	}

	/**
	 * send a message to the receipients.
	 * @param msg the message to be sent.
	 * @exception IOException
	 */
	public void sendMessage(String subject,String msg)
		throws ProtocolClientException
	{
		try {
			m_dos.write(DATA);
			m_dos.write(SUBJECT + subject + '\n');
			m_dos.write(msg + '\n');
			m_dos.write(".\n");
		} catch(IOException ie) {
			throw new ProtocolClientException(
					ProtocolClientException.ERR_IOERROR,
					ie.getMessage());
		}
		handleReply();
		handleReply();

	}

	void handleReply()
		throws ProtocolClientException
	{
		String reply = null;
		int errCode = -1;

		try {
			reply = m_dis.readLine();
			StringTokenizer st = new StringTokenizer(reply);
			String code = st.nextToken();
			errCode = Integer.parseInt(code);
		} catch(NumberFormatException nf) {
			throw new ProtocolClientException(
				ProtocolClientException.ERR_INVALIDCODE,
				nf.getMessage());

		}
		catch (IOException ie) {
			throw new ProtocolClientException(
				ProtocolClientException.ERR_IOERROR,
				ie.getMessage());

		}
		switch(errCode) {
			case HELLO_CODE:
			case OK_CODE:
			case ENTER_MAIL_CODE:
			case QUIT_CODE:
				break;
			default:
				throw new ProtocolClientException(errCode,
						reply);
		}

	}
	public static void main(String argv[])
	{
		try {
			SmtpClient smtp = new SmtpClient("sne451");
			smtp.setSender("odedn@sne451");
			smtp.addReceipient("odedn@amdocs.com");
			smtp.sendMessage("subject", "test message");
			smtp.close();
		} catch(ProtocolClientException se) {
			se.printStackTrace();
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}

	}

}
