/**
 * 
 */
package test.springweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author C5132784
 *
 */
@Controller
// @RequestMapping(value= "/SpringWeb")
public class TestController {
	private static final Logger log = Logger.getLogger(TestController.class);
	 
	@RequestMapping(value= "/hello" ,method=RequestMethod.GET)
	public String hello(Model model) {
		
		log.debug("in hello");
		return "HelloWorld";
	}

	
	
	@RequestMapping(value= "/login" ,method=RequestMethod.GET)
	public String root(Model model) {
		
		log.debug("in login");
		return "login";
	}

	
	
	@RequestMapping(value= "/dologin" ,method=RequestMethod.POST)
	public String login(@RequestParam String userid, @RequestParam String password,HttpServletRequest req) {
		
		log.debug("userid=" + userid + "\tpass=" + password);
		HttpSession session = req.getSession(true);
		session.setAttribute("user", userid);
		return "menu";
	}
	
	@RequestMapping(value= "/editEmp" ,method=RequestMethod.GET)
	public String edtiEmp(Model model) {
		
		log.debug("in editEmp");
		Emp emp = new Emp();
		model.addAttribute("emp", emp);
		return "editEmp";
	}

	
	@RequestMapping(value= "/addEmp" ,method=RequestMethod.POST)
	public String addEmp(Emp emp,Model model) {
		
		log.debug("in addEmp");
		log.debug("emp=" + ToStringBuilder.reflectionToString(emp));
		
		return "listEmp";
	}
	
	
	@RequestMapping(value= {"/menu","listEmp"})
	public void defaultAction()
	{
		log.debug("in defaultAction...");
	}
}
