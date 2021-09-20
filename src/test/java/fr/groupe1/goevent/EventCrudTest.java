package fr.groupe1.goevent;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.service.IAdressService;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.IEventTypeService;

@SpringBootTest
class EventCrudTest {

	@Autowired
	IEventService eventService;
	
	@Autowired
	IAdressService adressService;
	
	@Autowired
	IEventTypeService eventTypeService;

	@Test
	void addEventTest() {
//		Event event = new Event();
//		eventService.addEvent(event);
//		
//		EventType eventType = new EventType("music","jazz");
//		eventTypeService.addEventType(eventType);
//		Adress adress = new Adress("ville", "rue", 12365);
//		adressService.addAdress(adress);
//		Event event1 = new Event(10,"Salon du chocolat", "miam miam", null, null, 1000,adress, null);
//		event1.setEventType(eventType);
//		eventService.addEvent(event1);
		
		
		
		
		
		
	}

	//void suppEvent() {
	//	eventService.deleteEvent(2);
	//}

}
