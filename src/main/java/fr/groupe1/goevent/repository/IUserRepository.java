package fr.groupe1.goevent.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fr.groupe1.goevent.entities.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

	List<User> findByEmail(String Email);
	
	User findByResetPasswordToken(String token);

	User getById(Integer id);
	

	
}
