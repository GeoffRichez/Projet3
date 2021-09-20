package fr.groupe1.goevent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.repository.IEventTypeRepository;

@Service
public class EventTypeService implements IEventTypeService{

	@Autowired
	private IEventTypeRepository eventTypeRepository;

	@Override
	public EventType addEventType(EventType eventType) {
		return eventTypeRepository.save(eventType);
	}

	@Override
	public EventType updateEventType(EventType eventType) {
		return eventTypeRepository.save(eventType);
	}

	@Override
	public void deleteEventType(Integer id) {
		eventTypeRepository.deleteById(id);
	}

	@Override
	public EventType getEventType(Integer id) {
		return eventTypeRepository.findById(id).get();
	}

	@Override
	public List<EventType> getAllEventTypes() {
		List<EventType> list = new ArrayList<>();
		eventTypeRepository.findAll().forEach(et -> list.add(et));
		return list;
	}
	
	
	}


