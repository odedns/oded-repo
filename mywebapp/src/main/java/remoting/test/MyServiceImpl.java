/**
 * MyServiceImpl.java
 * Mar 19, 2008
 */
package remoting.test;

/**
 * @author Odedn
 *
 */
public class MyServiceImpl implements MyService {

	/* (non-Javadoc)
	 * @see remoting.test.MyServiceIF#foo(java.lang.String)
	 */
	public String foo(String s)
	{
		// TODO Auto-generated method stub
		System.out.println("foo: " + s);
		return("foo:  " + s);
	}

	public int add(int i)
	{
		// TODO Auto-generated method stub
		return(++i);
	}
	
	

}
