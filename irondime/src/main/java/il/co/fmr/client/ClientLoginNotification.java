/**
 * 
 */
package il.co.fmr.client;

import il.co.fmr.service.Notification;

/**
 * @author oded
 *
 */
public class ClientLoginNotification implements Notification {

	/* (non-Javadoc)
	 * @see il.co.fmr.service.Notification#before()
	 */
	@Override
	public boolean before() {
		// TODO Auto-generated method stub
		System.out.println("before login service");
		return true;
	}

	/* (non-Javadoc)
	 * @see il.co.fmr.service.Notification#after()
	 */
	@Override
	public boolean after() {
		// TODO Auto-generated method stub
		System.out.println("after login service");
		return true;
	}

}
