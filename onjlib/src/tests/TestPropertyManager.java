
package tests;
import onjlib.utils.PropertyManager;

import junit.framework.TestCase;


/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestPropertyManager extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestPropertyManager.class);
	}

	public void testGetIntProperty() {
	}

	public void testGetLongProperty() {
	}

	public void testGetShortProperty() {
	}

	public void testGetBooleanProperty() {
	}

	public void testGetFloatProperty() {
	}

	public void testGetDoubleProperty() {
	}
	
	public void testGetProperty() throws Exception 
	{
		PropertyManager pm = PropertyManager.getInstance();
		String s= pm.getProperty("default.connectionmanager");
		assertEquals(s,"onjlib.db.DriverConnectionManager");
	}
	

}
