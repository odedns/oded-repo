package onjlib.db;

import java.util.Properties;

import javax.naming.InitialContext;

import onjlib.utils.PropertyReader;

/**
 * Created on 07/06/2005
 * @author  P0006439  TODO To change the template for this generated type comment go to  Window - Preferences - Java - Code Style - Code Templates
 */
public class ConnectionManagerFactory {
	
	public static String JDBC_PROPS = "jdbc.properties";
	private static ConnectionManagerFactory m_instance = null;
	private static ConnectionManager m_connMng = null;
	private static Properties m_props = null;
	
	
	
	/**
	 * default constructor.
	 */
	public ConnectionManagerFactory()
	{
		
	}
	
	/**
	 * initialize this singleton object.
	 */
	private void init()
	{
		try {
			m_props = PropertyReader.read("/" + JDBC_PROPS);
			String clazzName = m_props.getProperty("default.connectionmanager");
			m_connMng = (ConnectionManager) Class.forName(clazzName).newInstance();
			m_connMng.init(m_props);
		} catch(Exception e) {
			m_connMng = null;
			System.out.println("Error on ConnectionManagerFactory initialization");
			e.printStackTrace();
		}
	}
	
	
	public static ConnectionManagerFactory getInstance()
	{
		if(m_connMng == null) {
			m_instance = new ConnectionManagerFactory();
			m_instance.init();
		}
		return(m_instance);
	}
	
	/**
	 * retrieve a ConnectionManager instance.
	 * @return ConnectionManager the current ConnectionManager.
	 */
	public ConnectionManager getConnectionManager()
	{		
		return(m_instance.getConnectionManager());
	}
	
	
	/**
	 * set the current connection manager to use.
	 * @param mng The ConnectionManager to use.
	 */
	public static void setConnectionManager(ConnectionManager mng)
	{
		m_instance.m_connMng= mng;
	}
}
