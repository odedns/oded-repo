/**
 * File: HelloServlet.java
 * Date: May 22, 2014
 * Author: I070659
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author I070659
 *
 */
@SuppressWarnings("serial")
@WebServlet ("/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("got request in HelloServlet");
		PrintWriter pw = resp.getWriter();
		pw.print("<p>This is HelloServlet : " + new Date().toString());
		pw.flush();
	}

}
