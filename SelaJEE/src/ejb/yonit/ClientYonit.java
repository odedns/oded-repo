package ejb.yonit;

import javax.naming.InitialContext;

/**
 * Created with IntelliJ IDEA.
 * User: dviry
 * Date: 8/11/13
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientYonit {

    public static void main(String[] args) throws Exception
    {
      InitialContext ctx = new InitialContext();
      PaymentProcessorYonit p = (PaymentProcessorYonit) ctx.lookup("SelaJEE/PaymentProcessorBean!ejb.exercise.PaymentProcessor");

      System.out.println("yonit");
    }

}
