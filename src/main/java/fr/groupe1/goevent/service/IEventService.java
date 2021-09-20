package fr.groupe1.goevent.service;

import java.util.List;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;

public interface IEventService {

	List<Event> getAllEvents();

	Event getEventById(Integer eventId);

	Event addEvent(Event event);

	void updateEvent(Event event);

	void deleteEvent(int eventId);

	Event findByTitle(String title);
	
	List<Event> getAllFutursEvents();

	List<Event> getAllFutursEvents(List<Event> events);

	List<Event> getAllPastEvents();

	List<Event> getAllPastEvents(List<Event> events);
	
//	String findById(Integer id);

	List<Adress> getAllCities(List<Event> events);

}
