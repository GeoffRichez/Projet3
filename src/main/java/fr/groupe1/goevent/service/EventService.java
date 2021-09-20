package fr.groupe1.goevent.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.repository.IEventRepository;

@Service
public class EventService implements IEventService {

	@Autowired
	private IEventRepository evenRepository;

	@Override
	public List<Event> getAllEvents() {
		List<Event> list = new ArrayList<>();
		evenRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Event getEventById(Integer eventId) {
		Event evt = evenRepository.findById(eventId).get();
		return evt;
	}

	@Override
	public Event addEvent(Event event) {
		List<Event> list = evenRepository.findByTitle(event.getTitle());
		if (list.size() > 0) {
			return null;
		} else {
			return evenRepository.save(event);
		}
	}

	@Override
	public void updateEvent(Event event) {
		evenRepository.save(event);

	}

	@Override
	public void deleteEvent(int eventId) {
		evenRepository.delete(getEventById(eventId));
	}

	@Override
	public Event findByTitle(String title) {
		return (Event) evenRepository.findByTitle(title);
	}

	@Override
	public List<Event> getAllFutursEvents(){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDateNow = LocalDate.now();
	    Date dateNow = Date.from(localDateNow.atStartOfDay(defaultZoneId).toInstant());
		return  getAllEvents()
				.stream()
				.filter(e -> e.getStartDate().compareTo(dateNow) > 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> getAllFutursEvents(List<Event> events){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDateNow = LocalDate.now();
	    Date dateNow = Date.from(localDateNow.atStartOfDay(defaultZoneId).toInstant());
		return  events
				.stream()
				.filter(e -> e.getStartDate().compareTo(dateNow) > 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> getAllPastEvents(){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDateNow = LocalDate.now();
	    Date dateNow = Date.from(localDateNow.atStartOfDay(defaultZoneId).toInstant());
		return  getAllEvents()
				.stream()
				.filter(e -> e.getStartDate().compareTo(dateNow) < 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Event> getAllPastEvents(List<Event> events){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDateNow = LocalDate.now();
	    Date dateNow = Date.from(localDateNow.atStartOfDay(defaultZoneId).toInstant());
		return  events
				.stream()
				.filter(e -> e.getStartDate().compareTo(dateNow) < 0)
				.collect(Collectors.toList());
	}


//	@Override
//	public String findById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;

	@Override
	public List<Adress> getAllCities(List<Event> events) {		
		List<Adress> listCities = new ArrayList<Adress>();
		for (Event event : events) {
			listCities.add(event.getLocation());			
		}		
		return listCities;		
	}
}
