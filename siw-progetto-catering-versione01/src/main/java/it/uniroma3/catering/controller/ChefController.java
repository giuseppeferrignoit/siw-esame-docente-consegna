package it.uniroma3.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.validator.ChefValidator;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;
	
	@Autowired
	private BuffetService buffetService;
	
	/*
	 * convenzione: get per le operazioni di lettura, post per gli aggiornamenti
	 * il path è associato alle classi del dominio
	*/
	
	// METODO POST PER INSERIRE UN NUOVO BUFFET

	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute(value="chef") Chef chef, 
			BindingResult bindingResult, Model model) {

		/* Se non ci sono errori inserisce la ricorrenza di chef 
		 * tramite la save del service 
		 * */
		
		/* Si invoca anche il metodo validate del Validator, oltre 
		 * alle validazioni automatiche dell'annotazione @valid
		 */
		this.chefValidator.validate(chef, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			
			this.chefService.save(chef); // salvo un oggetto chef
			model.addAttribute("chef", chef);
			
			// Ogni metodo ritorna la stringa col nome della vista successiva
			// se NON ci sono errori si va alla pagina di visualizzazione dati inseriti
			return "chef.html"; 
		}
		else {
			model.addAttribute("chef", chef);
			// se ci sono errori si rimanda alla form di inserimento
			return "chefForm.html"; 
		}
	}
	
	
	// METODI PER DELETE
	
		@GetMapping("/confermaDeleteChef/{id}")
		public String confermaDeleteChef(@PathVariable("id") Long id, Model model) {
			this.chefService.deleteById(id);
			model.addAttribute("chefs", this.chefService.findAll());
			return "chefs.html";
		}
		
		@GetMapping("/deleteChef/{id}")
		public String deleteChef(@PathVariable("id") Long id, Model model) {
			model.addAttribute("chef", this.chefService.findById(id));
			return "deleteChef.html";
		}

	// METODI GET

	// richiede un singolo chef tramite id
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		// id è una variabile associata al path
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		// ritorna la pagina con i dati dell'entità richiesta
		return "chef.html";
	}
	
	// richiede un singolo chef tramite id per l'utente semplice
		@GetMapping("/chefUtente/{id}")
		public String getChefUtente(@PathVariable("id")Long id, Model model) {
			// id è una variabile associata al path
			Chef chef = chefService.findById(id);
			model.addAttribute("chef", chef);
			// ritorna la pagina con i dati dell'entità richiesta
			return "chefUtente.html";
		}
	
	// richiede tutti gli chef, non c'è id
	@GetMapping("/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}
	
	// richiede tutti gli chef per l'utente semplice, non c'è id
	@GetMapping("/chefsUtente")
	public String getChefsUtente(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefsUtente.html";
	}
	
	@GetMapping("/chefForm")
	public String chefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	//richiede tutti i buffet dello chef passato nel path
	@GetMapping("/chef/{id}/buffets")
	public String getBuffet(@Valid @PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("buffets", buffetService.findAllByChef(chef));
		return "buffets.html";
	}
	
	//richiede tutti i buffet dello chef passato nel path per utenti semplici
	@GetMapping("/chefUtente/{id}/buffetsUtente")
	public String getBuffetUtente(@Valid @PathVariable("id") Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("buffets", buffetService.findAllByChef(chef));
		return "buffetsUtente.html";
	}
}
