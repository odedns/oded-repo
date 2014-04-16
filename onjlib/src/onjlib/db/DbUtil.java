/*
 * Created on 15/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.*;

/**
 * @author Oded Nissan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DbUtil {

	
	/**
	 * empty private constructor.
	 * cannot instantiate class.
	 */
	private DbUtil()
	{	
	}
	
	/**
	 * Get a connection from the current connection manager.
	 * @return Connection an open connection.
	 * @throws DbException in case of error.
	 */
	public static Connection getConnection() throws DbException
	{
		return( ConnectionManagerFactory.getInstance().getConnectionManager().getConnection());
	}
	
	/**
	 * close an open connection quitely.
	 * @param conn the connection to close.
	 * @throws SQLException in case of error.
	 */
	public static void closeConnectionQuitely(Connection conn) 
	{
		closeQuitely(conn, null, null);
	}
	
	
	
	/**
	 * close an open connection.
	 * @param conn the connection to close.
	 * @throws SQLException in case of error.
	 */
	public static void closeConnection(Connection conn) throws SQLException
	{
		if(null != conn) {
			conn.commit();
			conn.close();
		}
	}
	
	/**
	 * close connection result set and statement.
	 * @param conn the connection to close
	 * @param st the Statement to close.
	 * @param rs the ResultSet to close.
	 * @throws SQLException
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) throws SQLException
	{
		if(null != rs) {
			rs.close();
		}
		if(null != st) {
			st.close();
		}
		closeConnection(conn);
	}
	/**
	 * close connection result set and statement.
	 * @param conn the connection to close
	 * @param st the Statement to close.
	 * @param rs the ResultSet to close.
	 */
	public static void closeQuitely(Connection conn, Statement st, ResultSet rs) 
	{
		try {
			if(null != rs) {
				rs.close();
			}
			if(null != st) {
				st.close();
			}
			closeConnection(conn);
		} catch(SQLException se) {
			
		}
	}
}