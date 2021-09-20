package fr.groupe1.goevent.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IRoleRepository;
import fr.groupe1.goevent.repository.IUserRepository;


@Service("userService")
public class UserService implements IUserService {

	private IUserRepository userRepository;
	private IRoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	public UserService(IUserRepository userRepository, IRoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
//	@Autowired
//	public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
//		super();
//		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
//	}

	public User addUser(User user) {
		List<User> listUser = userRepository.findByEmail(user.getEmail());
		if (!listUser.isEmpty()) {
			return null;
		} else {
			 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setActive(1);
			return userRepository.save(user);
		}
	}
	
	
	
	public void saveAllUsers(List<User> users) {
		for (User user : users) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setActive(1);
		}
		userRepository.saveAll(users); 
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	
	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get(0);
	}


	@Override
	public void updateResetPasswordToken(String token, String email) {
		List<User> listUser = userRepository.findByEmail(email);
		if (!listUser.isEmpty()) {
			User user=listUser.get(0);
			user.setResetPasswordToken(token);
			userRepository.save(user);
		}
	}


	@Override
	public User getByResetPasswordToken(String token) {
		
		return userRepository.findByResetPasswordToken(token);
	}


	@Override
	public void updatePassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		userRepository.save(user);		
	}
	

}