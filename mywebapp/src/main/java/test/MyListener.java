/**
 * Date: 07/03/2007
 * File: MyListener.java
 * Package: test
 */
package test;

import java.util.Locale;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author a73552
 *
 */
public class MyListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed");
		String info = event.getServletContext().getServerInfo();
		System.out.println("server info: " + info);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("contextInitialized");
		//Locale.setDefault(Locale.GERMAN);
		System.out.println("Locale = " + Locale.getDefault());
		System.out.println("user.lang= " + System.getProperty("user.language"));
		System.out.println("user.region= " + System.getProperty("user.region"));

	}

}
