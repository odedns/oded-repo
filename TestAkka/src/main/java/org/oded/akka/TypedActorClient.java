/**
 * File: TypedActorClient.java
 * Date: 21 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;


import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.japi.Creator;
import akka.japi.Option;

/**
 * @author ODEDNI
 *
 */
public class TypedActorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActorSystem system = ActorSystem.create("JavaActorSystem");
		Squarer mySquarer =
				TypedActor.get(system).typedActorOf(
				new TypedProps<SquarerImpl>(Squarer.class,
				new Creator<SquarerImpl>() {
				public SquarerImpl create() { return new SquarerImpl("foo"); }
				}),
				"name");
		System.out.println("created typeActor Squarer..");
		
		mySquarer.squareDontCare(10);
		Option<Integer> oSquare = mySquarer.squareNowPlease(10); //Option[Int]
		System.out.println("result = " + oSquare.get());
		int iSquare = mySquarer.squareNow(10); //Int
		System.out.println("result square now = " + iSquare);
		
		Future<Integer> fSquare = mySquarer.square(10); //A Future[Int]
		try {
			Integer n = Await.result(fSquare, Duration.create("2 seconds"));
			System.out.println("got from await= " + n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
