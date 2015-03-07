import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestServlet extends HttpServlet
{

	/**
	 * @init
	 * initialize function.
	 * called once for the servlet.
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
	}
	/**
	 * @service
	 * the servlet service request.
	 * called once for each servlet request.
	 */
	public void service(HttpServletRequest servReq,
	                    HttpServletResponse servRes)
			    throws IOException
	{
		PrintWriter out = servRes.getWriter();

		String action = servReq.getParameter("action");

		if(null != action && "reload".equals( action )) {
		       out.println("<H2>This is the Reloaded OutputPage </H2>");
			out.flush();
		} else {
		        out.println("<H2>This is the TestSErvlet OutputPage </H2>");
		        out.println("<a href=\"/a1.html\"> link to a1.html </a>");
			out.flush();
		}
		/*
		OutputThread ot = new OutputThread(out);
		ot.start();
		System.out.println("OutputThread started service() finished");
		*/
		try {
			Thread.currentThread().sleep(10000);
			out.println("<SCRIPT Language=JavaScript> location.replace('/servlet/TestServlet?action=reload')</SCRIPT>");
		} catch(Exception e) {
			e.printStackTrace();
		}


	}
}

