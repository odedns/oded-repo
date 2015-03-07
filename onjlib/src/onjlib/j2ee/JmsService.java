/**
 * Date: 23/07/2007
 * File: JmsService.java
 * Package: onjlib.j2ee
 */
package onjlib.j2ee;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * The JmsService class provides utility methods for sending 
 * and receiving messages using JMS destinations.
 * It is created using the JmsServiceFactory.
 * @author Oded Nissan
 *
 */
public class JmsService {

	private Connection m_conn=null;
	private Session m_session=null;
	private MessageProducer m_producer=null;
	private MessageConsumer m_consumer=null;
	private Destination m_dest=null;
	private ConnectionFactory m_cf=null;
	private boolean m_transaction = false;
	private int m_ackMode = Session.AUTO_ACKNOWLEDGE;
	private String m_messageSelector=null;
	
	/**
	 * constructor.
	 *
	 */
	public JmsService() {
		super();		
	}
	
	
	/**
	 * Construct the JmsService Object.
	 * @param cf the ConnectionFactory to use.
	 * @param dest The Destination to use.
	 */
	public JmsService(ConnectionFactory cf, Destination dest) throws JMSException
	{
		m_cf = cf;
		m_dest = dest;
		open();
		
	}

	/**
	 * open the JMS connection and session.
	 * @throws JMSException in case of error.
	 */
	public void open()throws JMSException
	{
		if(m_cf == null) {
			throw new IllegalStateException("No ConnectionFactory object set.");
		}
		if(m_conn == null) {
			m_conn = m_cf.createConnection();			
		}
		if(m_session == null) {
			m_session = m_conn.createSession(m_transaction, m_ackMode);
		}
		if(m_dest != null) {
			m_consumer = m_session.createConsumer(m_dest);
			m_producer = m_session.createProducer(m_dest);
		}
		m_conn.start();
		
	}
	/**
	 * get the Connection
	 * @return Connection
	 */
	public Connection getConnection() {
		return m_conn;
	}

	/**
	 * set the connection.
	 * @param conn Connection to set.
	 */
	public void setConnection(Connection conn) {
		this.m_conn = conn;
	}

	/**
	 * get the message consumer.
	 * @return MessageConsumer.
	 */
	public MessageConsumer getMessageConsumer() {
		return m_consumer;
	}

	/**
	 * set the MessageConsumer.
	 * @param consumer the MessageConsumer to set. 
	 */
	public void setMessageConsumer(MessageConsumer consumer) {
		this.m_consumer = consumer;
	}

	/**
	 * create a MessageConsumer object for the specific Destination
	 * @param dest the Destination to use.
	 * @return MessageConsumer.
	 * @throws JMSException in case of error.
	 */
	public MessageConsumer createMessageConsumer(Destination dest) throws JMSException
	{
		return(m_session.createConsumer(dest));
	}
	
	/**
	 * get the MessageProducer.
	 * @return MessageProducer.
	 */
	public MessageProducer getMessageProducer() {
		return m_producer;
	}
	
	/**
	 * create a producer for a specific destination.
	 * @param dest The Destination to use.
	 * @return MessageProducer object.
	 * @throws JMSException in case of error.
	 */
	public MessageProducer createMessageProducer(Destination dest) throws JMSException
	{
		return(m_session.createProducer(dest));
	}

	/**
	 * set the message producer.
	 * @param producer MessageProducer.
	 */
	public void setMessageProducer(MessageProducer producer) {
		this.m_producer = producer;
	}

	/**
	 * get the JMS session.
	 * @return Session the JMS session.
	 */
	public Session getSession() {
		return m_session;
	}

	/**
	 * set the JMS session
	 * @param session the JMS session
	 */
	public void setSession(Session session) {
		this.m_session = session;
	}
	/**
	 * get the connection factory.
	 * @return ConnectionFactory
	 */
	public ConnectionFactory getConnectionFactory() {
		return m_cf;
	}


	/**
	 * set the  connection factory.
	 * @param cf the ConnectionFactory to set.
	 */
	public void setConnectionFactory(ConnectionFactory cf) {
		this.m_cf = cf;
	}


	/**
	 * get the Destination object.
	 * @return Destination the Destination object.
	 */
	public Destination getDestination() {
		return m_dest;
	}


	/**
	 * set the Destination object.
	 * @param dest the Destination object to set.
	 */
	public void setDestination(Destination dest) {
		this.m_dest = dest;
	}


	/**
	 * get the acknowledge mode.
	 * @return int
	 */
	public int getAckMode() {
		return m_ackMode;
	}


	/**
	 * set the acknowledge mode.
	 * @param mode the value.
	 */
	public void setAckMode(int mode) {
		m_ackMode = mode;
	}
	

	/**
	 * return the transaction flag for the session
	 * @return boolean.
	 */
	public boolean isTtransaction() {
		return m_transaction;
	}


	/**
	 * set the transaction mode flag.
	 * @param m_transaction the transaction flag.
	 */
	public void setTransaction(boolean m_transaction) {
		this.m_transaction = m_transaction;
	}

	/**
	 * get the current message selector	
	 * @return String
	 */
	public String getMessageSelector() {
		return m_messageSelector;
	}


	/**
	 * set the message selector.
	 * @param selector String the message selector.
	 */
	public void setMessageSelector(String selector) {
		m_messageSelector = selector;
	}

	
	
	/**
	 * send a message using the current destination.
	 * @param msg the message to send.
	 * @throws JMSException in case of error.
	 */
	public void send(Message msg) throws JMSException
	{
		m_producer.send(msg);
	}

	/**
	 * send a message using the current destination.
	 * @param dest the Destination to use.
	 * @param msg the message to send.
	 * @throws JMSException in case of error.
	 */
	public void send(Destination dest,Message msg) throws JMSException
	{
		m_producer.send(dest,msg);
	}

	/**
	 * receive a message.
	 * @return Message the received message.
	 * @throws JMSException in case of error.
	 */
	public Message receive() throws JMSException
	{
		m_conn.start();
		return(m_consumer.receive());
	}

	/**
	 * receive a message without blocking.
	 * @return Message the received message.
	 * @throws JMSException in case of error.
	 */
	public Message receiveNoWait() throws JMSException
	{
		m_conn.start();
		return(m_consumer.receiveNoWait());	
	}
	/**
	 * receive a message.
	 * @return Message the received message.
	 * @throws JMSException in case of error.
	 */
	public Message receive(long timeout) throws JMSException
	{
		m_conn.start();
		return(m_consumer.receive(timeout));
	}
	
	/**
	 * set the messageListener.
	 * @param listener the messageListener to use.
	 * @throws JMSException in case of error.
	 */
	public void setMessageListener(MessageListener listener)throws JMSException
	{
		m_consumer.setMessageListener(listener);
		m_conn.start();
	}
	
	public void close()
	{
		try {
			m_session.close();
			m_conn.stop();
			m_conn.close();
			m_session = null;
			m_conn = null;
		} catch(JMSException jme ){
			
		}
		
	}

}
