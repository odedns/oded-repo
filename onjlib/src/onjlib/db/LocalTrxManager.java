/*
 * Created on 09/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.SQLException;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocalTrxManager extends TrxManager {

	/**
	 * @param conn
	 */
	public LocalTrxManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#commit()
	 */
	public void commit() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_conn.commit();			
			m_conn.setAutoCommit(true);
		} catch(SQLException se) {
			throw new DbException("setAutoCommit failed", se);
		}

	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#rollback()
	 */
	public void rollback() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_conn.rollback();
			m_conn.setAutoCommit(true);
		} catch(SQLException se) {
			throw new DbException("setAutoCommit failed", se);
		}

	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#beginTransaction()
	 */
	public void beginTransaction() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_conn.setAutoCommit(false);
		} catch(SQLException se) {
			throw new DbException("setAutoCommit failed", se);
		}

	}

}
