package ejb.exercise;

import java.util.Date;

import javax.ejb.Stateless;

@Stateless
public class PaymentProcessorBean implements PaymentProcessor {

	@Override
	public void byCash(long trxId, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void byCard(String cardNo, Date expDate, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getPaymentMethods() {
		// TODO Auto-generated method stub
		String pmtMethods[] = new String[2];
		pmtMethods[0] = "byCash";
		pmtMethods[1] = "byCard";
		return(pmtMethods);
	}

}
