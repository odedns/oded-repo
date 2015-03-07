/**
 * Date: 29/07/2007
 * File: TestJmsService.java
 * Package: tests
 */
package tests;

import static org.junit.Assert.*;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;

import onjlib.j2ee.JmsService;
import onjlib.j2ee.ServiceLocator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Oded Nissan
 *
 */
public class TestJmsService {
	 public final static String JMS_FACTORY="java:/XAConnectionFactory";
  
	  public final static String QUEUE="queue/A";
	  private static ConnectionFactory cf = null;
	  private static Destination dest = null;
	  private static JmsService service=null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");
		cf = (ConnectionFactory) ServiceLocator.getInstance().findObject(JMS_FACTORY, QueueConnectionFactory.class);
		dest = (Destination) ServiceLocator.getInstance().findObject(QUEUE, Queue.class);
		service = new JmsService(cf,dest);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service.close();
		
	}

	/**
	 * Test method for {@link onjlib.j2ee.JmsService#sendMessage(javax.jms.Message)}.
	 */
	
	@Test
	public void testSendMessageMessage() throws JMSException {
		System.out.println("testSendMessageMessage");
		TextMessage m = service.getSession().createTextMessage();
		m.setText("foo");
		service.send(m);
	}

	/**
	 * Test method for {@link onjlib.j2ee.JmsService#sendMessage(javax.jms.Destination, javax.jms.Message)}.
	 */
	@Test
	public void testSendMessageDestinationMessage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link onjlib.j2ee.JmsService#receiveMessage()}.
	 * @throws JMSException 
	 */
	@Test
	public void testReceiveMessage() throws JMSException {
		System.out.println("testReceiveMessage");
		Message m = service.receive(10000);
		if(m == null) {
			fail("no message received");
		} else {
			System.out.println("got msg  = " + m.toString());
		}
	}
	
	public static void main(String[] args) {
	 org.junit.runner.JUnitCore.main("tests.TestJmsService");
	}
}
