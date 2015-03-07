package onjlib.utils.logger;


/**
 * Log class for generating log records.
 * The log records are handled by the LogQueue class which is
 * responsible for storing LogMessage objects in a queue and
 * retrieving the messages from the queue.
 * The log class provides methods for generating trace error and information
 * records.
 */
public class Log {
	static final String TRACE = "TRACE";
	static final String ERROR = "ERROR";
	static final String INFO  = "INFO";
	static final String QUIT  = "QUIT";

	static Log m_log = null;
	static LogQueue m_logQueue = null;

	/**
	 * virtual constructor creates an instance of the Log class
	 * if an instance has not been created.
	 * @return Log
	 */
	public static Log getInstance()
	{
		if(null == m_log) {
			m_log = new Log();
		}
		return(m_log);
	}

	/**
	 * set the LogQueue object to use for handling the
	 * log messages.
	 * @param lq the LogQueue object to use.
	 */
	public static void setLogQueue(LogQueue lq)
	{
		m_logQueue = lq;
		m_logQueue.start();
	}

	/**
	 * close the logging mechanism.
	 * Handle all records currently in the queue and
	 * close the log mechanism.
	 */
	public static void close()
	{
		writeMessage(Log.QUIT,null);
	}

	/**
	 * generate and error log record.
	 * @param msg string containing the message.
	 */
	public static void ERROR(String msg)
	{
		writeMessage(Log.ERROR,msg);
	}

	/**
	 * generate an info log record.
	 * @param msg string containing the message.
	 */
	public static void INFO(String msg)
	{
		writeMessage(Log.INFO,msg);
	}

	/**
	 * generate a trace log record.
	 * @param msg string containing the message.
	 */
	public static void TRACE(String msg)
	{
		writeMessage(Log.TRACE,msg);
	}

	private static void writeMessage(String type, String msg)
	{
		m_logQueue.add(type,msg);
	}


	public static void main(String argv[])
	{
		LogQueue lq = new LogQueue("foo", 5, 5000);
		Log.setLogQueue(lq);
		for(int i = 0; i < 10; ++i) {
			Log.ERROR("some error message " + i);
			Log.TRACE("some trace message " + i);
			Log.INFO("some info message " + i);
		}
		Log.close();
	}
}
