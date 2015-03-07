/**
 * File: MasterActor.java
 * Date: 15 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;


import scala.Option;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;

/**
 * @author ODEDNI
 *
 */
public class MasterActor extends UntypedActor{

	public static Props mkProps(String name)
	{
		return(Props.create(MasterActor.class, name));
	}
	
	public MasterActor(String name) 
	{
		System.out.println("MasterActor name=" + name);
	}
	@Override
	public void onReceive(Object obj) throws Exception {
		// TODO Auto-generated method stub
		int i = 10;
		if(obj instanceof JavaMessage) {
			JavaMessage msg = (JavaMessage)obj;
			if(msg.msg.equals("start")) {
				System.out.println("MasterActor: " + msg.msg);
				Props props = Props.create(JavaActor.class);
					ActorRef actor = getContext().actorOf(props);
					System.out.println("path = " + actor.path().toString());

					actor.tell(new JavaMessage(i++,"worker-" + i), getSelf());
					//actor.tell(new JavaMessage(i++,"worker-" + i), getSelf());
					//actor.tell(new JavaMessage(i,"worker-" + i), getSelf());
				
			} else {
				System.out.println("Master actor got message back from worker-" + msg.num);
			//	System.out.println("Master Actor sneding another message ");
			//	getSender().tell(new JavaMessage(msg.num, "work"), getSelf());
				//getContext().stop(getSender());
			}
			
		} else {
			unhandled(obj);
		}
	}
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		SupervisorStrategy strategy =
				new OneForOneStrategy(1, Duration.Inf(),
						new Function<Throwable, Directive>() {
				public Directive apply(Throwable t) {
					System.out.println("strategy sending stop...");					
					return SupervisorStrategy.restart();
				
				}
		});
		//return strategy;
		return super.supervisorStrategy();
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("master.preRestart");	
}

	@Override
	public void preStart() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("master.preStart");
	}

}
