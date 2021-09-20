package fr.groupe1.goevent;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.service.IEventTypeService;


@SpringBootTest
public class EventTypeTest {
	@Autowired
	IEventTypeService eventTypeService;
	
	@Test
	void addEventTypeTest() {
		EventType eventType = new EventType("music","jazz");
		eventTypeService.addEventType(eventType);
		
	}
	
	@Test
	public void getAllEventTypes() {
		List<EventType> eventTypes= new ArrayList<EventType>();
		eventTypeService.getAllEventTypes();
	}
	
}


