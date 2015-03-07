/**
 * File: MasterTestActor.java
 * Date: 16 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author ODEDNI
 *
 */
public class MasterTestActor extends UntypedActor {

	/* (non-Javadoc)
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object obj) throws Exception {
		// TODO Auto-generated method stub
		if(obj instanceof String) {
			System.out.println("MasterActor: " + obj.toString());
			Props props = Props.create(TestActor.class);
			for(int i=0; i < 10; ++i) {
				ActorRef actor = getContext().actorOf(props);
				System.out.println("master creating testActor");
				actor.tell(obj, getSelf());
			}
		} else {
			unhandled(obj);
		}
	}

}
