/*
 * Created on 07/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author P0006439
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ConnectionManager {

	public static String JDBC_DRIVER = "jdbc.driver";
	public static String JDBC_URL = "jdbc.url";
	public static String JDBC_USER = "jdbc.user";
	public static String JDBC_PASSWORD = "jdbc.password";
	
	public abstract void init(Properties props) throws DbException;
	public abstract Connection getConnection() throws DbException;

	/**
	 * validate the connection.
	 * @param conn the Connection to validate.
	 * @return true if valid false otherwise.
	 */
	public boolean validate(Connection conn) 
	{
		boolean res = true;
		try {
			Statement st = conn.createStatement();
			st.close();
		} catch (SQLException e) {
			res = false;
		} 
			
		return(res);
		
	}
}
