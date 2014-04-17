package ejb.yonit;

import javax.ejb.Stateless;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dviry
 * Date: 8/11/13
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class PaymentProcessorBeanYonit implements PaymentProcessorYonit {
    @Override
    public void byCash(long trxId, double amount) {

        System.out.println("trxId = " + trxId +", amount = " + amount);
    }

    @Override
    public void byCard(String cardNo, Date expDate, double amount) {
        System.out.println("cardNo = " + cardNo +" expDate = " + expDate + " amount = " + amount);
    }

    @Override
    public String[] getPaymentMethods() {

        String[] a = new String[2];
        a[0] = "yonit";
        return a;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
