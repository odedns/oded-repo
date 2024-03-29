package il.co.fmr.coord.rest;



import java.util.Date;
import java.util.List;

import il.co.fmr.coord.service.EventService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/rest")
public class EventController {
	
	private static final Logger log = Logger.getLogger(EventController.class);
	@Autowired
	EventService service;
	
	
	@RequestMapping(value= "/events", method = RequestMethod.POST, consumes="application/json")
	public void postEvent(@RequestBody FaasEvent event)
	{
		log.debug("in /rest/events: ");
		log.debug("got event = "+ ToStringBuilder.reflectionToString(event));
		event.setEventTime(new Date());
		service.createEvent(event);
		log.debug("stored event");
		
		
	}
	
		
	@RequestMapping(value= "/events/{id}", method = RequestMethod.GET, produces="application/json")
	public FaasEvent findById(@PathVariable Integer id)
	{
		log.debug("in findById( " + id + ")");
		FaasEvent event = service.findById(id);
		return(event);
		
		
	}
	
	@RequestMapping(value= "/events/type/{type}", method = RequestMethod.GET, produces="application/json")
	public List<FaasEvent> findByType(@PathVariable String type)
	{
		log.debug("in findByType( " + type + ")");
		List<FaasEvent> events = service.findbyType(type);
		return(events);
		
		
	}
}
