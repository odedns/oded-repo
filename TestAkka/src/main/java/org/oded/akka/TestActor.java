/**
 * File: TestActor.java
 * Date: 2 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;

import akka.actor.UntypedActor;

/**
 * @author ODEDNI
 *
 */
public class TestActor extends UntypedActor {

	public TestActor()
	{
		System.out.println("TestActor created..");
	}
	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		// TODO Auto-generated method stub

		if(obj instanceof String) {
			System.out.println("TestActor got message: " + obj);
		} else {
			System.out.println("unhandled message: " + obj.toString());
			unhandled(obj);
		}
	}

}
