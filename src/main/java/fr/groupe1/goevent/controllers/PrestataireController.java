package fr.groupe1.goevent.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.groupe1.goevent.GoeventApplication;
import fr.groupe1.goevent.entities.Prestation;
import fr.groupe1.goevent.entities.User;
import fr.groupe1.goevent.repository.IPrestationRepository;
import fr.groupe1.goevent.service.UserService;

@Controller
@RequestMapping("/prestataire")
public class PrestataireController {
	private final IPrestationRepository prestationRepository;
	
	@Autowired
	public PrestataireController(IPrestationRepository prestationRepository) {
		this.prestationRepository = prestationRepository;
	}
	
	@Autowired
	private UserService userService;
	
	// page accueil
	@RequestMapping("/pageaccueil")
	public String getDashboard() {
		return "prestataire/pageaccueil";
	}
	
	// profil de prestataire
	@GetMapping("/profil")
	public String getprofil(Model model)  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", user);
		System.out.println(user);
		return "prestataire/profil";
	}		

	@PostMapping("/profil")
	public String updateProfil (@ModelAttribute("user") User user) {
		userService.updateUser(user);
		 return "prestataire/profil";
	}
	
	// addFormulaire
	@GetMapping("/add")  
	public String showAddPrestationForm(Model model) {
		Prestation prestation = new Prestation();
		model.addAttribute("prestation", prestation);
		return "prestataire/addprestationform";
	}
	
	// addPrestation
	@PostMapping("/add") 
	public String addPrestation(@Valid Prestation prestation) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		prestation.setUser(user);
		prestationRepository.save(prestation);
		return "redirect:/prestataire/show";
	}
	
	// show liste des prestations
//	@GetMapping("/show")
//	public String showListePrestation(Model model) {
//		List<Prestation> prestations = (List<Prestation>) prestationRepository.findAll();
//		model.addAttribute("prestations", prestations);
//		return "prestataire/listeprestations";
//	}
	
	@GetMapping("/show")
    public String listPrestation(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		
    	List<Prestation> prestations = (List<Prestation>) prestationRepository.findAll();
    	long nbr =  prestationRepository.count();
    	if(prestations.size()==0)
    		prestations = null;
    	
        model.addAttribute("prestations", 
        		prestations.stream()
				.filter(e -> e.getUser().getId().compareTo(user.getId()) == 0 )
				.collect(Collectors.toList())		
				);
        
        model.addAttribute("nbr", nbr);
        return "prestataire/listeprestations";
    }

	
	// editFormulaire
	@GetMapping("/editform/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		Prestation prestation = prestationRepository.getById(id);
		model.addAttribute("prestation", prestation);
		
		return "prestataire/update";
	}
	
	// update une prestation
	@PostMapping("/update/{id}")
	public String updatePrestation(@PathVariable Long id, @ModelAttribute("prestation") Prestation prestation, Model model) {
		//System.out.println(prestation);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		prestation.setUser(user);
		prestationRepository.save(prestation);
		model.addAttribute("prestations", prestationRepository.getById(id));
		return "redirect:/prestataire/show";
	}
	
	// delete une prestation
	@GetMapping("/delete/{id}")
	public String deletePrestation(@PathVariable("id") Long id, Model model) {
		Prestation prestation = prestationRepository.getById(id);
        //orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		{prestationRepository.delete(prestation);
		
//		try} catch (Exception E){
//			
//		}
		
        model.addAttribute("users", prestationRepository.findAll());
        return "redirect:/prestataire/show";
    }
}	
}