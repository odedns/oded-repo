package org.oded.akka;


import scala.Option;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;


public class JavaActor extends UntypedActor{
	// The main 'receive' method for the Actor
	public void onReceive(Object message) throws Exception{
	// We don't have pattern matching so we just use 'if'
	// blocks, and a whole lot of 'instanceof' invocations
		//Props props = Props.create(MasterActor.class);
		ActorRef master = getContext().actorOf(MasterActor.mkProps("master"));

	if (message instanceof JavaMessage) {
		JavaMessage m = (JavaMessage)message;
		
			System.out.println("JavaActor got msg: " + m.msg + " num=" + m.num);
		getSender().tell(new JavaMessage(m.num,"reply "), getSelf() );
		System.out.println("sending reply from worker-" + m.num);
		
		throw new Exception("exception JavaActor");
		
	} else{
	// We've got to call this by hand otherwise any
	// unhandled message will be silently discarded
		System.out.println("got unhandled message");
		unhandled(message);
	}
}

	@Override
	public void postStop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JavaActor.PostStop()");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JavaActor.preRestatr() messag= " + message.toString());
		getSelf().forward(message.get(), getContext());
	}

	@Override
	public void preStart() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JavaActor.PreStart()");
	}
	
	
	
	
	
}
