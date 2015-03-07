/**
 * 
 */
package test.springweb;

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
		
		log.debug("in root");
		return "login";
	}

	
	
	@RequestMapping(value= "/dologin" ,method=RequestMethod.POST)
	public String login(@RequestParam String userid, @RequestParam String password,Model model) {
		
		log.debug("userid=" + userid + "\tpass=" + password);
		return "HelloWorld";
	}

}
