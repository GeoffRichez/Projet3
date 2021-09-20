package fr.groupe1.goevent.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.groupe1.goevent.GoeventApplication;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.Role;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IEventTypeRepository;
import fr.groupe1.goevent.repository.IRoleRepository;
import fr.groupe1.goevent.repository.IUserRepository;
import fr.groupe1.goevent.service.EmailService;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.UserService;

@Controller
@RequestMapping
public class HomeController {

	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	@Autowired
	IEventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private IUserRepository usersrepo;

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IEventTypeRepository eventTypeRepository;
	
	@Autowired
	private EmailService emailService;

//	@RequestMapping(value = "/allFuturEvents")
//	public String getEvent(Model model) {
//		String evenType = "lll";
//		String searhString = "lll";
//		model.addAttribute("listeEvent", eventService.getAllFutursEvents());
//		model.addAttribute("eventType", evenType);
//		model.addAttribute("searhString", searhString);
//		return "/allFuturEvents";
//	}

	@RequestMapping("/allFuturEvents")
	public String getEvent(@RequestParam(name="searchString", required = false) String searchString , @RequestParam(name="searchTheme", required = false) String searchTheme, Model model) {

		List<Event> listeEvent =  eventService.getAllFutursEvents();
		if(!(searchString == null) && !(searchString.isEmpty()) && !(searchString.trim().isEmpty())) {
			listeEvent = listeEvent.stream()
					.filter(e -> e.getTitle().toLowerCase().contains(searchString.toLowerCase()))
					.collect(Collectors.toList());
		}		
		if(!(searchTheme == null) && !(searchTheme.isEmpty()) && !(searchTheme.trim().isEmpty())) {
			listeEvent = listeEvent.stream()
					.filter(e -> e.getTheme().toLowerCase().contains(searchTheme.toLowerCase()))
					.collect(Collectors.toList());
		}
		List<String>themes =listeEvent.stream().map(Event::getTheme).collect(Collectors.toList());
		model.addAttribute("listeEvent", listeEvent);
		model.addAttribute("themes",themes);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchTheme", searchTheme);
		return "/allFuturEvents";
	}
	

	@GetMapping({ "/index"})
	public String home1() {
		return "index";
	}

	@GetMapping({ "/indexrole" })
	public String index() {
		String dashboard = "none";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<User> users = usersrepo.findByEmail(auth.getName());
		User user = users.get(0);
		List<Role> roleUser = user.getRoles();
		Role r = roleUser.get(0);
		String role = r.getRole();
		switch (role) {
		case GoeventApplication.ADMIN:
			dashboard = "admin/accueil";
			break;
		case GoeventApplication.PARTICIPANT:
			dashboard = "participant/prochainsevenements";
			break;
		case GoeventApplication.ORGANIZER:
			dashboard = "dashboardOrganisateur/Accueil";
			break;
		case GoeventApplication.PROVIDER:
			dashboard = "prestataire/pageaccueil";
			break;
		case "none":
			dashboard = "index";
			break;
		}
		System.out.println(dashboard);
		return "redirect:/" + dashboard;
	}
	
	@GetMapping("")
	public String home() {
		return"index";
	}
	

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("/createAccount")
	public String createAccount(Model model) {
		User user = new User();
		List<Role> listRoles = (List<Role>) roleRepository.findAll();
		List<Role> rolesroles=new ArrayList<>();
		for(Role r: listRoles) {
			if (!r.getRole().equalsIgnoreCase(GoeventApplication.ADMIN)) {
				rolesroles.add(r);
			}
		}

		model.addAttribute("user", user);
		model.addAttribute("rolesroles", rolesroles);
		return "createAccount";
	}

	@PostMapping("/createAccount")
	public String createAccount(@Valid User user, Model model) {
//		////Début Upload Image
//    	StringBuilder fileName = new StringBuilder();
//    	MultipartFile file = files[0];
//    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
//    	fileName.append(file.getOriginalFilename());
//		  try {
//			Files.write(fileNameAndPath, file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		 user.setPicture(fileName.toString());
//    	///Fin Upload Image
		if (userService.addUser(user) != null) {
			
			emailService.SendConfirmationAccountCreated(user.getEmail());
			return "redirect:login";

		} else {
			model.addAttribute("message", "Adresse E-mail déjà utilisé! ");
			return "createAccount";
		}
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
	
}
