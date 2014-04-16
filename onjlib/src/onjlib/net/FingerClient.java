
package onjlib.net;
import java.io.*;
import java.util.*;


/**
 * This class implements the Finger protocol.
 * It serves as a finger client class which retrieves
 * information from a finger server.
 */
public class FingerClient extends ProtocolClient {
	/**
	 * the finger service port.
	 */
	static final int FINGER_PORT = 79;
	String m_fingerString;

	/**
	 * connect to the finger host specified in the
	 * finger string.
	 * a finger string is in the format user@machine.
	 * @param fingerString the finger string to be sent to the
	 * host.
	 * @exception IOException
	 */
	public FingerClient(String fingerString)
		throws IOException
	{
		int numTokens = 0;
		String host = null;
		StringTokenizer st = new StringTokenizer(fingerString,"@");
		numTokens = st.countTokens();
		switch(numTokens) {
			case 1:
				if(fingerString.charAt(0) == '@') {
					host = st.nextToken();
				} else {
					host = "localhost";
				}
				break;
			case 2:
				host = st.nextToken();
				host = st.nextToken();
				break;
			default:
				break;
		}

		m_fingerString = fingerString;
		super.connect(host,FINGER_PORT);
	}

	/**
	 * connect to the host specified by the host variable
	 * use the finger string for retrieving finger information
	 * @param host the host to connect to.
	 * @param fingerString the finger string to be sent to the
	 * host.
	 * @exception IOException
	 */
	public FingerClient(String host, String fingerString)
		throws IOException
	{
		super(host, FINGER_PORT);
		m_fingerString = fingerString;
	}

	/**
	 * get the finger reply from the finger host.
	 * @return String containing fionger information.
	 */
	public String getReply()
		throws IOException
	{
		String s = null;
		StringBuffer sb = new StringBuffer();

		m_dos.write(m_fingerString + "\r\n");
		s = m_dis.readLine();
		while(null != s) {
			sb.append(s + '\n');
			s = m_dis.readLine();
		}
		return(sb.toString());
	}


	public static void main(String argv[])
	{
		if(argv.length < 1) {
			System.out.println("usage Finger <fingerstring>");
			System.exit(1);
		}
		try {
			FingerClient f = new FingerClient(argv[0]);
			System.out.println("got = \n" + f.getReply());
		} catch(IOException ie) {
			ie.printStackTrace();
		}


	}

	//{{DECLARE_CONTROLS
	//}}
}
