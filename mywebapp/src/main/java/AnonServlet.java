
import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class AnonServlet extends HttpServlet 
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
	public void service(HttpServletRequest req,
	                    HttpServletResponse res)
			    throws IOException
	{
		String url = req.getParameter("url");
		try {
			res.sendRedirect(url);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}

