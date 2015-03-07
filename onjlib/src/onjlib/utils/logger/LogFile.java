package onjlib.utils.logger;
public class LogFile {
	long m_sizeLimit;

	/**
	 * Logfile open a logfile with the specified name and
	 * size limit.
	 * @param fname the name of the logfile.
	 * @param sizeLimit the limit of the file in KB.
	 */
	public LogFile(String fname, long sizeLimit)
	{
		m_sizeLimit=sizeLimit;
	}

	/**
	 * write a message to the Logfile, if the logfile size
	 * exceeds sizelimit, the file will be backed-up and reopened.
	 * the date will be appended to the logfile name.
	 */
	public void write(String msg)
	{
	}
}
