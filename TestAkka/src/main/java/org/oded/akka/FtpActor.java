/**
 * File: FtpActor.java
 * Date: 22 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;


import java.util.Properties;

import akka.actor.UntypedActor;

/**
 * @author Oded Nissan
 *
 */
public class FtpActor extends UntypedActor {

	
	
	public FtpActor()
	{
		
	}
	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		if(obj instanceof FtpMessage) {
			
			FtpMessage msg = (FtpMessage) obj;
			Properties props = msg.getProps();
			handleFtp(props);
			
		} else {
			unhandled(obj);
		}

	}
	
	private void handleFtp(Properties props) throws Exception
	{
		
	}

}
