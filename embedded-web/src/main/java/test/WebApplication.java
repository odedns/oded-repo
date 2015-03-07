/**
 * File: WebApplication.java
 * Date: May 22, 2014
 * Author: I070659
 */
package test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author I070659
 *
 */
public class WebApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(8080);
		 
        WebAppContext context = new WebAppContext();
      //  context.setDescriptor(webapp+"/WEB-INF/web.xml");
        context.setResourceBase(".");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        server.setHandler(context);
 
        try {
			server.start();
	        server.join();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
