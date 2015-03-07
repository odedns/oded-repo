package onjlib.patterns;

import java.util.EventObject;

/**
 * Send events to the events handlers.
 * Each notifier has a list of event handlers that it notifies
 * of the event occurance.
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface EventNotifierIF {
	
	/**
	 * add a handler to the list of handlers
	 * to be notified of an event.
	 * @param handler the EventHandler to be added to
	 * the list.
	 */
	public abstract void addHandler(EventHandlerIF handler);
	/**
	 * remove a handler from the list of handlers
	 * to be notified of an event.
	 * @param handler the EventHandler to be removed from
	 * the list.
	 */
	public abstract void removeHandler(EventHandlerIF handler);
	/**
	 * send an event to all handlers in the notifier's
	 * list.
	 * @param e the EventObject to be send.
	 */
	public abstract void sendEvent(EventObject e);

}
