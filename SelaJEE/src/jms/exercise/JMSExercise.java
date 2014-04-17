/**
 * 
 */
package jms.exercise;

import java.util.Properties;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author ....
 *
 */
public class JMSExercise {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("init");
		try {
			createPublisher();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createPublisher() throws Exception
	{
		
		InitialContext ic = getInitialContext();
		TopicConnectionFactory tcf = (TopicConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		TopicConnection conn = tcf.createTopicConnection("jee","1234");
		conn.start();
		TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) ic.lookup("jms/topic/test");
		TopicPublisher publisher = session.createPublisher(topic);
		TextMessage msg = session.createTextMessage();
		msg.setText("topic msg");
		publisher.publish(msg);
		publisher.close();
		conn.close();
		System.out.println("msg sent");	
		
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
