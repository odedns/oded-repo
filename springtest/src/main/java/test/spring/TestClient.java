/**
 * TestClient.java
 * Mar 31, 2008
 */
package test.spring;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import test.spring.data.Departments;
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
		
		SingletonBean sBean = ctx.getBean(SingletonBean.class);
		TestBean tb = sBean.getBean();
		System.out.println("TestBean name =  " + tb.getName());
		
		DataSource ds = sBean.getDatasource();
		System.out.println("ds. conntection= " + ds.toString());
		
		TestDao dao = sBean.getDao();
		dao.printAll();
		
		
		RepoTestBean jpaRepo = ctx.getBean(RepoTestBean.class);
		List<Departments> depts = jpaRepo.getRepo().findAll();
		for(Departments dept : depts) {
			System.out.println(dept.toString());
		}
		
	
	}

}
