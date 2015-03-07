/**
 * RemoteResponse.java
 * Mar 19, 2008
 */
package remoting;

import java.io.Serializable;

/**
 * @author Oded Nissan
 *
 */
public class RemoteResponse implements Serializable {

	/**
	 * RemoteResponse.java long
	 */
	private static final long serialVersionUID = 1L;
	private Object result;
	private Exception exception;
	
	
	/**
	 * default constructor.
	 */
	public RemoteResponse()
	{
		exception = null;
		result = null;
	}


	/**
	 * get the result Object.
	 * 
	 * @return Object the result object.
	 */
	public Object getResult()
	{
		return result;
	}


	/**
	 * set the result object.
	 * @param result Object the result object.
	 */
	public void setResult(Object result)
	{
		this.result = result;
	}


	/**
	 * get the Exception that occured in remote method invocation.
	 * @return Exception in case of an error.
	 */
	public Exception getException()
	{
		return exception;
	}


	/**
	 * set the Exception that occured in remote method invocation.
	 * @param exception the Exception.
	 */
	public void setException(Exception exception)
	{
		this.exception = exception;
	}

}
