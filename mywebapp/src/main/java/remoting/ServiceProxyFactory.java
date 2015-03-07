/*
 * Created on 06/09/2005
 * 
 */
package remoting;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.apache.log4j.*;


/**
 * @author Oded Nissan
 *
 * This class is a factory used to create service object implementation.
 * The factory reads a property file serviceproxy.properties and determines which object
 * will be returned by the factory: the real object implementation or 
 * a proxy object implementation.
 */
public class ServiceProxyFactory {

	private static final String IMPL = "Impl";
	private final static Logger log = Logger.getLogger(ServiceProxyFactory.class);

	/**
	 * Get the service object implementation.
	 * The object returned will be either the object implementation
	 * or an object proxy, depending on values in a property file.
	 * @param class the class of the service interface.
	 * @return Object the service implementation.
	 * @throws Exception in case of error.
	 */
	public static Object getService(Class<?> serviceIF) throws Exception
	{
		return(getService(serviceIF,true));
	}

	
	
	/**
	 * Get the service object implementation.
	 * The object returned will be either the object implementation
	 * or an object proxy, depending on values in a property file.
	 * @param class the class of the service interface.
	 * @param useProxy boolean indicates if this service should run using a proxy.
	 * @return Object the service implementation.
	 * @throws Exception in case of error.
	 */
	public static Object getService(Class<?> serviceIF,boolean useProxy) throws Exception
	{
		String name = serviceIF.getName();
		return(getService(name,useProxy));
	}
	
	
	/**
	 * Get the service object implementation.
	 * The object returned will be either the object implementation
	 * or an object proxy, depending on values in a property file.
	 * @param name the name of the service ( the key in the property file)
	 * @return Object the service implementation.
	 * @throws Exception in case of error.
	 */
	public static Object getService(String name) throws Exception
	{
		return(getService(name,true));
	}
	
	/**
	 * Get the service object implementation.
	 * The object returned will be either the object implementation
	 * or an object proxy, depending on values in a property file.
	 * @param name the name of the service ( the key in the property file)
 	 * @param useProxy boolean indicates if this service should run using a proxy.
	 * @return Object the service implementation.
	 * @throws Exception in case of error.
	 */
	public static Object getService(String name,boolean useProxy) throws Exception
	{
		Object serviceImpl = null;
		Class<?> serviceInterface= null;
		
		String className = getClassImpl(name);
		log.debug("className=" + className);
		/*
		 * either return the class implementation or 
		 * the proxy object.
		 */
		if(!useProxy) {
			try {
				serviceImpl = Class.forName(className).newInstance();
			} catch(Exception e) {
				throw new Exception("Error creating service implementation", e);
			}
		} else {
			try {
				String interfaceName = name;
				serviceInterface = Class.forName(interfaceName);
			} catch(ClassNotFoundException ce) {
				throw new Exception("Error creating service implementation", ce);
			}			
			serviceImpl = getProxyObject(serviceInterface,className);
			
		}
		return(serviceImpl);
		
	}
	
	
	
	/**
	 * Return the name of the service implementation class.
	 * @param name the name of the service object.
	 * @return String the class name.
	 */
	private static String getClassImpl(String name)
	{
		String className = name + IMPL; 

		return(className);
	}
	

	
	/**
	 * get the proxy object for the service.
	 * @param interfaceClass the service interface class.
	 * @param implClassName the name of the implementation class.
	 * @return Object the proxy service object.
	 */
	private static Object getProxyObject(Class<?> interfaceClass, String implClassName)
	{
		InvocationHandler handler = new ProxyHandler(implClassName);
		ClassLoader cl = interfaceClass.getClassLoader();		
	
		Object proxyObj = Proxy.newProxyInstance(cl,
												  new Class[] { interfaceClass },
												  handler);
                                          
		return(proxyObj);
	}


	/**
	 * get the service url .
	 * @return String the url.
	 */
	public static String getUrl()
	{
		return HttpInvoker.getUrl();
	}


	/**
	 * set the service url
	 * @param url String the url.
	 */
	public static void setUrl(String url)
	{
		HttpInvoker.setUrl(url);
	}
}
