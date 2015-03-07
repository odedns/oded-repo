/**
 * 
 */
package jndi;

import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

/**
 * @author Oded Nissan
 *
 */
public class JNDIClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        try {
		        	
		            Properties jndiProps = new Properties();
		          
		            jndiProps.put(Context.SECURITY_PRINCIPAL, "jee3");
		            jndiProps.put(Context.SECURITY_CREDENTIALS, "1234");
		            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		            jndiProps.put(Context.PROVIDER_URL, "remote://localhost:4447");
		            InitialContext ctx = new InitialContext(jndiProps);
		            System.out.println("got initial context");
		        
		            Object data = (Object) ctx.lookup("jms/queue/test");
		            System.out.println("got data = " + data.getClass().getName());
		            
		            NamingEnumeration<NameClassPair> list = ctx.list("");
		            System.out.println("got list :" + list.toString());
		            while (list.hasMore()) {
		            	  System.out.println(list.next().getName());
		            }
		         
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	
}
