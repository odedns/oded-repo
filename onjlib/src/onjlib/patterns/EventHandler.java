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
public class EventHandler implements EventHandlerIF {

	/**
	 * Constructor for EventHandler.
	 */
	public EventHandler() {
		super();
	}

	/**	 
	 * Handle the event sent to the handler
	 * by the notifier.
	 * @param e the EventObject to handle.
	 * @see onjlib.patterns.EventHandlerIF#handleEvent(EventObject)
	 */
	public void handleEvent(EventObject e) {
		
		System.out.println("Thread : " + Thread.currentThread().getName() + " handleEvent() got event : " + e.toString());
	}

}
