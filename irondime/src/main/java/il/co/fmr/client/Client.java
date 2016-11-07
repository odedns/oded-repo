/**
 * 
 */
package il.co.fmr.client;

import il.co.fmr.service.FmrService;
import il.co.fmr.service.ServiceNames;

/**
 * @author oded
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("client calling FMR services...");
		ClientLoginNotification loginNotify = new ClientLoginNotification();
		ClientScrapeNotification scrapNotify = new ClientScrapeNotification();
		FmrService service = new FmrService();
		
		service.addNotification(ServiceNames.LOGIN,loginNotify);
		service.addNotification(ServiceNames.SCRAPE,scrapNotify);
		
		service.invoke(null);
	}

}
