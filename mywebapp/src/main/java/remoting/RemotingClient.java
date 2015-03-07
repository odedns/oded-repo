/**
 * RemotingClient.java
 * Mar 19, 2008
 */
package remoting;

import remoting.test.MyService;

/**
 * @author Odedn
 *
 */
public class RemotingClient {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Remoting Client");
		ServiceProxyFactory.setUrl("http://localhost:8080/myweb/remoting");
		MyService svc;
		try {
			svc = (MyService) ServiceProxyFactory.getService(MyService.class);
			String result = svc.foo("bar");
			System.out.println("result=" + result);
			
			int i = svc.add(10);
			System.out.println("i = " + i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
