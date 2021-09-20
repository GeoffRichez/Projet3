package fr.groupe1.goevent.repository;

import org.springframework.data.repository.CrudRepository;

import fr.groupe1.goevent.entities.EventType;

public interface IEventTypeRepository extends CrudRepository<EventType, Integer>{
	

}
