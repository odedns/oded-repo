/**
 *
 */
package ejb.yonit;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;

/**
 * @author ...
 *
 */


@Remote
public interface PaymentProcessorYonit {

	public void byCash(long trxId, double amount);
	public void byCard(String cardNo, Date expDate, double amount);
	String [] getPaymentMethods();

}
