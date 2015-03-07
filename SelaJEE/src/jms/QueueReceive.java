
package jms;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This example shows how to establish a connection to
 * and receive messages from a JMS queue. The classes in this
 * package operate on the same JMS queue. Run the classes together to
 * witness messages being sent and received, and to browse the queue
 * for messages.  This class is used to receive and remove messages
 * from the queue.
 *
 * @author Copyright (c) 1999-2003 by BEA Systems, Inc. All Rights Reserved.
 */
public class QueueReceive
  implements MessageListener
{
 
 // Defines the JMS context factory.
  public final static String JMS_FACTORY="jms/RemoteConnectionFactory";


  // Defines the queue.
  public final static String QUEUE="jms/queue/test";

  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueReceiver qreceiver;
  private Queue queue;
  private boolean quit = false;

/**
 * Message listener interface.
 * @param msg  message
 */

  // MessageListener interface
  public void onMessage(Message msg)
  {
    try {
      String msgText;
      if (msg instanceof TextMessage) {
        msgText = ((TextMessage)msg).getText();
      } else {
        msgText = msg.toString();
      }

      System.out.println("Message Received: "+ msgText );

      if (msgText.equalsIgnoreCase("quit")) {
        synchronized(this) {
          quit = true;
          this.notifyAll(); // Notify main thread to quit
        }
      }
    } catch (JMSException jmse) {
      jmse.printStackTrace();
    }
  }

  /**
   * Creates all the necessary objects for receiving
   * messages from a JMS queue.
   *
   * @param   ctx	JNDI initial context
   * @param	queueName	name of queue
   * @exception NamingException if operation cannot be performed
   * @exception JMSException if JMS fails to initialize due to internal error
   */
  public void init(Context ctx, String queueName)
       throws NamingException, JMSException
  {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    qcon = qconFactory.createQueueConnection("jee","1234");
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qreceiver = qsession.createReceiver(queue);
    qreceiver.setMessageListener(this);
    qcon.start();
  }

  /**
   * Closes JMS objects.
   * @exception JMSException if JMS fails to close objects due to internal error
   */
  public void close()
       throws JMSException
  {
    qreceiver.close();
    qsession.close();
    qcon.close();
  }
/**
  * main() method.
  *
  * @params args  WebLogic Server URL
  * @exception  Exception if execution fails
  */

  public static void main(String[] args)
       throws Exception
  {
  	
    InitialContext ic = getInitialContext();
    QueueReceive qr = new QueueReceive();
    qr.init(ic, QUEUE);
    
    System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message).");

    // Wait until a "quit" message has been received.
    synchronized(qr) {
      while (! qr.quit) {
        try {
          qr.wait();
        } catch (InterruptedException ie) {}
      }
    }
    qr.close();
  }

  private static InitialContext getInitialContext()
       throws NamingException
  {
	  Properties jndiProps = new Properties();
      jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,  org.jboss.naming.remote.client.InitialContextFactory.class.getName());
      jndiProps.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
      jndiProps.setProperty(Context.PROVIDER_URL, "remote://localhost:4447");
      jndiProps.put(Context.SECURITY_PRINCIPAL, "jee");
      jndiProps.put(Context.SECURITY_CREDENTIALS, "1234");
      InitialContext ctx = new InitialContext(jndiProps);
      return(ctx);
  }

}




