package fr.groupe1.goevent.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.groupe1.goevent.GoeventApplication;
import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.EventType;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IAdressRepository;
import fr.groupe1.goevent.repository.IEventRepository;
import fr.groupe1.goevent.repository.IEventTypeRepository;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.UserService;

@Controller
@RequestMapping("/participant")
public class ParticipantController {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	IEventTypeRepository eventTypeRepository;
	
	
	@Autowired
	IEventRepository eventRepository;
	
	@Autowired
	IAdressRepository adressRepository;
	
	@RequestMapping("/dashboard")
	public String getDashboard()  {
		return "/participant/participant_dashboard_accueil";
	}
	
	
	// PROFIL
	@GetMapping("/profil")
	public String getprofil(Model model)  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		return "/participant/profil";
	}	
	
	@PostMapping("/profil")
	public String updateProfil (@ModelAttribute("user") User user, Model model) {
		userService.updateUser(user);
		model.addAttribute("message", "Vos modifications ont bien été enregistrées");
		 return "/participant/profil";
	}
	
		
	
		// EVENEMENTS FUTURS
	@RequestMapping(value = "/prochainsevenements")
	public String getAllFutursEvents(@RequestParam(name="searchString", required = false) String searchString ,
			@RequestParam(name="searchCity", required = false) String searchCity,
			@RequestParam(name="searchTheme", required = false) String searchTheme,
			Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		List<Event> listeEvent = user.getParticipantEvents();
		listeEvent = eventService.getAllFutursEvents(listeEvent);
		
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
		model.addAttribute("listeEvent", listeEvent);
		model.addAttribute("themes",themes);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchCity", searchCity);
		model.addAttribute("eventTypes", eventTypes);	
		model.addAttribute("searchTheme", searchTheme);
		return "participant/participant_next_events";
	}
	
	
	
	@RequestMapping("/participant_detailNextEvent/{id}" )
	public String getdetailNextEvent(@PathVariable("id") Integer id, Model model)  {		
			model.addAttribute("event", eventService.getEventById(id));				
			return "participant/participant_detailNextEvent";
	}
	
	
	
	
	
	// EVENEMENTS PASSES
	@RequestMapping(value = "/evenementspasses")
	public String getAllPastEvents(@RequestParam(name="searchString", required = false) String searchString ,
				@RequestParam(name="searchTheme", required = false) String searchTheme,
				@RequestParam(name="searchCity", required = false) String searchCity,
				Model model) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.getUserByEmail(auth.getName());
			List<Event> listeEvent = user.getParticipantEvents();
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
			model.addAttribute("eventTypes", eventTypes);
			model.addAttribute("searchTheme", searchTheme);
		return "participant/participant_past_event";
	}
	
//	@RequestMapping("/participant_detailPastEvent")
//	public String getdetailPastEvent()  {
//		return "/participant/participant_detailPastEvent";
//	}
	
	

	@RequestMapping("/participant_detailPastEvent/{id}" )
	public String getdetailPastEvent(@PathVariable("id") Integer id, Model model)  {		
			model.addAttribute("event", eventService.getEventById(id));				
			return "participant/participant_detailPastEvent";
	}
	
	
}
