package fr.groupe1.goevent.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.groupe1.goevent.entities.Event;

@Repository
public interface IEventRepository extends CrudRepository<Event, Integer> {

	List<Event> findByTitle(String title);

}
