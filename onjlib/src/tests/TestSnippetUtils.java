/**
 * Date: 01/02/2007
 * File: TestSnippetUtils.java
 * Package: tests
 */
package tests;

import static org.junit.Assert.*;

import onjlib.utils.SnippetUtils;

import org.junit.Test;

/**
 * @author a73552
 *
 */
public class TestSnippetUtils {

	/**
	 * Test method for {@link onjlib.utils.SnippetUtils#runSnippet(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRunSnippet() {
		String name = "Mysnippet";
		String code = "System.out.println(\"in Mysnippet\");";
		
		try {
			SnippetUtils.runSnippet(name, code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}

}
