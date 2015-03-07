/**
 * File: Str.java
 * Date: Feb 6, 2014
 * Author: I070659
 */
package test;

import java.util.Scanner;

/**
 * @author I070659
 *
 */
public class Str {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter string:");
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		String []parts = s.split("\\.");
		System.out.println("parts len =" + parts.length);
		for(String s1 : parts) {
			System.out.println(s1 + "\t");
		}
		
	}

}
