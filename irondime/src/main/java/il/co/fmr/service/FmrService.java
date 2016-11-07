/**
 * 
 */
package il.co.fmr.service;

import java.util.HashMap;

/**
 * @author oded
 *
 */
public class FmrService {

	private HashMap<String, Notification> notificationsMap = new HashMap<String, Notification>(); 
	
	public FmrService()
	{
		
	}
	
	
	/**
	 * this is the main service call	
	 * @param data
	 */
	public void invoke(Object data)
	{
		System.out.println("in FmrService.invoke()");
		callLoginService();		
		callCalcBalanceService();
		callScrapeService();
	}
	
	
	public void addNotification(String serviceName, Notification n)
	{
		notificationsMap.put(serviceName, n);
	}
	
	
	/**
	 * calling the appropriate rest services
	 */
	private void callLoginService()
	{
		boolean result = true;
		Notification n = notificationsMap.get(ServiceNames.LOGIN);
		if(null != n) {
			result = n.before();
		}
		if(result) {
			System.out.println("calling login REST service");
		}
		if(null != n) {
			n.after();
		}
	}
	
	private void callCalcBalanceService()
	{
		boolean result = true;
		Notification n = notificationsMap.get(ServiceNames.CALC_BALANCE);
		if(null != n) {
			result = n.before();
		}
		if(result) {
			System.out.println("calling callCalcBalanceService ");
		}
		if(null != n) {
			n.after();
		}
		
	}
	
	private void callScrapeService()
	{
		boolean result = true;
		Notification n = notificationsMap.get(ServiceNames.SCRAPE);
		if(null != n) {
			result = n.before();
		}
		if(result) {
			System.out.println("calling callScrapeService ");
		} 
		if(null != n) {
			n.after();
		}
	}
	
	
	
	
	
}
