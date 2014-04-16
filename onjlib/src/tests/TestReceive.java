/**
 * Date: 29/07/2007
 * File: TestReceive.java
 * Package: tests
 */
package tests;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;

import onjlib.j2ee.JmsService;
import onjlib.j2ee.ServiceLocator;

import org.junit.BeforeClass;

/**
 * @author a73552
 *
 */
public class TestReceive {
	 public static final  String JMS_FACTORY="java:/XAConnectionFactory";
	  
	  public  final static String QUEUE="queue/A";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  ConnectionFactory cf = null;
		  Destination dest = null;
		  JmsService service=null;
		  try {
			System.out.println("setUpBeforeClass");
			cf = (ConnectionFactory) ServiceLocator.getInstance().findObject(JMS_FACTORY, QueueConnectionFactory.class);
			dest = (Destination) ServiceLocator.getInstance().findObject(QUEUE, Queue.class);
			service = new JmsService(cf,dest);
			Message m = service.receive(10000);
			if(null == m ) {
				System.out.println("no message");
			} else {
				System.out.println("m=" + ((TextMessage)m).getText());			
			}
			service.close();
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
	}

}
