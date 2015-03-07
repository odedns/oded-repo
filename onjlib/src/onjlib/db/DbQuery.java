/*
 * Created on 15/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.*;

/**
 * @author P0006439
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DbQuery {

	private Connection m_conn;
	PreparedStatement m_ps;
	
	/**
	 * create a DbQuery.
	 * @param conn an open Db connection.
	 * @param sql the sql string.
	 * @throws SQLException
	 */
	public DbQuery(Connection conn, String sql) throws SQLException
	{
		m_conn = conn;
		m_ps = m_conn.prepareStatement(sql);
	}
	
	/**
	 * create a DbQuery.
	 * @param sql the sql string.
	 * @throws SQLException
	 */
	public DbQuery(String sql)throws SQLException, DbException
	{
		if(m_conn == null) {
			m_conn = DbUtil.getConnection();
		}
		m_ps = m_conn.prepareStatement(sql);
	}
	
	/**
	 * set a parameter in the statement
	 * @param index the position of the parameter.
	 * @param param an Object the paramter.
	 * @param sqlType the sql type representing the parameter.
	 * @throws SQLException in case of error.
	 */
	public void setParam(int index, Object param, int sqlType) throws SQLException
	{
		m_ps.setObject(index,param,sqlType);
		
	}
	
	/**
	 * close the connection and the 
	 * prepared statement.
	 * @throws SQLException in case of error.
	 */
	public void close() throws SQLException
	{
		DbUtil.close(m_conn, m_ps, null);
	}
	
	
	public void addBatch() throws SQLException
	{
		m_ps.addBatch();
	}
	
	/**
	 * @return Returns the m_conn.
	 */
	public Connection getConnection() {
		return m_conn;
	}
	/**
	 * @param conn The m_conn to set.
	 */
	public void setConnection(Connection conn) {
		this.m_conn = conn;
	}
	/**
	 * @return Returns the m_ps.
	 */
	public PreparedStatement getPreparedStatement() {
		return m_ps;
	}
	/**
	 * @param m_ps The m_ps to set.
	 */
	public void setPreperaedStatement(PreparedStatement ps) {
		this.m_ps = ps;
	}
}
