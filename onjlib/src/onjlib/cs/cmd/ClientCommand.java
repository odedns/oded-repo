package onjlib.cs.cmd;

/**
 * @author Oded Nissan 01/03/2004
 *
 * The ClientCommand is an abstract implementation of a client 
 * command that can execute a server command by calling the 
 * execServerCmd method.
 *  
 */
public abstract class ClientCommand implements CommandIF {
	
	String m_serverCmdName = null;
	
	/**
	 * constructor
	 * @param serverCmdName the class name of the server
	 * command to execute.
	 */	
	public ClientCommand(String serverCmdName)
	{
		m_serverCmdName = serverCmdName;
	}	
	
	/**
	 * execute the client command.
	 * @param params the CommandParams object the parameters
	 * of the command.
	 */
	public abstract CommandParams execute(CommandParams params) throws Exception;
	/**
	 * run the server side command.
	 */
	private CommandParams execServerCmd(CommandParams params) throws Exception
	{
		CommandParams outParams = null;
		if(null != m_serverCmdName) {
			outParams = CSManager.executeCommand(m_serverCmdName, params);
		}
		return(outParams);	
	}

}
