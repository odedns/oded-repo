package test;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler.Context;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Server server = new Server(8080);
		 
		// Create static context to serve static files
		
	        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	       
	        context.setContextPath("/");
	        context.setClassLoader(Thread.currentThread().getContextClassLoader());
	        context.setResourceBase(".");
	        ServletHolder holderHome = new ServletHolder();
	        holderHome.setServlet(new DefaultServlet());
	        String dir = Thread.currentThread().getContextClassLoader().getResource(".").toExternalForm();
	        holderHome.setInitParameter("resourceBase",dir);
	        holderHome.setInitParameter("dirAllowed","true");
	        //holderHome.setInitParameter("pathInfoOnly","true");
	        context.addServlet(holderHome,"/");
	        
	        
	        server.setHandler(context);
	       
	 
	        context.addServlet(new ServletHolder(new HelloServlet()),"/hello");
	 
	        try {
				server.start();
		        server.join();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
}
