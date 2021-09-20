package fr.groupe1.goevent.controllers;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	
	@RequestMapping("/list")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping("/{userId}")
	public User getUser(@PathVariable Integer userId) {
		return userService.getUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/{userId}")
	public void addUser(@PathVariable User user) {
		userService.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/{userId}")
	public void updateUser(@PathVariable User user) {
		userService.updateUser(user);
	}
	

}
