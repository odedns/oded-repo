/**
 * File: AkkaRemote.java
 * Date: 2 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent.ClusterDomainEvent;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author ODEDNI
 *
 */
public class AkkaNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Akka Node...");
		String port = null;
		Config config = null;
		if (args.length > 0) {
			port = args[0];
			System.out.println("got port=" + args[0]);
			config  = ConfigFactory.parseString("akka.remote.netty.tcp.port="+port).withFallback(ConfigFactory.load().getConfig("TestCluster"));
		} else {
			config  = ConfigFactory.load().getConfig("TestCluster");
		}
		ActorSystem system = ActorSystem.create("ClusterSystem",config);
		ActorRef clusterListener = system.actorOf(Props.create(SimpleClusterListener.class), "clusterListener");
	    // Add subscription of cluster events
	    Cluster.get(system).subscribe(clusterListener,
	    		ClusterDomainEvent.class);

	}

}
