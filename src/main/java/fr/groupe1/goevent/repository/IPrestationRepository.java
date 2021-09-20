package fr.groupe1.goevent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.groupe1.goevent.entities.Prestation;


@Repository
public interface IPrestationRepository extends CrudRepository<Prestation, Long> {

	Optional<Prestation> findById(Long id);
	
	Prestation getById(Long id);

}
