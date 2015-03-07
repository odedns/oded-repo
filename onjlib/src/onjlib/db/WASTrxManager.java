/*
 * Created on 09/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.lang.reflect.Method;

import javax.transaction.TransactionManager;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WASTrxManager extends TrxManager {

	private static final String TRX_MANAGER_FACTORY_51 = "com.ibm.ws.Transaction.TransactionManagerFactory";
	private TransactionManager m_manager;

	/**
	 * @param conn
	 */
	public WASTrxManager() {
		super();
		// TODO Auto-generated constructor stub
		m_manager = getTransactionManager();
		
		
	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#commit()
	 */
	public void commit() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_manager.commit();
		} catch(Exception e) {
			throw new DbException("Error in commit", e);
		}

	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#rollback()
	 */
	public void rollback() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_manager.rollback();
		} catch(Exception e) {
			throw new DbException("Error in rollback", e);
		}

	}

	/* (non-Javadoc)
	 * @see onjlib.db.TrxManager#beginTransaction()
	 */
	public void beginTransaction() throws DbException {
		// TODO Auto-generated method stub
		try {
			m_manager.begin();
		} catch(Exception e) {
			throw new DbException("Error in beginTransaction", e);
		}

	}

	private TransactionManager getTransactionManager()
	{
		Class clazz;
		TransactionManager manager = null;
		try {
			System.out.println("Trying WebSphere 5.1: " + TRX_MANAGER_FACTORY_51);
			clazz = Class.forName(TRX_MANAGER_FACTORY_51);
			Method method = clazz.getMethod("getTransactionManager", (Class[]) null);
			manager = (TransactionManager) method.invoke(null, (Object[]) null);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
		return(manager);					
	}
}
