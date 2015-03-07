/**
 * File: TestAkka.java
 * Date: 15 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author ODEDNI
 *
 */
public class TestAkka2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test Akka...");
		ActorSystem system = ActorSystem.create("JavaActorSystem");
		//final Props props = Props.create(MasterActor.class);
		final ActorRef actor = system.actorOf(MasterActor.mkProps("master"));
		System.out.println("path = " + actor.path().toString());
		
		actor.tell(new JavaMessage(0, "start"), actor);
		System.out.println("after sending message");
		
		//system.shutdown();
	}
	

}
