/**
 * File: ExampleActor.java
 * Date: 6 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;
import akka.actor.UntypedActor;

/**
 * @author ODEDNI
 *
 */
public class ExampleActor extends UntypedActor {

	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		// TODO Auto-generated method stub

		if(obj instanceof String) {
			String msg =(String)obj;
			System.out.println("ExampleActor got message: " + msg);
		} else {
			unhandled(obj);
		}
	}

}
