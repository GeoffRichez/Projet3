package fr.groupe1.goevent.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import fr.groupe1.goevent.entities.User;

public interface IUserService {
	
	User addUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(Integer id);
	
	User getUser(Integer id);
	
	List<User> getAllUsers();
	
	User getUserByEmail(String email);
	
	void saveAllUsers(List<User> users);

	void updateResetPasswordToken(String token, String email);

	User getByResetPasswordToken(String token);
	
	void updatePassword(User user, String newPassword);
}

