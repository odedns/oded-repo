/*
 * Created on 24/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.utils.threads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Worker extends Thread {

	private List m_workItems;
	private boolean m_stopFlag = false;
	private Object m_lockObject;
	/**
	 * 
	 */
	public Worker(List workItems) {
		m_workItems = workItems;
	}
	
	public Worker(WorkIF work)
	{
		m_workItems = new LinkedList();
		m_workItems.add(work);
	}

	/**
	 * Raise the stopFlag so that 
	 * the worker will stop when comleting the next
	 * work item.
	 *
	 */
	public void stopWorker()
	{
		m_stopFlag = true;
	}
		
	
	/**
	 * runs the thread.
	 * Do the work for this worker.
	 */
	public void run()
	{
		WorkIF work = null;
		Iterator iter = m_workItems.iterator();
		while(iter.hasNext()) {
			work = (WorkIF) iter.next();
			if(m_stopFlag) {
				break;
			}
			work.doWork();
			synchronized(work) {
				work.notify();
			}
		} // while
	}
	

}
