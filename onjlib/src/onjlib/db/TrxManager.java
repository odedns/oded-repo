/*
 * Created on 09/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class TrxManager {

	protected Connection m_conn;
	/**
	 * Constructor.
	 * @param conn the connection associated with the transaction.
	 */
	public TrxManager() 
	{
	
	}
	
	/**
	 * commit all queries in this transaction.
	 * @throws SQLException
	 */
	public abstract void commit() throws DbException;
	/**
	 * rollback the current transaction.
	 * @throws SQLException
	 */
	public abstract void rollback() throws DbException;
	
	/**
	 * start a transaction.
	 * All subsequent execute operations must
	 * be terminated by a commit call.
	 */
	public abstract void beginTransaction() throws DbException;
	
	
	

	/**
	 * @return Returns the m_conn.
	 */
	public Connection getConnection() {
		return m_conn;
	}
	/**
	 * @param m_conn The m_conn to set.
	 */
	public void setConnection(Connection conn) {
		this.m_conn = conn;
	}
}
