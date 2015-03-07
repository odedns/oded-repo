/**
 * AccountServiceImpl.java
 * Mar 31, 2008
 */
package test.spring;

/**
 * @author Odedn
 *
 */
public class AccountServiceImpl implements AccountService {

	/* (non-Javadoc)
	 * @see test.spring.AccountService#foo()
	 */
	public void foo()
	{
		// TODO Auto-generated method stub
		System.out.println("in foo()");
	}

	public String bar(String s)
	{
		return(s + "-result from server");
	}
	
	
	public int add(int n)
	{
		return(++n);
	}
}
