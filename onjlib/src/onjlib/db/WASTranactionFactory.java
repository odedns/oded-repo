/*
 * Created on 26/10/2005
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
public class WASTranactionFactory {

	

	private static final String TRX_MANAGER_FACTORY_51 = "com.ibm.ws.Transaction.TransactionManagerFactory";

		
	public static TransactionManager getTransactionManager()
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
	
	
	public static void foo()
	{
		TransactionManager manager = getTransactionManager();
		if(null != manager) {
			System.out.println("got trx manager : " + manager.getClass().getName());
		}
		try {
			manager.begin();
			// Transaction trx = manager.getTransaction();
			DbService srv = new DbService();
			srv.beginTransaction();
			DbQuery query = srv.createQuery("INSERT INTO ON_TEAMS VALUES (?,?,?)");
			query.setParam(1,new Integer(5),java.sql.Types.INTEGER);
			query.setParam(2,new String("Giants"),java.sql.Types.VARCHAR);
			query.setParam(3,new String("New York"),java.sql.Types.VARCHAR);
			srv.executeUpdate(query);			
			manager.rollback();
			System.out.println("insert rpllback");
			//System.out.println("insert commit");
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
