/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author family
 *
 */
public class ArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> ar = new ArrayList<String>();
		for(int i=0; i < 10; ++i) {
			ar.add("string-" + i);
		}
		
		List<String> l = ar.subList(5, 9);
		System.out.println("ar = " + ar.toString());
	}

}
