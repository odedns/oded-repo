
package onjlib.jcron;

import java.util.Date;

public class JCronTask {
	String m_name;
	String m_desc;
	String m_path;
	String m_args[];
	int m_type;
	Date m_dates[];
	int m_occurs;
	int m_numOccurs;
	Date m_validTo;


	public JCronTask(String name, String desc)
	{
		m_name = name;
		m_desc = desc;
	}

	public void setName(String name, String desc)
	{
		m_name = name;
		m_desc = desc;
	}

	public String getName()
	{
		return(m_name);
	}

	public String getDescription()
	{
		return(m_desc);
	}

	public void setProgram(String path, String args[])
	{
		m_path = path;
		m_args = args;
	}

	public void setSchedule(Date dates[], int occurs, int numOccurs)
	{
		m_dates = dates;
		m_occurs = occurs;
		m_numOccurs = numOccurs;
	}

	public void setValidDate(Date validTo)
	{
		m_validTo = validTo;
	}
}
