/*
 * Created on 15/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tests;

import onjlib.db.*;



import junit.framework.TestCase;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestIdGenerator extends TestCase {

	/**
	 * Test the TimeStampIdGenerator.
	 *
	 */
	public void testTsIdGeneraor() {
		IdGenerator gen = null;
		try {
			gen = (IdGenerator) IdGeneratorFactory.getIdGenerator("onjlib.db.TsIdGenerator");
		}catch(Exception e) {
			fail();
		}
		for(int i=0; i < 10; ++i) {			
			System.out.println("nextVal = " + gen.nextVal());
		}
		
	}

	

	/**
	 * Test the TimeStampIdGenerator.
	 *
	 */
	public void testTableIdGeneraor() {
		IdGenerator gen = null;
		try {
			gen = (IdGenerator) IdGeneratorFactory.getIdGenerator("onjlib.db.TableIdGenerator");
		}catch(Exception e) {
			fail();
		}
		for(int i=0; i < 10; ++i) {			
			System.out.println("nextVal = " + gen.nextVal());
		}
		
	}
	/**
	 * main test
	 * @param args
	 */
	public static void main(String[] args)
	{
		junit.textui.TestRunner.run(TestIdGenerator.class);
	}


}
