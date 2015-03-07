package onjlib.cs.cmd;

/**
 * @author Oded Nissan 01/03/2004
 *
 * Command interface for a command.
 */
public interface CommandIF {
	public CommandParams execute(CommandParams params) throws Exception;	
}
