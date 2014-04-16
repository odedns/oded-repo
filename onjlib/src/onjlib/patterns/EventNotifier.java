package onjlib.patterns;

import java.util.*;

/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class EventNotifier implements EventNotifierIF {

	LinkedList m_list;
	/**
	 * Constructor for EventNotifier.
	 */
	public EventNotifier() {
		super();
		m_list = new LinkedList();
	}

	/**
	 * add a handler to the list of handlers
	 * to be notified of an event.
	 * @param handler the EventHandler to be added to
	 * the list.
	 * @see onjlib.patterns.EventNotifierIF#addHandler(EventHandlerIF)
	 */
	public void addHandler(EventHandlerIF handler) {
		m_list.add(handler);
	}

	/**
	 * remove a handler from the list of handlers
	 * to be notified of an event.
	 * @param handler the EventHandler to be removed from
	 * the list.
	 * @see onjlib.patterns.EventNotifierIF#removeHandler(EventHandlerIF)
	 */
	public void removeHandler(EventHandlerIF handler) {
		m_list.remove(handler);
	}

	/**
	 * send an event to all handlers in the notifier's
	 * list.
	 * @param e the EventObject to be send.
	 * @see onjlib.patterns.EventNotifierIF#sendEvent(EventObject)
	 */
	public void sendEvent(EventObject e) {
		Iterator iter = m_list.iterator();
		EventHandlerIF h = null;
		while(iter.hasNext()) {
			h = (EventHandler) iter.next();	
			h.handleEvent(e);
		}
	}

}
