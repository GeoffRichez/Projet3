package fr.groupe1.goevent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.groupe1.goevent.entities.Role;

@Repository("roleRepository")
public interface IRoleRepository extends CrudRepository<Role, Long>{
	//Role findByNumeroRole(Long numRole); 

}
