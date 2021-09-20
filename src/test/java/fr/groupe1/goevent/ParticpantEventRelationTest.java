package fr.groupe1.goevent;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.IUserService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ParticpantEventRelationTest {

	@Autowired
	IEventService eventService;
	
	@Autowired
	IUserService userService;
	
	@Disabled
	void testRelationUserEvent() {		
		User user = new User();
		user.setLastName("Nicolas");
		user = userService.addUser(user);

		Event event = new Event();
		event.setTitle("cheval7");
//		List<User> users = new ArrayList<User>();
//		users.add(user);
//		event.setUsers(users);
	//	event.setUser(user);
		eventService.addEvent(event);
		
		List<Event> events = new ArrayList<Event>();
		events.add(event);
		
//		user.setParticipantEvents(events);
		
		userService.updateUser(user);
	}
	
	@Test
	void addTwoParticipantytoAnEvent() {		
		
		Event event1 = new Event();
		event1.setTitle("cheval35");
		event1 = eventService.addEvent(event1);
		
		Event event2 = new Event();
		event2.setTitle("cheval36");
		event2 = eventService.addEvent(event2);
		
		User participant1 = new User();
		participant1.setLastName("Mario");
		participant1 = userService.addUser(participant1);
		
		User participant2= new User();
		participant2.setLastName("Jean");
		participant2 = userService.addUser(participant2);
		
	
		event1.getParticipants().add(participant1);
		event1.getParticipants().add(participant2);
		event2.getParticipants().add(participant1);
			
		eventService.updateEvent(event1);
		eventService.updateEvent(event2);
	}
	
	
	
	@Test
	void getAllParticpantsOfAnEvent() {		
		
		Event event = eventService.getEventById(1);
		System.out.println(event);
		List<User> participants = event.getParticipants();
		System.out.println(participants.size());
		System.out.println(participants);
		for (User user : participants) {
			System.out.println(user);
		}
		
	}
	
	
	@Test
	void getAllEventsOfAParticipant() {	
		
		User particpant = userService.getUser(1);
		List<Event> events = particpant.getParticipantEvents();
		System.out.println(events.size());
		for (Event event : events) {
			System.out.println(event);
		}
	}
	
	@Disabled
	void deleteAnEventParticipantRelation() {	
		Event event = eventService.getEventById(1);
		event.getParticipants().remove(0);
		eventService.updateEvent(event);
		
	}

}