/**
 * 
 */
package il.co.fmr.client;

import il.co.fmr.service.Notification;

/**
 * @author oded
 *
 */
public class ClientScrapeNotification implements Notification {

	/* (non-Javadoc)
	 * @see il.co.fmr.service.Notification#before()
	 */
	@Override
	public boolean before() {
		// TODO Auto-generated method stub
		System.out.println("before scrape service");
		return true;
	}

	

}
