package fr.groupe1.goevent.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.groupe1.goevent.GoeventApplication;
import fr.groupe1.goevent.entities.Event;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.service.IEventService;
import fr.groupe1.goevent.service.IUserService;
import fr.groupe1.goevent.service.UserService;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	IEventService eventService; 
	
	@Autowired
	IUserService userService;
	
	
	@GetMapping("detailsOfAnEvent/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") Integer id, Model model) {
		Event event = eventService.getEventById(id);		
		model.addAttribute("event", event);
		System.out.println("In details of an event");
		return "detailsOfAnEvent";
	}
	
	
	@PostMapping("detailsOfAnEvent/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Event event = eventService.getEventById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		if(!event.getParticipants().contains(user)) {
			event.getParticipants().add(user);
			redirectAttributes.addFlashAttribute("message", "Votre inscription à l'événement est confirmée");
		} else {
			redirectAttributes.addFlashAttribute("message", "Vous êtes déja inscrit à l'événement");
		}
        eventService.updateEvent(event);
        
		return "redirect:/event/detailsOfAnEvent/{id}";
	}

}
