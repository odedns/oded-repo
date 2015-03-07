import java.io.*;

import java.sql.*;

import java.util.*;

import javax.naming.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.*;

/**
 * DOCUMENT ME!
 *
 * @author Arik Yelovitch
 */
public class DBServlet extends HttpServlet{
	/**
	 * @init
	 * initialize function.
	 * called once for the servlet.
	 */
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		System.out.println("DbServlet.init()");
		
	}

	/**
	 * @service
	 * the servlet service request.
	 * called once for each servlet request.
	 */
	public void service(
		HttpServletRequest servReq, HttpServletResponse servRes)
		throws IOException{
		PrintWriter out = servRes.getWriter();
		System.out.println("DbServlet.service()");
		out.println("<h1> This is DBServlet</h1>");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try{
			conn = getConnection();
			out.println("<p>ofekds - gotConnection");
			out.println("<p>Catalog :" + conn.getCatalog());
			
			DatabaseMetaData meta = conn.getMetaData();
			out.println("<h2>Meta Data: </h2>");
			out.println("<p> product name: " + meta.getDatabaseProductName());
			out.println("<p> product version:" + meta.getDatabaseProductVersion());
			out.println("<p> Driver Name: "  + meta.getDriverName());
			out.println("<p> Driver Version: "  + meta.getDriverVersion());
			
			out.println("<h2> Database Schemas </h2>");
			rs = meta.getSchemas();
			while(rs.next()) {
				out.println("<p> TABLE_SCHEMA :"  + rs.getString(1));
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		finally{
			try{
				if (null != conn){
					conn.close();
				}

				if (null != st){
					st.close();
				}

				if (null != rs){
					rs.close();
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	static Connection getConnection() throws Exception{
		javax.naming.InitialContext context = null;
		context = new javax.naming.InitialContext();

		javax.sql.DataSource dataSource = ( javax.sql.DataSource ) context
			.lookup("jdbc/ofekds");

		System.out.println("got Datasource");

		//Connection conn = dataSource.getConnection("odedn","odedn01");
		Connection conn = dataSource.getConnection();

		return (conn);
	}
}
