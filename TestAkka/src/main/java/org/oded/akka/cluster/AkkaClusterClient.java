package org.oded.akka.cluster;

import org.oded.akka.TestActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;

import com.typesafe.config.ConfigFactory;

public class AkkaClusterClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("AkkaLocalClient...");
		ActorSystem system = ActorSystem.create("ClusterSystem",ConfigFactory.load().getConfig("TestCluster"));
		//ActorSystem system = ActorSystem.create("LocalActorSystem",ConfigFactory.load().getConfig("TestCluster"));
//		ActorRef actor = system.actorOf(Props.create(TestActor.class), "testActor");
		ActorRef actor = system.actorOf(Props.create(TestActor.class).withRouter(new FromConfig()), "testActorRouter");
		
		for(int i =0; i < 3; ++i) {
			System.out.println("sending message to remote actor");
			actor.tell("message from local", ActorRef.noSender());
		}
		//system.shutdown();
		
		
		

	}

}
