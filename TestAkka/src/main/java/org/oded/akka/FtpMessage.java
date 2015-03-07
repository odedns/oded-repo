/**
 * File: FtpMessage.java
 * Date: 23 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;

import java.io.Serializable;
import java.util.Properties;

import akka.actor.Props;

/**
 * @author Oded Nissan
 *
 */
public class FtpMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Properties props;
	
	public static Props mkProps(Properties props) {
		return Props.create(FtpMessage.class, props);
		}
	
	public FtpMessage(Properties props)
	{
		this.setProps(props);
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
