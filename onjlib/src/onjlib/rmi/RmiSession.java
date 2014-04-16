
package onjlib.rmi;

import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

public class RmiSession extends UnicastRemoteObject implements RmiSessionIF {
	String m_data;
		public RmiSession() throws RemoteException
		{
			super();
		long l = System.currentTimeMillis();
		m_data = Long.toString(l);
		}

	public void setData(String s)
	{
		System.out.println("in RmiSessio.setData: " + s);
		m_data = s;
	}
	public String getData()
	{
		System.out.println("in RmiSessio.getData ");
		return(m_data);
	}


}
