package org.oded.akka.local;

import org.oded.akka.TestActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;

import com.typesafe.config.ConfigFactory;

public class AkkaLocalClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("AkkaLocalClient...");
		ActorSystem system = ActorSystem.create("LocalActorSystem",ConfigFactory.load().getConfig("TestLocalConfig"));
		ActorRef actor = system.actorOf(Props.create(TestActor.class).withRouter(new FromConfig()), "testActor");
		/*
		Address[] addresses = {new Address("akka.tcp", "RemoteActorSystem", "localhost", 2555),
				new Address("akka.tcp", "RemoteActorSystem", "localhost", 2552)};
		
		RemoteRouterConfig remoteRouter = new RemoteRouterConfig(new RoundRobinPool(2), addresses);
		
		ActorRef actor = system.actorOf(Props.create(MasterTestActor.class).withRouter(remoteRouter),"testActor");
		*/
	//	ActorRef actor = system.actorOf(remoteRouter.props(Props.create(TestActor.class)));
		System.out.println("sending message to remote actor");
		actor.tell("message from local", actor);
		actor.tell("message from local", actor);
		actor.tell("message from local", actor);
		actor.tell("message from local", actor);

		//router.tell("message from local", ActorRef.noSender());
		//router.tell("message from local", ActorRef.noSender());

		
		//system.shutdown();
		
		
		

	}

}
