/**
 * File: Squarer.java
 * Date: 21 ���� 2014
 * Author: ODEDNI
 */
package org.oded.akka;


import scala.concurrent.Future;
import akka.japi.Option;

/**
 * @author ODEDNI
 *
 */

public interface Squarer {
	void squareDontCare(int i); //fire-forget
	Future<Integer> square(int i); //non-blocking send-request-reply
	Option<Integer> squareNowPlease(int i);//blocking send-request-reply
	int squareNow(int i); //blocking send-request-reply
}