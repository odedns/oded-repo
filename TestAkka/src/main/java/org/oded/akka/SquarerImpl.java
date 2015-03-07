/**
 * File: SquarerImpl.java
 * Date: 21 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;



import scala.concurrent.Future;
import akka.dispatch.Futures;
import akka.japi.Option;

/**
 * @author ODEDNI
 *
 */

public class SquarerImpl implements Squarer {
	private String name;
	public SquarerImpl() {
		
		this.name = "default";
	}
	public SquarerImpl(String name) {
		this.name = name;
	}
	public void squareDontCare(int i) {
		int sq = i * i; //Nobody cares :(
		System.out.println("in squareDontCare");
	}
	public Future<Integer> square(int i) {
		System.out.println("in square");
		Integer n = i *i;
		return Futures.successful(n);
		
	}
	public Option<Integer> squareNowPlease(int i) {
		System.out.println("in squareNowPlease");

		return Option.some(i * i);
	}
	public int squareNow(int i) {
		System.out.println("in squareNow");
		return i * i;
	}
}