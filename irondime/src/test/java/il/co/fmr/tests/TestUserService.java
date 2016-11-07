/**
 * 
 */
package il.co.fmr.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;
import il.co.fmr.app.Application;
import il.co.fmr.data.IronDimeUser;
import il.co.fmr.data.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author oded
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TestUserService {
	@Autowired
	UserService service;
	
	
	@Test
	public void testFindUser()
	{
		String userId = "johnd";
		
		IronDimeUser u = service.getUser(userId);
		System.out.println("u = " + u.getName());
		assertEquals(u.getName(), "John Doe");				
	}
	


	@Test
	public void testCreateUser()
	{
		IronDimeUser u = new IronDimeUser();
		u.setUserId("user"+ System.currentTimeMillis() /3600 );
		u.setEmail("user"+ System.currentTimeMillis() /3600 + "@fmr.co.il");
		u.setPassword("****");
		u.setName("User " + + System.currentTimeMillis() /3600);
		u.setPhone("054-9250209");
		service.createUser(u);
		IronDimeUser u2 = service.getUser(u.getUserId());
		System.out.println("u2 = " + u.getName());
		assertEquals(u.getName(), u2.getName());
		
	}
	
	
	@Test
	public void testLogin()
	{
		String user = "johnd";
		String pass = "***";		
		boolean res = service.login(user, pass);
		assertTrue(res);
	}
}
