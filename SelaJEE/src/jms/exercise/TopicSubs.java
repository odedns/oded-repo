/**
 * 
 */
package jms.exercise;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

/**
 * @author I070659
 *
 */
public class TopicSubs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InitialContext ic = new InitialContext();
			TopicConnectionFactory tcf = (TopicConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
			TopicConnection conn = tcf.createTopicConnection("jee","1234");
			conn.setClientID("dd");
			conn.start();

			
			
			TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = (Topic) ic.lookup("jms/topic/test");
		//	TopicSubscriber subs = session.createSubscriber(topic);
			TopicSubscriber subs = session.createDurableSubscriber(topic, "iiii");
	
			TextMessage msg = (TextMessage) subs.receive();
		
			System.out.println("got msg =" + msg.getText());	
			
			subs.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
