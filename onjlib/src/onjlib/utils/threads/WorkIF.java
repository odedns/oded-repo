/*
 * Created on 24/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.utils.threads;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface WorkIF {

	
	/**
	 * The doWork method is called by the worker
	 * to perform the work for this work item.
	 */
	public void doWork();
	
	/**
	 * this method is used to notify the caller
	 * when the work has completed.
	 * The worker calls this method when the doWork
	 * method has completed.
	 */
	public void notifyStatus();
	
}
