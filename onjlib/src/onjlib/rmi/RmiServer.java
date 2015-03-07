
package onjlib.rmi;
/*
 *  RmiServer.java
 *
 *  class RmiServer implements the remote interface
 *  Hello7 to identify it as a remote object.
 *  Methods printHello() and getTime() are remote methods
 *  for clients to call.
 */


import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class RmiServer extends UnicastRemoteObject implements RmiIF
{
	int m_cnt = 1;
	public RmiServer() throws RemoteException
	{
		super();
	}

	public String printHello() throws RemoteException
	{
	System.out.println("server: in printHello");
		return( "Howdy!" );
	}

	public Date getTime() throws RemoteException
	{
	System.out.println("server: in getTime ");
		return( new Date( System.currentTimeMillis() ) );
	}

	public String connectURL() throws RemoteException
	{
		String hostname = "localhost";
		String url = null;
	RmiSession session = null;
	System.out.println("server: in connect ");

	++m_cnt;
	try {
		session = new RmiSession();
		url = "rmi://" + hostname + ":5001/RmiSession" + m_cnt;
		System.out.println("url : " + url);
			Naming.rebind(url, session );

	} catch (Exception e) {
		e.printStackTrace();
	}

	return(url);
	 }


	public ClientSession connect() throws RemoteException
	{
		String hostname = "localhost";
		String url = null;
	RmiSession session = null;
	System.out.println("server: in connect ");

	++m_cnt;
	try {
		session = new RmiSession();
		url = "rmi://" + hostname + ":5001/RmiSession" + m_cnt;
		System.out.println("url : " + url);
			Naming.rebind(url, session );

	} catch (Exception e) {
		e.printStackTrace();
	}

	ClientSession csession = new ClientSession(url);
	return(csession);
	 }



	public static void main( String args[] )
	{
		try
		{
			System.setSecurityManager( new RMISecurityManager() );

			RmiServer server = new RmiServer();
		LocateRegistry.createRegistry(5001);
			Naming.rebind( "//:5001/RmiServer", server );
			System.out.println( "RmiServer :  bound in registry" );
		}
		catch( Exception e )
		{
			System.out.println( e.toString() );
		e.printStackTrace();
		}
	}
}
