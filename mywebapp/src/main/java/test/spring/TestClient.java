/**
 * TestClient.java
 * Mar 31, 2008
 */
package test.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Odedn
 *
 */
public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Resource resource = new ClassPathResource("beans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		AccountService account = (AccountService) factory.getBean("accountBean");
		String s = account.bar("mytest");
		System.out.println("s = " + s);
		int n = account.add(10);
		System.out.println("result= " + n);
	}

}
