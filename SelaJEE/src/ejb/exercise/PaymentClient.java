package ejb.exercise;

import javax.naming.InitialContext;

import org.jboss.tutorial.stateless.bean.Calculator;

public class PaymentClient {

	
	 public static void main(String[] args) throws Exception
	   {
	      InitialContext ctx = new InitialContext();
	      PaymentProcessor payment = (PaymentProcessor) ctx.lookup("SelaJEE/PaymentProcessorBean!ejb.exercise.PaymentProcessor");
	      String paymentMethods[] = payment.getPaymentMethods();	
	      
	     
	   }
}
