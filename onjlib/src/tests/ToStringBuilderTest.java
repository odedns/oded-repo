package tests;
import onjlib.utils.ToStringBuilder;

import junit.framework.TestCase;

/*
 * Created on 28/09/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author P0006439
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ToStringBuilderTest extends TestCase
{
	Object testObj;

	/**
	 * Constructor for ToStringBuilderTest.
	 * @param arg0
	 */
	public ToStringBuilderTest(String arg0)
	{
		super(arg0);
	}

	public static void main(String[] args)
	{
		junit.textui.TestRunner.run(ToStringBuilderTest.class);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{		
		super.setUp();
		
		class BaseBase {
			private String bar;
			
			void setBar(String s)
			{
				bar = s;
			}
			
			
		}
		class Base extends BaseBase {
			private String foo;
			
			void setFoo(String s)
			{
				foo = s;
			}
		}
		class Myclass extends Base {
			private int id;
			float salary;
			String name;
			static final int CONST = 1;	
	
		}
		Myclass m = new Myclass();
		m.setBar("bar");
		m.setFoo("foo");
		m.id = 100;
		m.name ="Bob";
		m.salary = 50000;
		testObj = m;
	}

	/*
	 * Test for String toString(Object)
	 */
	public void testToStringObject() throws Exception
	{		
		String s = ToStringBuilder.toString(testObj);
		System.out.println(s);
		assertNotNull(s);
	}

}
