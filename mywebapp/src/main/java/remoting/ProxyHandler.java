package remoting;

import java.lang.reflect.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

/**
 * @author P0006439
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProxyHandler implements InvocationHandler
{
	private final static Logger log = Logger.getLogger(ProxyHandler.class);

	private String realClassName;

		
		public ProxyHandler(String className) 		
		{			
			realClassName = className;
		}
		
				 
		/**
		 * implements the InvocationHandler invoke callback.
		 */
		public Object invoke(Object proxy, Method method,
					 Object[] args)
			  throws Throwable
		{
			log.debug("in invoke");			
			//method.invoke(realObj,args);
			
			ArrayList list = new ArrayList();
			for(int i=0; i< args.length; ++i) {
				list.add(args[i]);
			}
			RemoteResponse resp = HttpInvoker.invokeServer(realClassName, method.getName(), list);
			log.debug("after invoke..");
			if(resp.getException() != null) {
				throw new Exception(resp.getException());
			}
			return(resp.getResult());
       	
		}


		public String getRealClassName()
		{
			return realClassName;
		}


		public void setRealClassName(String realClassName)
		{
			this.realClassName = realClassName;
		}
	
}
