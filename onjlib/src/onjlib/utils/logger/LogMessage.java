package onjlib.utils.logger;

/**
 * LogMessage class containing the message.
 */
class LogMessage {
	/**
	 * type is the type of message to be logged.
	 */
	String m_type;
	/**
	 * the timestamp of the logged message.
	 */
	String m_timeStamp;
	/**
	 * the message text.
	 */
	String m_message;

	/**
	 * constructor create a LogMessage object.
	 * @param type the message type.
	 * @param timeStamp the message time stamp.
	 * @param msg the message text.
	 */
	LogMessage(String type, String timeStamp, String msg)
	{
		m_type = type;
		m_timeStamp = timeStamp;
		m_message = msg;
	}

	/**
	 * Format the message information into a string.
	 * @return String the formatted message.
	 */
	public String toString()
	{
		return(m_type + "-" + m_timeStamp + ":" + m_message);
	}
}
