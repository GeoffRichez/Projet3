package fr.groupe1.goevent.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.Prestation;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IAdressRepository;
import fr.groupe1.goevent.repository.IEventRepository;
import fr.groupe1.goevent.repository.IEventTypeRepository;
import fr.groupe1.goevent.repository.IPrestationRepository;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.IUserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/dashboardOrganisateur")
public class OrganisateurController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IEventRepository eventRepository;
	
	@Autowired
	IPrestationRepository prestationReposiory;
	
	@Autowired
	IEventTypeRepository eventTypeRepository;
	
	@Autowired
	IAdressRepository adressRepository;

	@RequestMapping("/Accueil")
	public String getAllFutursEvents(@RequestParam(name="searchString", required = false) String searchString ,
			@RequestParam(name="searchCity", required = false) String searchCity,
			@RequestParam(name="searchTheme", required = false) String searchTheme,
			Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		List<Event> listeEvent = user.getOrganizerEvents();
//		listeEvent = eventService.getAllFutursEvents();
		
		List<EventType> eventTypes = (List<EventType>) eventTypeRepository.findAll();
		if(!(searchString == null) && !(searchString.isEmpty()) && !(searchString.trim().isEmpty())) {
			listeEvent = listeEvent.stream()
					.filter(e -> e.getTitle().toLowerCase().contains(searchString.toLowerCase()))
					.collect(Collectors.toList());
		}
		if(!(searchCity == null) && !(searchCity.isEmpty()) && !(searchCity.trim().isEmpty())) {
		listeEvent = listeEvent.stream()
				.filter(e -> e.getLocation().getCity().equals(searchCity))
				.collect(Collectors.toList());
		}
		if(!(searchTheme == null) && !(searchTheme.isEmpty()) && !(searchTheme.trim().isEmpty())) {
			listeEvent = listeEvent.stream()
					.filter(e -> e.getTheme().toLowerCase().contains(searchTheme.toLowerCase()))
					.collect(Collectors.toList());
		}		
		List<Adress> adresses = eventService.getAllCities(listeEvent);
		List<String>cities = adresses.stream().map(Adress::getCity).collect(Collectors.toList());
		List<String>themes = listeEvent.stream().map(Event::getTheme).collect(Collectors.toList());
		model.addAttribute("cities",cities);
		model.addAttribute("themes",themes);
		model.addAttribute("listeEvent", listeEvent);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchCity", searchCity);
		model.addAttribute("searchTheme", searchTheme);
		model.addAttribute("eventTypes", eventTypes);
		model.addAttribute("user", user);
	/*public String getOrgDashboard(Model model) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		List<Event> listeEvent = user.getOrganizerEvents();
		System.out.println(listeEvent);
		model.addAttribute("listeEvent", listeEvent);*/
		
		System.out.println(themes);
		return "/organisateur/dashboardOrganisateurAccueil";
	}
	
	@RequestMapping("/evenementspasses")
	public String getAllPastEvents(@RequestParam(name="searchString", required = false) String searchString ,
				@RequestParam(name="searchCity", required = false) String searchCity,
				@RequestParam(name="searchTheme", required = false) String searchTheme,
				Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.getUserByEmail(auth.getName());
			List<Event> listeEvent = user.getOrganizerEvents();
			listeEvent = eventService.getAllPastEvents(listeEvent);
			
			List<EventType> eventTypes = (List<EventType>) eventTypeRepository.findAll();
			if(!(searchString == null) && !(searchString.isEmpty()) && !(searchString.trim().isEmpty())) {
				listeEvent = listeEvent.stream()
						.filter(e -> e.getTitle().toLowerCase().contains(searchString.toLowerCase()))
						.collect(Collectors.toList());
			}			
			if(!(searchCity == null) && !(searchCity.isEmpty()) && !(searchCity.trim().isEmpty())) {
			listeEvent = listeEvent.stream()
					.filter(e -> e.getLocation().getCity().equals(searchCity))
					.collect(Collectors.toList());
			}
			if(!(searchTheme == null) && !(searchTheme.isEmpty()) && !(searchTheme.trim().isEmpty())) {
				listeEvent = listeEvent.stream()
						.filter(e -> e.getTheme().toLowerCase().contains(searchTheme.toLowerCase()))
						.collect(Collectors.toList());
			}		
			List<Adress> adresses = eventService.getAllCities(listeEvent);
			List<String>cities = adresses.stream().map(Adress::getCity).collect(Collectors.toList());
			List<String>themes = listeEvent.stream().map(Event::getTheme).collect(Collectors.toList());
			model.addAttribute("cities",cities);
			model.addAttribute("themes", themes);
			model.addAttribute("listeEvent", listeEvent);
			model.addAttribute("searchString", searchString);
			model.addAttribute("searchCity", searchCity);
			model.addAttribute("searchTheme", searchTheme);
			model.addAttribute("eventTypes", eventTypes);	
			model.addAttribute("user", user);
		return "/organisateur/dashboardOrganisateurHistorique";
	}
	
		
	@GetMapping("/profil")
	public String getprofil(Model model)  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		return "/organisateur/OrganisateurProfil";
	}	
		
	@PostMapping("/profil")
	public String updateProfil (@ModelAttribute("user") User user,  Model model) {
		userService.updateUser(user);
		System.out.println(user);
		model.addAttribute("messages", "Vos modifications ont bien été enregistrées");
		return "/organisateur/OrganisateurProfil";
	}
	
	
	@RequestMapping("/detail/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") Integer id, Model model) {
		Event event = eventService.getEventById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		List<User> participants = event.getParticipants();
		Integer numberOfParticipants = participants.size();
		model.addAttribute("event", event);
		model.addAttribute("numberOfParticipants", numberOfParticipants);
		model.addAttribute("user", user);
		return "/organisateur/dashboardOrganisateurDetail";
	}
	
	
	@RequestMapping("/modification/{id}")
	public String showProviderFormToUpdate1(@PathVariable("id") Integer id, Model model) {
		Event event = eventService.getEventById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		System.out.println("In modification");
		return "/organisateur/dashboardOrganisateurModification";
	}
	
	@PostMapping("/modification/{id}")
	public String modifyEvent(@PathVariable("id") Integer id, @ModelAttribute("event") Event event, Model model) {
		System.out.println(event.getIdEvent());
		event.setIdEvent(id);
		System.out.println(event.getIdEvent());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		event.setOrganizer(user);
		eventService.updateEvent(event);
		
		return "organisateur/dashboardOrganisateurAccueil";
	}
	
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	
	@GetMapping("/creation")  
	public String showAddEventForm(Model model) {
		Event event = new Event();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		List<Prestation> prestations = (List<Prestation>) prestationReposiory.findAll();
		List<EventType> eventTypes = (List<EventType>) eventTypeRepository.findAll();   
		model.addAttribute("prestations", prestations);
		model.addAttribute("event", event);
		model.addAttribute("eventTypes", eventTypes);
		return "/organisateur/dashboardOrganisateurCreation";
	}
	
	@PostMapping("/creation")
	public String addEvent(@Valid  Event event, Model model, @RequestParam("files") MultipartFile[] files) {
		System.out.println("Event :" + event.getTitle());
		System.out.println("Prestations :" + event.getPrestations());
//		if (result.hasErrors()) {
//			return "dashboardOrganisateurAccueil/dashboardOrganisateurCreation";
//		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		event.setOrganizer(user);
		
//		List<Prestation> prestations = (List<Prestation>) prestationReposiory.findAll();
//		event.setPrestations(prestations);
		
////Début Upload Image

    	StringBuilder fileName = new StringBuilder();
    	MultipartFile file = files[0];
    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	
    	fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		 event.setPicture(fileName.toString());

    	///Fin Upload Image
		
		
		eventService.addEvent(event);
		return "redirect:/dashboardOrganisateur/Accueil";
	}
	
	 @RequestMapping("/")
	  public void handleRequest() {
	      throw new RuntimeException("test exception");
	  }
	
	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Integer id, Model model) {
		Event event = eventRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
	    eventRepository.delete(event);
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.getUserByEmail(auth.getName());
//		model.addAttribute("user", user);
	    System.out.println(event.getTitle() + "   "  +  event.getIdEvent());
        return "redirect:/dashboardOrganisateur/Accueil";
    }
	
	
}
