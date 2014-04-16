package onjlib.patterns.examples;

import java.util.EventObject;

import onjlib.patterns.EventHandler;
import onjlib.patterns.EventManager;
import onjlib.patterns.EventNotifier;



/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class EventThread extends Thread {

	EventHandler m_handler;
	/**
	 * Constructor for EventThread.
	 */
	public EventThread() {
		super();
		m_handler =  new EventHandler();
		try {
			EventManager.addHandler("sampleNotifier", m_handler);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run()
	{
		while(true) {
			try {
				Thread.sleep(10000);
			} catch(InterruptedException ie) {
				ie.printStackTrace();	
			}
		}	
	}

	public static void main(String[] args) {
		EventNotifier n = new EventNotifier();
		EventManager.addNotifier("sampleNotifier",n);
		for(int i=0; i < 5; ++i) {
			EventThread th = new EventThread();
			th.setName("Th-" + i);
			th.start();	
		}
		for(int i=0; i < 3; ++i) {
			n.sendEvent(new EventObject("Notifier: " + n.toString() + 
						" Event-" + i ));
		}
	}
}
