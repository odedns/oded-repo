package onjlib.patterns;
import java.util.EventObject;

/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface EventHandlerIF {

	/**
	 * Handle the event sent to the handler
	 * by the notifier.
	 * @param e the EventObject to handle.
	 */	
	public abstract void handleEvent(EventObject e);

}
