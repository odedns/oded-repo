/**
 * 
 */
package ejb.exercise;

import java.util.Date;

import javax.ejb.Remote;

/**
 * @author ...
 *
 */
@Remote
public interface PaymentProcessor {
	
	public void byCash(long trxId, double amount);
	public void byCard(String cardNo, Date expDate, double amount);
	String [] getPaymentMethods();

}
