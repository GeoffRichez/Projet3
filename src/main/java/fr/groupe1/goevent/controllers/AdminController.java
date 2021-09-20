package fr.groupe1.goevent.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.groupe1.goevent.GoeventApplication;
import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IRoleRepository;
import fr.groupe1.goevent.repository.IUserRepository;
import fr.groupe1.goevent.service.AdressService;
import fr.groupe1.goevent.service.IAdressService;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.IEventTypeService;
import fr.groupe1.goevent.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IEventService eventService;

	@Autowired
	private IEventTypeService eventTypeService;
	
	@Autowired
	private IAdressService adressService;
	
	@RequestMapping(value = "/accueil", method = RequestMethod.GET)
	public String homeAdmin(Model model) {
		System.out.println("In admin acceuil start");
		List<User> listUsers = userService.getAllUsers();
		
		Integer listParticipant = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.PARTICIPANT))
				.collect(Collectors.toList()).size();

		model.addAttribute("listParticipant", listParticipant);

		int listPrestataire = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.PROVIDER))
				.collect(Collectors.toList()).size();
		model.addAttribute("listPrestataire", listPrestataire);
		
		int listOrganisateur = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.ORGANIZER))
				.collect(Collectors.toList()).size();
		model.addAttribute("listOrganisateur", listOrganisateur);
		
		int listAdmin = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.ADMIN))
				.collect(Collectors.toList()).size();
		model.addAttribute("listAdmin", listAdmin);
		
		List<Event>listEvents = eventService.getAllEvents();
		model.addAttribute("listEvents", listEvents);
		
		List<Event>listFutureEvents = eventService.getAllFutursEvents(listEvents);
		model.addAttribute("listFutureEvents", listFutureEvents);
		
		List<Event>listPastEvents = eventService.getAllPastEvents(listEvents);
		model.addAttribute("listPastEvents", listPastEvents);
		
		List<EventType>listEventTypes = eventTypeService.getAllEventTypes();
		model.addAttribute("listEventTypes", listEventTypes);
		
		List<Adress>listAdress = adressService.getAllAdress();
		model.addAttribute("listAdress", listAdress);
		
		model.addAttribute("getAllUser", listUsers);
		
		System.out.println("In admin acceuil end");
		return "/admin/adminPage";
	}
	
	@GetMapping("/profil")
	public String getprofil(Model model)  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		return "/admin/profil";
	}	
	
//	@RequestMapping("/list")
//	public String listAllUsers(Model model, String keyword) {
//		List<User> listUsers = userService.getAllUsers();
//		model.addAttribute("getAllUser", listUsers);
//		return "/admin/adminListUsers";	
//	}
	
	@RequestMapping("/list")
	public String getEvent(@RequestParam(name="searchString", required = false) String searchString, Model model) {
		List<User> listUsers = userService.getAllUsers();
	
	if(!(searchString == null) && !(searchString.isEmpty()) && !(searchString.trim().isEmpty())) {
		listUsers = listUsers.stream()
				.filter(u -> u.getFirstName().toLowerCase().contains(searchString.toLowerCase()) ||		
						u.getLastName().toLowerCase().contains(searchString.toLowerCase()))
				.collect(Collectors.toList());
	}
		model.addAttribute("searchString", searchString);
		model.addAttribute("getAllUser", listUsers);
		return "/admin/adminListUsers";	
	}
	
	@RequestMapping("/list/event")
	public String listEvent(Model model, String keyword) {
		List<Event>listEvents = eventService.getAllEvents();
		model.addAttribute("listEvents", listEvents);
		return "/admin/adminListEvent";
		
	}
	
	@RequestMapping("/list/eventType")
	public String listEventType(Model model, String keyword) {
		List<EventType>listEventTypes = eventTypeService.getAllEventTypes();
		model.addAttribute("listEventTypes", listEventTypes);
		return "/admin/adminListEventType";
		
	}
	
	@RequestMapping("/list/event/future")
	public String listFutureEvent(Model model, String keyword) {
		List<Event>listEvents = eventService.getAllEvents();
		model.addAttribute("listEvents", listEvents);
		
		List<Event>listFutureEvents = eventService.getAllFutursEvents(listEvents);
		model.addAttribute("listFutureEvents", listFutureEvents);
		return "/admin/adminListFutureEvent";	
		
	}
	
	@RequestMapping("/list/event/past")
	public String listPastEvent(Model model, String keyword) {
		List<Event>listEvents = eventService.getAllEvents();
		model.addAttribute("listEvents", listEvents);
		
		List<Event>listPastEvents = eventService.getAllPastEvents(listEvents);
		model.addAttribute("listPastEvents", listPastEvents);
		return "/admin/adminListPastEvent";	
		
		
	}
	
	@RequestMapping("/list/location")
	public String listAdresse(Model model, String keyword) {
		
	List<Adress>listAdress = adressService.getAllAdress();
	model.addAttribute("listAdress", listAdress);
	return "/admin/adminListLocation";	
	}
	
	@RequestMapping("/list/participant")
	public String listParticipant(Model model, String keyword) {
		List<User> listUsers = userService.getAllUsers();
		listUsers = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.PARTICIPANT))
				.collect(Collectors.toList());
		model.addAttribute("getAllUser", listUsers);
		return "/admin/adminListParticipant";	
	}
	
	@RequestMapping("/list/organisateur")
	public String listOrganisateur(Model model, String keyword) {
		List<User> listUsers = userService.getAllUsers();
		listUsers = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.ORGANIZER))
				.collect(Collectors.toList());
		model.addAttribute("getAllUser", listUsers);
		return "/admin/adminListOrganisateur";	
	}
	
	@RequestMapping("/list/prestataire")
	public String listPrestataire(Model model, String keyword) {
		List<User> listUsers = userService.getAllUsers();
		listUsers = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.PROVIDER))
				.collect(Collectors.toList());
		model.addAttribute("getAllUser", listUsers);
		return "/admin/adminListPrestataire";	
	}
	
	@RequestMapping("/list/admin")
	public String listAdmin(Model model, String keyword) {
		List<User> listUsers = userService.getAllUsers();
		listUsers = listUsers.stream().filter(u -> u.getRoles().get(0).getRole().equals(GoeventApplication.ADMIN))
				.collect(Collectors.toList());
		model.addAttribute("getAllUser", listUsers);
		return "/admin/adminListAdmin";	
	}
	
//	 @GetMapping("/delete/{id}")
//	    public String deleteUser(@PathVariable("id") Integer id, Model model) {
//	        User user = userRepository.findById(id)
//	            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
//	       
//	        System.out.println(id);
//	        System.out.println(user.getId());
//	        return "/admin/list";
//	  }
//	 
//	 @GetMapping("/deleteUserPost/{id}")
//	    public String deleteUserPost(@PathVariable("id") Integer id, Model model) {
//	        User user = userRepository.findById(id)
//	            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
//	  //      userService.deleteUser(1);
//	        System.out.println(user);
//	        userRepository.delete(user);
//	        System.out.println(id);
//	        System.out.println("In post user id: " + user.getId());
//	        
//	        return "redirect:/admin/list";
//	  }
	 
	 @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
      
        model.addAttribute("users", userRepository.findAll());
        User user = userRepository.getById(id);
        userRepository.delete(user); 
        return "redirect:/admin/list";
  }
	 
	 @GetMapping("enable/{id}")
		//@ResponseBody
	    public String enableUserAcount(@PathVariable ("id") int id) {
			 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		     user.setActive(1);
		     userRepository.save(user);
	    	return "redirect:/admin/list";
	    }
		
		@GetMapping("disable/{id}")
		//@ResponseBody
		public String disableUserAcount(@PathVariable ("id") int id) {
			 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
		     user.setActive(0);
		     userRepository.save(user);
		     return "redirect:/admin/list";
	    }
	 
		

	 

}
