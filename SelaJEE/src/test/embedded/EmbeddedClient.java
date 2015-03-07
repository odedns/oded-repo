/**
 * 
 */
package test.embedded;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.tutorial.stateless.bean.Calculator;

/**
 * @author Oded Nissan
 *
 */
public class EmbeddedClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        System.setProperty("org.jboss.as.embedded.ejb3.BARREN", "true");
        System.setProperty("jboss.home", "C:/dev/servers/jboss-as-7.1.1.Final");
        System.setProperty("jboss.home.dir", "C:/dev/servers/jboss-as-7.1.1.Final");
        Map<String, Object> props = new HashMap<String, Object>();
       /* 
        File[] ejbModules = new File[1];
        ejbModules[0] = new File("c:/tmp/SelaEE.war");
 
        props.put(EJBContainer.MODULES, ejbModules);
        */
		EJBContainer container = EJBContainer.createEJBContainer();
		Context context = container.getContext();
		Calculator calculator = null;
		try {
			calculator = (Calculator) context.lookup("SelaJEE/CalculatorBean!org.jboss.tutorial.stateless.bean.CalculatorRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		      System.out.println("1 + 1 = " + calculator.add(1, 1));
		      System.out.println("1 - 1 = " + calculator.subtract(1, 1));
	}

}
