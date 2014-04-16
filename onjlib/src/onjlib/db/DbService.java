/*
 * Created on 16/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.*;
import java.util.*;

/**
 * @author  Oded Nissan  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class DbService {

	private boolean m_trxFlag = false;
	private Connection m_conn;
	private List m_queryList;
	private TrxManager m_trx;
	private boolean m_autoCommit = false;
	private static String m_trxClassName = "onjlib.db.LocalTrxManager";
	/**
	 * create a DbService object.
	 * @throws DbException
	 */
	public DbService() throws DbException
	{
		m_conn = DbUtil.getConnection();
		m_queryList = new LinkedList();
		try {
			m_trx = (TrxManager) Class.forName(m_trxClassName).newInstance();
			m_trx.setConnection(m_conn);
		} catch(Exception e){
			throw new DbException("Error initializing Transaction manager", e);
		}
		
		try {
			m_conn.setAutoCommit(m_autoCommit);
		} catch(SQLException se) {
			throw new DbException("setAutoCommit failed", se);
		}
	}
	
	/**
	 * create a DbService object.
	 * @param conn a Jdbc connection to be passed.
	 * @throws DbException
	 */
	public DbService(Connection conn) throws DbException
	{
		m_conn = conn;
		m_queryList = new LinkedList();
		try {
			m_trx = (TrxManager) Class.forName(m_trxClassName).newInstance();
			m_trx.setConnection(m_conn);
		} catch(Exception e){
			throw new DbException("Error initializing Transaction manager", e);
		}
		
		try {
			m_conn.setAutoCommit(m_autoCommit);
		} catch(SQLException se) {
			throw new DbException("setAutoCommit failed", se);
		}
	}
	
	/**
	 * create a DbQuery.
	 * @param sql the sql to use for the query
	 * @return DbQuery object.
	 * @throws SQLException incase of error.
	 */
	public DbQuery createQuery(String sql) throws DbException
	{
		DbQuery query=null;
		try {
			query = new DbQuery(m_conn, sql);
		} catch(SQLException sqe) {
			throw new DbException("Error in createQuery",sqe);
		}
		m_queryList.add(query);
		return(query);
	}
	
	/**
	 * excetue a query using the DBQuery Object.
	 * @param query the DbQuery object.
	 * @return ResultSet containing the results.
	 * @throws DbException in case of error.
	 */
	public ResultSet executeQuery(DbQuery query) throws DbException
	{
		ResultSet rs = null;
		try {			
			rs =query.m_ps.executeQuery();
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}
		
		return(rs);
	}
	
	/**
	 * excetue a query using the DBQuery Object.
	 * @param sql  the sql query.
	 * @return ResultSet containing the results.
	 * @throws DbException in case of error.
	 */
	public ResultSet executeQuery(String sql) throws DbException
	{
		
		DbQuery query = null;
		try {
			query = new DbQuery(sql);
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}		
		return(executeQuery(query));
	}
	
	/**
	 * excetue a query using the DBQuery Object.
	 * @param query the DbQuery object.
	 * @return Object the object returned.
	 * @throws DbException in case of error.
	 */
	public Object executeQuerySingle(DbQuery query) throws DbException
	{
		ResultSet rs = null;
		Object o = null;
		try {			
			rs =query.m_ps.executeQuery();
			if(!rs.next()) {
				return(null);
			}
			o = rs.getObject(1);
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}
		
		return(o);
	}
	

	/**
	 * excetue a query using the DBQuery Object.
	 * @param sql the query string.
	 * @return Object the object returned.
	 * @throws DbException in case of error.
	 */
	public Object executeQuerySingle(String sql) throws DbException
	{
		DbQuery query = null;
		try {
			query = new DbQuery(sql);
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuerySingle",sqe);
		}
		return(executeQuerySingle(query));
	}

	
	/**
	 * excetue a query using the DBQuery Object.
	 * @param query the DbQuery object.
	 * @param ResultSetHandler the callback handler 
	 * for the ResultSet. 
	 * @throws SQLException in case of error.
	 */
	public void executeQuery(DbQuery query, ResultSetHandler handler) throws DbException
	{
		ResultSet rs = null;
		try {			
			rs =query.m_ps.executeQuery();
			while(rs.next()) {
				handler.handleRow(rs);
			}
			rs.close();
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}
		
		
	}

	/**
	 * excetue a query using the DBQuery Object.
	 * @param sql the sql query string.
	 * @return Page containing the results.
	 * @throws DbException in case of error.
	 */
	public Page executeQueryEx(String sql) throws DbException
	{
		DbQuery query = null;
		try {
			query = new DbQuery(sql);
		} catch(SQLException sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}		
		return(executeQueryEx(query));
	}
	
	/**
	 * excetue a query using the DBQuery Object.
	 * @param query the DbQuery object.
	 * @return Page containing the results.
	 * @throws DbException in case of error.
	 */
	public Page executeQueryEx(DbQuery query) throws DbException
	{
		ResultSet rs = null;
		Page p = null;
		try {			
			rs =query.m_ps.executeQuery();
			p = new Page(rs);
			rs.close();
		} catch(Exception sqe) {
			throw new DbException("Error in executeQuery",sqe);
		}
		
		return(p);
	}
	
	/**
	 * excute an update using the DBQuery Object.
	 * @param query the DbQuery object.
	 * @throws DbException
	 */
	public void executeUpdate(DbQuery query) throws DbException
	{
		
		try {
			query.m_ps.execute();		
		} catch(SQLException sqe) {
			throw new DbException("Error in executeUpdate",sqe);
		}
		/*
		 * if there is no open transaction
		 * we call commit.
		 */
		if(!m_trxFlag){
			commit();
		}
	}
	
	/**
	 * excute an update using the DBQuery Object.
	 * @param sql  the sql query.
	 * @throws DbException
	 */
	public void executeUpdate(String sql) throws DbException
	{
		DbQuery query = null;
		try {
			query = new DbQuery(sql);
		} catch(SQLException sqe) {
			throw new DbException("Error in executeUpdate",sqe);
		}
		executeUpdate(query);
	}
	/**
	 * execute a batch of statements.
	 * @param query The DbQuery object.
	 * @throws SQLException in case of error.
	 */
	public void executeBatch(DbQuery query) throws DbException
	{
		try {
			query.m_ps.executeBatch();
		} catch(SQLException sqe) {
			throw new DbException("Error in executeBatch",sqe);
		}
		/*
		 * if there is no open transaction
		 * we call commit.
		 */
		if(!m_trxFlag){
			commit();
		}
	}
	
	/**
	 * commit all queries in this transaction.
	 * @throws SQLException
	 */
	public void commit() throws DbException
	{	
		m_trxFlag = false;	
		m_trx.commit();		
	}
	
	/**
	 * rollback the current transaction.
	 * @throws SQLException
	 */
	public void rollback() throws DbException
	{
		m_trxFlag = false;
		m_trx.rollback();		
	}
	
	/**
	 * start a transaction.
	 * All subsequent execute operations must
	 * be terminated by a commit call.
	 */
	public void beginTransaction() throws DbException
	{
		m_trxFlag = true;
		m_trx.beginTransaction();
	}
	
	/**
	 * close the connection used
	 * Loop over all queries created by the service and
	 * commit them.
	 * by the DbService.
	 */
	public void close() throws DbException
	{
		m_trxFlag = false;
		Iterator iter = m_queryList.iterator();
		try {
			while(iter.hasNext()) {
				DbQuery query = (DbQuery)iter.next();			
				query.m_ps.close();
			}		
			m_conn.close();
		} catch(SQLException sqe) {
			throw new DbException("Error in close", sqe);
		}
	}
	
	/**
	 * close  quitelythe connection used
	 * Loop over all queries created by the service and
	 * commit them.
	 * by the DbService.
	 */
	public void closeQuitely() 
	{
		try {
			close();
		} catch(DbException sqe) {
		}
	}
	
	/**
	 * Close a DbQuery.
	 * @param query the DbQuery.
	 * @throws SQLException
	 */
	public void closeQuery(DbQuery query) throws DbException
	{
		m_queryList.remove(query);
		try {
			query.m_ps.close();
		} catch(SQLException sqe) {
			throw new DbException("Error in closeQuery", sqe);
		}
	}
	
	/**
	 * @return Returns the m_autoCommit.
	 */
	public boolean isAutoCommit() {
		return m_autoCommit;
	}
	/**
	 * @param commit The autoCommit to set.
	 */
	public void setAutoCommit(boolean commit) {
		m_autoCommit = commit;
	}
	/**
	 * Returns the Transaction Manager class name
	 * @return String the Transaction Manager class name.
	 */
	public String getTrxClassName() {
		return m_trxClassName;
	}
	/**
	 * @param trxClassName The trxClassName to set.
	 */
	public static void setTrxClassName(String trxClassName) {
		m_trxClassName = trxClassName;
	}
	/**
	 * @return Returns the Transaction Manager assosiated
	 * with the service.
	 */
	public TrxManager getTransactionManager() {
		return m_trx;
	}
}
