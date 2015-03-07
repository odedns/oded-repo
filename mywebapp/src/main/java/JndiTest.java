
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

import java.rmi.RemoteException;
import java.util.*;





/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class JndiTest {


	public static void foo() throws Exception
	{
  		String url       = "iiop://localhost:2809";
//  		String url = "corbaloc:iiop:localhost:2809";
  		//String factory = "com.ibm.ejs.ns.jndi.CNInitialContextFactory";
  		String factory = "com.ibm.websphere.naming.WsnInitialContextFactory";
		
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,factory);
		prop.put(Context.PROVIDER_URL, url);
		//prop.put("java.naming.factory.url.pkgs","com.ibm.websphere.naming");
 		Context ctx          = new InitialContext(prop);
 		ctx.bind("test", new String("test"));
 		String s = (String) ctx.lookup("test");
 		ctx.unbind("test");
		System.out.println("\nEnd Mytest session.Client...\n");
 
 	}


	public static void main(String argv[])
	{
		System.out.println("in JNDITest");
		try {
			foo();
		} catch(Exception e) {
			e.printStackTrace();	
		}
			
	}
}
