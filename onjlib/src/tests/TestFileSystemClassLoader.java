/**
 * Date: 04/02/2007
 * File: TestFileSystemClassLoader.java
 * Package: tests
 */
package tests;

import static org.junit.Assert.*;

import onjlib.utils.FileSystemClassLoader;

import org.junit.Test;

/**
 * @author a73552
 *
 */
public class TestFileSystemClassLoader {

	/**
	 * Test method for {@link onjlib.utils.FileSystemClassLoader#loadClass(java.lang.String)}.
	 */
	@Test
	public void testLoadClassString() throws Exception {
		
		FileSystemClassLoader fcl = new FileSystemClassLoader("c:/temp");
		Class c= fcl.findClass("HelloWorld");
		assertNotNull(c);
	}
	public static void main(String args[]) {
	      org.junit.runner.JUnitCore.main("tests.TestFileSystemClassLoader");
	    }

}
