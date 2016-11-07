/**
 * 
 */
package il.co.fmr.service;

/**
 * @author oded
 *
 */
public interface Notification {

	
	public boolean before();
	default public boolean after() {
		System.out.println("in after method...");
		return(true);
	}
}
