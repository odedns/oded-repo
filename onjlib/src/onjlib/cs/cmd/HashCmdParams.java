package onjlib.cs.cmd;

import java.util.*;

/**
 * @author Oded Nissan 01/03/2004
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HashCmdParams extends CommandParams {
	
	HashMap m_hash;


	public HashCmdParams()
	{
		m_hash = new HashMap();	
	}
	
	public void setParam(String name, Object value)
	{
		m_hash.put(name,value);	
	}	
	
	
	public Object getParam(String name)
	{
		return(m_hash.get(name));	
	}
	public String toString()
	{
		return(m_cmdClassName + ':' + m_hash.toString());	
	}
}
