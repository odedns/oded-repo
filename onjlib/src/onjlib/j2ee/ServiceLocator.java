/*
 * Created on 12/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.j2ee;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceLocator {

	private static ServiceLocator m_service = null;
	private Map m_map;
	private InitialContext m_ctx;
	private static Properties m_props = null;
	
	/**
	 * return the singleton instance.
	 * @return ServiceLocator the object.
	 */
	public static synchronized ServiceLocator getInstance() throws NamingException
	{
		if(m_service == null) {
			m_service = new ServiceLocator();			
		}
		return(m_service);
		
	}
	
	
	/**
	 * private constructor cannot 
	 * instantiate class.
	 * @throws NamingException
	 *
	 */
	private ServiceLocator() throws NamingException
	{		
		m_map = new HashMap();
		if(null == m_props) {
			m_ctx = new InitialContext();
		} else {
			m_ctx = new InitialContext(m_props);
		}
	}
	
	
	public static void setProperties(Properties props)
	{
		m_props = props;
	}
	
	/**
	 * Find an object in the jndi tree. first check the local cache 
	 * then access jndi to retrieve the object.
	 * @param jndiName the name to lookup
	 * @param c the class  of the object to lookup.
	 * @return Object the retrieved object.
	 * @throws NamingException in case of error.
	 */
	public Object findObject(String jndiName, Class c) throws NamingException
	{
		Object o;
		if(null == (o= m_map.get(jndiName))) {			
			o = PortableRemoteObject.narrow(m_ctx.lookup(jndiName),c);
			synchronized(m_map) {
				m_map.put(jndiName,o);
			}
		}
		return(o);
		
	}
	
	
}
