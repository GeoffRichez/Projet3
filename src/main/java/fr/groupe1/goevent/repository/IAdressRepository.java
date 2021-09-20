package fr.groupe1.goevent.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.groupe1.goevent.entities.Adress;

@Repository
public interface IAdressRepository extends CrudRepository<Adress, Integer>{
	

	
}
