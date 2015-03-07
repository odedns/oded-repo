/**
 * File: TestAkkaRouter.java
 * Date: 6 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinRouter;

/**
 * @author ODEDNI
 *
 */
public class TestAkkaRouter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("TestAkkaRouter...");
		ActorSystem system = ActorSystem.create("JavaActorSystem");
		//final Props props = Props.create(MasterActor.class);
		final ActorRef router = system.actorOf(Props.create(ExampleActor.class).withRouter(new RoundRobinRouter(4)));
		System.out.println("path = " + router.path().toString());
		for(int i=0; i < 20; ++i) {
			router.tell("msg-" + i, system.guardian());
		}
		System.out.println("done sending messages..");
		system.shutdown();
	}

}
