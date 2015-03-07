/**
 * RemoteRequest.java
 * Mar 19, 2008
 */
package remoting;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Oded Nissan
 *
 */
public class RemoteRequest implements Serializable {
	/**
	 * RemoteRequest.java long
	 */
	private static final long serialVersionUID = 1L;
	private String remoteClassName;
	private String methodName;
	private ArrayList<?> params;
	
	
	/**
	 * default empty constructor.
	 */
	public RemoteRequest()
	{
		
	}
	
	/**
	 * create a RemoteRequest
	 * @param c the class for the request.
	 * @param methodName the method to invoke.
	 * @param params the method params.
	 */
	public RemoteRequest(String className, String methodName, ArrayList params)
	{
		this.remoteClassName = className;
		this.methodName = methodName;
		this.params = params;		
	}
	
	
	/**
	 * get the remote class
	 * @return String the remote class.
	 */
	public String getRemoteClass()
	{
		return remoteClassName;
	}
	/**
	 * set the remote class
	 * @param remoteClass Class
	 */
	public void setRemoteClass(String remoteClass)
	{
		this.remoteClassName = remoteClass;
	}
	
	/**
	 * get the method name.
	 * @return String the method name.
	 */
	public String getMethodName()
	{
		return methodName;
	}
	
	/**
	 * set the method name.
	 * @param methodName String the method name.
	 */
	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}
	
	/**
	 * the method params.
	 * @return ArrayList of method params.
	 */
	public ArrayList<?> getParams()
	{
		return params;
	}
	/**
	 * set the method params.
	 * @param params ArrayList of method params.
	 */
	public void setParams(ArrayList<?> params)
	{
		this.params = params;
	}

}
