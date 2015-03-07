/**
 * TestClient.java
 * Mar 31, 2008
 */
package test.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import test.spring.data.DepartmentRepository;
import test.spring.data.RepoTestBean;

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
		  ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		AccountService account = (AccountService) ctx.getBean(AccountService.class);
		String s = account.bar("mytest");
		System.out.println("s = " + s);
		int n = account.add(10);
		System.out.println("result= " + n);
		
		
		RepoTestBean repoTest = ctx.getBean(RepoTestBean.class);
		DepartmentRepository repo = repoTest.getRepo();
		System.out.println("got repo = " + repo.toString());
		
	}

}
