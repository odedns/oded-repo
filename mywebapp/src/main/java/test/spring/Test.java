/**
 * Date: 07/06/2007
 * File: Test.java
 * Package: test.spring
 */
package test.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author a73552
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Resource resource = new FileSystemResource("src/beans.xml");
		Resource resource = new ClassPathResource("beans.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		TestBean bean = (TestBean) factory.getBean("testBean");
		System.out.println("got TestBean: " + bean.toString());
		SingletonBean singleton = (SingletonBean) factory.getBean("singletonBean",SingletonBean.class);
		singleton.run();
		System.out.println(singleton.getBean().toString());
		DriverManagerDataSource ds = (DriverManagerDataSource) factory.getBean("dataSource");
		System.out.println("got datasource: " + ds.getUrl());
		AccountService account = (AccountService) factory.getBean("accountBean");
		account.foo();
		
	}

}
