package onjlib.utils.logger;

import java.util.Vector;
import java.util.Date;

/**
 * the LogQueue class is responsible for storing log messages
 * in a queue, retrieving and handling the messages from the queue
 * when the number of messages exceeds a certain limit.
 * The class runs as a thread which monitors the message queue to see
 * if messages need to be handled. If no messages should be handled
 * the thread sleeps for a predefined period of time.
 */
class LogQueue extends Thread {
	/**
	 * the queue containing the log messages.
	 */
	Vector m_queue;
	/**
	 * the maximum number of messages to be stored in the queue.
	 */
	long m_sizeLimit;
	/**
	 * the time to sleep when inactive, in ms.
	 */
	long m_sleepTime;

	boolean m_continue = true;

	/**
	 * create a LogQueue object.
	 * @param fname the log file name to use.
	 * @param sizelimit the maximum number of records to store
	 * in the queue.
	 * @param sleepTime the time to sleep when inactive in ms.
	 */
	LogQueue(String fname, long sizeLimit, long sleepTime)
	{
		m_queue = new Vector(200);
		m_sizeLimit = sizeLimit;
		m_sleepTime = sleepTime;
	}

	/**
	 * add a log message to the queue.
	 * @param type the type of log record to add.
	 * @param msg the message to add.
	 */
	synchronized void add(String type,String msg)
	{
		Date d = new Date();
		LogMessage logMsg = new LogMessage(type,d.toString(),msg);
		m_queue.addElement(logMsg);
		notify();
	}

	/**
	 * the thread's run method.
	 * Monitors the queue for messages to handle.
	 * If there are not enough messages in the queue, the thread
	 * sleeps.
	 */
	public void run()
	{
		while(m_continue) {
			System.out.println("thread running");
			if(m_sizeLimit < m_queue.size()) {
				while(m_queue.size() > 0 ) {
					processMessage();
				}
			}
			System.out.println("thread sleeping");
			try {
				wait(m_sleepTime);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * process a log message from the message queue.
	 * if the message is a quit message stops the thread.
	 */
	void processMessage()
	{
		LogMessage logMsg = (LogMessage) m_queue.elementAt(0);
		m_queue.removeElementAt(0);
		if(Log.QUIT.equals( logMsg.m_type )) {
			System.out.println("terminating thread");
			m_continue = false;
		}
		System.out.println(logMsg.toString());
	}

}
