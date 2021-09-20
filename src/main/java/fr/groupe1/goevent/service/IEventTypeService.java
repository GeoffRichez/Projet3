package fr.groupe1.goevent.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.groupe1.goevent.entities.EventType;


@Repository
public interface IEventTypeService {
	
	EventType addEventType(EventType eventType);
	
	EventType updateEventType(EventType eventType);
	
	void deleteEventType(Integer id);
	
	EventType getEventType(Integer id);
	
	List<EventType> getAllEventTypes();
	
	
	
	
	
	

}
