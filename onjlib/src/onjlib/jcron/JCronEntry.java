
package onjlib.jcron;

import java.util.Date;

/**
 * JCronEntry an entry describing a Jcron task.
 * The entry contains the required information on how to run the program
 * and when to schedule it.
 */
public class JCronEntry {
	String m_name;
	String m_desc;
	String m_path;
	String m_args[];
	int m_type;
	Date m_dates[];
	int m_occurs;
	int m_numOccurs;
	Date m_validTo;


	/**
	 * Constructor creates a JCronEntry object.
	 * @param name the name of the entry.
	 * @desc a description of the entry
	 */
	public JCronEntry(String name, String desc)
	{
		m_name = name;
		m_desc = desc;
	}

	/**
	 * Sets the name and description of the entry.
	 * @param name the name of the entry.
	 * @desc a description of the entry
	 */
	public void setName(String name, String desc)
	{
		m_name = name;
		m_desc = desc;
	}

	/**
	 * get the name of the entry.
	 * @return name the entry name.
	 */
	public String getName()
	{
		return(m_name);
	}

	/**
	 * get the description of the entry.
	 * @return name the entry description.
	 */
	public String getDescription()
	{
		return(m_desc);
	}

	/**
	 * sets the path of the program to execute
	 * and its arguments.
	 * @param path the path to the program to execute.
	 * @param args[] the coomand line arguments for the program.
	 */
	public void setProgram(String path, String args[])
	{
		m_path = path;
		m_args = args;
	}

	/**
	 * get the program path.
	 * @return String the path of the program to execute.
	 */
	public String getProgram()
	{
		return(m_path);
	}

	/**
	 * get the program arguments.
	 * @return String[] the arguments of the program to execute.
	 */
	public String[] getProgramArgs()
	{
		return(m_args);
	}

	public void setSchedule(Date dates[], int occurs, int numOccurs)
	{
		m_dates = dates;
		m_occurs = occurs;
		m_numOccurs = numOccurs;
	}

	/**
	 * set the last date that the entry will
	 * be valid on.
	 * @param validTo the last date the entry will be valid on.
	 */
	public void setValidDate(Date validTo)
	{
		m_validTo = validTo;
	}
}
