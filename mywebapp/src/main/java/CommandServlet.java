import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


/**
 * @version 	1.0
 * @author
 */
public class CommandServlet extends HttpServlet {

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("in doGet");
		PrintWriter out = resp.getWriter();
		out.print("in doGet");
		out.print("content length : " + req.getContentLength());		
		Enumeration e = req.getHeaderNames();
    if (e.hasMoreElements()) {
      out.println("<h1>Request headers:</h1>");
      out.println("<pre>");
      while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        out.println(" " + name + ": " + req.getHeader(name));
      }
      out.println("</pre>");
    }

    e = req.getParameterNames();
    if (e.hasMoreElements()) {
      out.println("<h1>Servlet parameters (Single Value style):</h1>");
      out.println("<pre>");
      while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        out.println(" " + name + " = " + req.getParameter(name));
      }
      out.println("</pre>");
    }

    e = req.getParameterNames();
    if (e.hasMoreElements()) {
      out.println("<h1>Servlet parameters (Multiple Value style):</h1>");
      out.println("<pre>");
      while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String vals[] = (String []) req.getParameterValues(name);
        if (vals != null) {
          out.print("<b> " + name + " = </b>");
          out.println(vals[0]);
          for (int i = 1; i<vals.length; i++)
            out.println("           " + vals[i]);
        }
        out.println("<p>");
      }
      out.println("</pre>");
    }

    out.println("<h1>Request Attributes:</h1>");
    e = req.getAttributeNames();
    if (e.hasMoreElements()) {
      out.println("<pre>");
      while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
	Object o = req.getAttribute(name);
	if (o == null) continue;
        out.println(" " + name + ": type=" + o.getClass().getName() + " str='" + o.toString() + "'");
      }
      out.println("</pre>");
    }
    out.println("</body></html>");

	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			
			System.out.println("in doPost");
			System.out.println("content length : " + req.getContentLength());
			int length = req.getContentLength();
			
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			try {
				if(length > 0) {	
					HashMap hm  = (HashMap) ois.readObject();							
					hm.put("3","three");					
					System.out.println("got hm = " + hm.toString());									
					ObjectOutputStream os = new ObjectOutputStream(resp.getOutputStream());
					os.writeObject(hm);
					os.close();
					
				}
			} catch(ClassNotFoundException cnf) {
				cnf.printStackTrace();
				throw new ServletException(cnf.getMessage());	
			}
			
	}

}
