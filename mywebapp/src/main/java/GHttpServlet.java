
import java.util.Hashtable;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GHttpServlet extends HttpServlet
{
	Hashtable m_sessionTable;

	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		m_sessionTable= new Hashtable();
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

		//HttpSession session = req.getSession(true);
		HttpSession session = getSession(req);
		ServletOutputStream out = res.getOutputStream();

		res.setContentType("text/html");
		if(session.isNew()) {
			out.println("<p> Session is new ");
			out.println("<p> Session id : " + session.getId());
		} else {
			out.println("<p> Session is not new ");
			out.println("<p> Session id : " + session.getId());
		}
		out.println("<p> URL : " + res.encodeRedirectURL("aa.html"));
		Long l = (Long)session.getAttribute("timemilis");
		out.println("<p> Session id = " + session.getId());
		out.println("<p> TimeMillis = " + l);

//		session.invalidate();

	}


	HttpSession getSession(HttpServletRequest req)
	{
		HttpSession session;
		String sessionId = req.getParameter("WeblogicSession");
		if(sessionId == null) {
			System.out.println("new session ..");
			session = req.getSession(true);
			sessionId = session.getId();
			m_sessionTable.put(sessionId,session);
		} else {
			System.out.println("old session ..");
			session = (HttpSession)m_sessionTable.get(sessionId);
		}
		return(session);
		
	}
}
