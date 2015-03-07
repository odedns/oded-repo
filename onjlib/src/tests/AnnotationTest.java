/**
 * Date: 16/05/2007
 * File: AnnotationTest.java
 * Package: tests
 */
package tests;

import java.lang.annotation.*;
import java.lang.reflect.Method;


/**
 * @author Oded Nissan
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestB {
	String value();
}




public class AnnotationTest {

	@TestB("other value")
	public void foo()
	{
		System.out.println("this is foo()");
	}
	

	@TestB("some value")
	public void bar()
	{
		System.out.println("this is bar()");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationTest tst = new AnnotationTest();
		Class c = tst.getClass();
		try {
			Method m = c.getMethod("foo", null);
			TestB an = m.getAnnotation(TestB.class);	
			System.out.println("an-" + an.value());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
