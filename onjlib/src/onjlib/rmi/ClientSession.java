
package onjlib.rmi;

import java.rmi.*;
import java.io.Serializable;
import java.net.MalformedURLException;


public class ClientSession implements Serializable {
	static final long serialVersionUID = 123;
	String m_url;
	RmiSessionIF m_session = null;

	public ClientSession(String url)
	{
		m_url = url;
	}

	public RmiSessionIF getRemoteInterface()
		throws NotBoundException, MalformedURLException,
		RemoteException
	{
		System.out.println("getRemoteInterface url: " + m_url);
		if (null == m_session) {
			m_session = (RmiSessionIF)Naming.lookup(m_url);
		}
		System.out.println("m_session = " + m_session.getData());
			return(m_session);

	}

	public String getURL()
	{
		return(m_url);
	}


}

