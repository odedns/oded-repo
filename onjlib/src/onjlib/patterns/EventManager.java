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
public class EventManager {

	private static HashMap m_map = new HashMap();
	/**
	 * Constructor for EventManager.
	 */
	private EventManager() {
		super();
	}
	
	/**
	 * Register a notfier to the EventManager.
	 * @param name the name that will identify
	 * the notifier.
	 * @param notifier the EventNotifier to register.
	 */
	public static synchronized void addNotifier(String name, EventNotifierIF notifier)
	{
		m_map.put(name,notifier);		
	}
	
	/**
	 * Remove a notfieir from the EventManager.
	 * @param name the name that will identify
	 * the notifier.
	 * @param notifier the EventNotifier to register.
	 */
	public static synchronized void removeNotifier(String name)
	{
		m_map.remove(name);		
	}
	/**
	 * Register a notfier to the EventManager.
	 * @param name the name that will identify
	 * the notifier.
	 * @param notifier the EventNotifier to register.
	 */
	public static synchronized void addHandler(String name, EventHandlerIF handler)
		throws Exception 
	{
		EventNotifierIF notifier = (EventNotifierIF) m_map.get(name);		
		if(null == notifier) {
			throw new Exception("Notifier not found : " + name);
		}
		notifier.addHandler(handler);
		m_map.put(name,notifier);
	}
	
	/**
	 * Remove a notfieir from the EventManager.
	 * @param name the name that will identify
	 * the notifier.
	 * @param notifier the EventNotifier to register.
	 */
	public static synchronized void removeHandler(String name, EventHandlerIF handler)
		throws Exception
	{
		EventNotifierIF notifier = (EventNotifierIF) m_map.get(name);		
		if(null == notifier) {
			throw new Exception("Notifier not found : " + name);
		}
		notifier.removeHandler(handler);
		m_map.put(name,notifier);
	}
	

}
