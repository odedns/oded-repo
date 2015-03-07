package onjlib.rmi;
/*
 *  RmiClient.java
 *
 *  RmiClient uses RMI for methods in Hello7 class.
 */


import java.rmi.*;

public class RmiClient
{
	public static void main( String args[] )
	{
		String hostName = "localhost";

		if( args.length > 0 )
			hostName = args[0];

		System.setSecurityManager( new RMISecurityManager() );

		try
		{
			RmiIF remote = (RmiIF) Naming.lookup( "rmi://" + hostName +
				":5001/RmiServer" );
			System.out.println( remote.printHello() + "    It's now " + (remote.getTime()).toString() + " at the remote object." );

			RmiIF remote2 = (RmiIF) Naming.lookup( "rmi://" + hostName +
				":5001/RmiServer" );
		ClientSession client1  = remote.connect();
		ClientSession client2  = remote2.connect();
		RmiSessionIF session = client1.getRemoteInterface();
		RmiSessionIF session2 = client2.getRemoteInterface();
		/*
		String url = remote.connectURL();
		String url2 = remote2.connectURL();
		RmiSessionIF session = (RmiSessionIF)Naming.lookup(url);
		RmiSessionIF session2 = (RmiSessionIF)Naming.lookup(url2);
		*/
		System.out.println("client 1 data  = " + session.getData());
		System.out.println("client 2 data  = " + session2.getData());
		session.setData("client1");
		session2.setData("client2");
		System.out.println("client 1 data  = " + session.getData());
		System.out.println("client 2 data  = " + session2.getData());
		}
		catch( Exception e )
		{
		e.printStackTrace();
			System.out.println( e.toString() );
		}
	}
}
