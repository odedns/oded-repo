package il.co.fmr.rest;

import il.co.fmr.data.IronDimeUser;
import il.co.fmr.data.UserService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	
	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService service;
	
	@RequestMapping(value = "/user/register", method= RequestMethod.POST, consumes="application/json" )
	public void register(@RequestBody IronDimeUser u)
	{
		
		log.info("rest call register got user :" + ToStringBuilder.reflectionToString(u));
	}
	
	
	@RequestMapping(value= "/user/{id}", method = RequestMethod.GET, produces= "application/json")
	public IronDimeUser getUser(@PathVariable String id)
	{
		log.info("in getUser: " +id);
		IronDimeUser u = service.getUser(id);
		return(u);
	}

	@RequestMapping(value= "/user/test", method = RequestMethod.GET, produces= "application/json")
	public void test()
	{
		log.info("in test: ");
	}
	
	@RequestMapping(value = "/user/login",  method= RequestMethod.POST, consumes="application/json")
	public void login(@RequestBody String user, @RequestBody String password, @RequestBody String code)
	{
		log.info("user= " + user + " password=" + password + " code=" + code);
	}
}
