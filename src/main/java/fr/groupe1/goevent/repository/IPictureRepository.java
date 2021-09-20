package fr.groupe1.goevent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.groupe1.goevent.entities.Picture;

@Repository
public interface IPictureRepository extends CrudRepository<Picture, Integer>{

}
