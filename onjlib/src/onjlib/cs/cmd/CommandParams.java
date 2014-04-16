package onjlib.cs.cmd;

import java.io.Serializable;

/**
 * @author Oded Nissan 01/03/2004.
 *
 * The CommandParams class is an abstract class representing the
 * parameters passed to the command.
 * All parameter classes should extend this class.
 * The class implements the Serializable interface and is a place holder
 * for the class name of the server command to be executed.
 * 
 */
public abstract class CommandParams implements Serializable {
	String m_cmdClassName;
	static final long serialVersionUID = 123;


	public String getCommandClassName()
	{
		return(m_cmdClassName);	
	}
	
	public void setCommandClassName(String className)
	{	
		m_cmdClassName = className;
	}


}
