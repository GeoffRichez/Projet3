package fr.groupe1.goevent;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.Role;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IEventRepository;
import fr.groupe1.goevent.repository.IEventTypeRepository;
import fr.groupe1.goevent.service.IUserService;

@SpringBootTest
public class UserDatabseTests {

	@Autowired
	IUserService userService;
	
	@Autowired
	IEventTypeRepository eventTypeRepository;

	@Test
	public void addUser() {
		User user = new User();
		user.setFirstName("Mario");
		user.setLastName("Brosse");
		user.setEmail("mario@gmail.com");
		
		Role role = new Role();
		role.setRole("Jobs");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
//		user.setRoles(roles);
		
		EventType eventType = new EventType("music", "jazz");
	//	eventTypeRepository.save(eventType);
		
		Event event = new Event();
		event.setTitle("Tr");
//		event.setDescritpion("SPACE 3022");
		event.setMaxAttendees(600);
		event.setEventType(eventType);
	//	event.setUser(user);
		List<Event> listeEvents = new ArrayList<Event>();
		listeEvents.add(event);
//		user.setListeEvents(listeEvents);
		

		userService.addUser(user);
	}
	
	
	@Test
	public void getAllUsers() {
		List<User> users= new ArrayList<User>();
		userService.getAllUsers();
	}

//	@Test
//	public void getUserById() {
//		User user = new User();
//		user.setFirstName("Mario");
//		userService.getUser(user);
//	}

}
