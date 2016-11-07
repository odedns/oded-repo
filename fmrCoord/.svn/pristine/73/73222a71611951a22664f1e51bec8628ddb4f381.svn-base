package il.fmr.coord.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import il.co.fmr.coord.app.CoordApplication;
import il.co.fmr.coord.rest.FaasEvent;
import il.co.fmr.coord.service.EventService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CoordApplication.class)
public class EventServiceTest {
	@Autowired
	EventService service;

	@Test
	public void testCreateEvent() {

		FaasEvent event  = new FaasEvent();
		event.setEventType("LOGIN");
		event.setEventTime(new Date());
		event.setEventSource("APP");
		HashMap<String,String > map = new HashMap<String,String>();
		map.put("p1", "v1");
		map.put("p2", "v2");
		event.setParams(map);
		service.createEvent(event);
	}
	
	@Test
	public void testFindById() {
		
		FaasEvent event = service.findById(1);
		System.out.println(ToStringBuilder.reflectionToString(event));
		assertEquals(event.getId(),1);
	}

	@Test
	public void testFindByType() {
		
		List<FaasEvent> events = service.findbyType("LOGIN");
		events.forEach(event -> assertEquals(event.getEventType(), "LOGIN"));
	}
}
