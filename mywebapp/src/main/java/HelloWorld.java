import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet
{
	public void service(HttpServletRequest servReq,
	                    HttpServletResponse servRes)
			    throws IOException
	{
		//servRes.setContentType("text/unknown");
		//servRes.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = servRes.getOutputStream();
		servRes.setContentType("text/html");
		//servRes.sendError(-1,"Some fucking error");
		//servRes.addHeader("status", "fucking status");
		out.println("<p>Hello World !!");
		out.println("<br> some more more  %%%%%%@@@@@@@@@ more texxxxt");
		
		String user = servReq.getParameter("userName");
		if(user != null) {
			out.println("<p>userName = " + user);
		} else {
			out.println("<p>no userName given");
		}
		String s = servReq.getRemoteHost();
		if(null != s) {
			out.println("<p>host = " + s);
		}
		s = servReq.getServerName();
		if(null != s) {
			out.println("<p>ServerName = " + s);
		}
		int port = servReq.getServerPort();
		out.println("<p>ServerPort= " + port);
		s = servReq.getContentType();
		if(null != s) {
			out.println("<p>ContentType= " + s);
		}
		port = servReq.getContentLength();
		out.println("<p>ContentLength= " + port);

		




	}
}

