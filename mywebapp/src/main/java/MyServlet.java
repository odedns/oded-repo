import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet 
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
		String name;
		String value[];
		String val;

		servRes.setHeader("AUTHORIZATION", 
				"user fred:mypassword");
		ServletOutputStream out = servRes.getOutputStream();

		HttpSession session = servReq.getSession(true);
		session.setAttribute("timemilis",
				new Long(System.currentTimeMillis()));
		if(session.isNew()) {
			out.println("<p> Session is new ");
		} else {
			out.println("<p> Session is not new ");
		}
		Long l = (Long)session.getAttribute("timemilis");
		out.println("<p> Session id = " + session.getId());
		out.println("<p> TimeMillis = " + l);

		out.println("<H2>Servlet Params</H2>");
		Enumeration e = servReq.getParameterNames();
		while(e.hasMoreElements()) {
			name = (String)e.nextElement();
			value = servReq.getParameterValues(name);
			out.println(name + " : ");
			for(int i = 0; i < value.length; ++i) {
				out.println(value[i]);
			}
			out.println("<p>");
		}


		out.println("<H2> Request Headers : </H2>");
		e = servReq.getHeaderNames();
		while(e.hasMoreElements()) {
			name = (String)e.nextElement();
			val = (String) servReq.getHeader(name);
			out.println("<p>"  + name + " : " + val);
		}
		try {
			BufferedReader br = servReq.getReader();
			String line = null;
			while(null != (line = br.readLine())) {
				out.println(line);
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		
		session.invalidate();

	}
}

