/* Created on 17/10/2006 */
package tests;

import java.io.IOException;
import java.util.Properties;

import onjlib.utils.PropertyReader;
import junit.framework.TestCase;

/**
 * 
 * @author Odedn
 */
public class TestPropertyReader extends TestCase {

	public static void main(String[] args)
	{
		junit.textui.TestRunner.run(TestPropertyReader.class);
	}

	public void testRead() throws IOException
	{
		Properties p = PropertyReader.read("/tests/myprop.properties");
		assertNotNull(p);
		System.out.println(p.toString());
	}

}
