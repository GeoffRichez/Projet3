package fr.groupe1.goevent.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.groupe1.goevent.entities.Role;
import fr.groupe1.goevent.repository.IRoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleRepository roleRepo;
	
	public RoleController(IRoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}
	
	@RequestMapping("/list")
	@ResponseBody    // Nicolas l'a trouvé
	public List<Role> listRole(){
	System.out.println("In listRole");   // s'afficher dans le console; 
		System.out.println((List<Role>)roleRepo.findAll());
		return (List<Role>)roleRepo.findAll();  // envoyer sur la page web; 
	}
}