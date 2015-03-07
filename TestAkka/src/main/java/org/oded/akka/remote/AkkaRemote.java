/**
 * File: AkkaRemote.java
 * Date: 2 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka.remote;

import akka.actor.ActorSystem;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author Oded Nissan
 *
 */
public class AkkaRemote {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  port;
		Config config = null;
		if (args.length > 0) {
			port = args[0];
			System.out.println("got port=" + args[0]);
			config  = ConfigFactory.parseString("akka.remote.netty.tcp.port="+port).withFallback(ConfigFactory.load().getConfig("TestConfig"));
		} else {
			config  = ConfigFactory.load().getConfig("TestConfig");
		}
		System.out.println("Akka Remote...");
		ActorSystem system = ActorSystem.create("RemoteActorSystem",config);

	}

}
