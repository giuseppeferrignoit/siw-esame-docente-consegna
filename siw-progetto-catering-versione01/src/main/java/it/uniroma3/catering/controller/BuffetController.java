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
import it.uniroma3.catering.model.Ingrediente;
import it.uniroma3.catering.model.Piatto;
import it.uniroma3.catering.service.BuffetService;
import it.uniroma3.catering.service.ChefService;
import it.uniroma3.catering.service.PiattoService;
import it.uniroma3.catering.validator.BuffetValidator;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private BuffetValidator buffetValidator;

	@Autowired
	private ChefService chefService;

	@Autowired
	private PiattoService piattoService;


	/*
	 * convenzione: get per le operazioni di lettura, post per gli aggiornamenti
	 * il path è associato alle classi del dominio
	 */

	// METODO POST PER INSERIRE UN NUOVO BUFFET

	@PostMapping("/chef/{idChef}/buffet")
	public String addBuffet(@Valid @ModelAttribute(value="buffet") Buffet buffet, @PathVariable("idChef") Long idChef,
			BindingResult bindingResult, Model model) {

		// Creo una entità Buffet dai dati di input della stringa HTTP usando i metodi Get
		// I risultati della validazione sono riportati in BindingResult
		/* 
		 * Se non ci sono errori inserisce la ricorrenza di Buffet 
		 * tramite la save del service 
		 * */

		/* Si invoca anche il metodo validate del Validator, oltre 
		 * alle validazioni automatiche dell'annotazione @valid
		 */
		this.buffetValidator.validate(buffet, bindingResult);

		if (!bindingResult.hasErrors()) { 

			// Se non ci sono errori
			this.buffetService.save(buffet, chefService.findById(idChef)); // Salvo un oggetto Buffet
			model.addAttribute("buffet", buffet);
			model.addAttribute("piattiAssenti", this.piattoService.findPiattiNotInBuffet(buffet));

			// Ogni metodo ritorna la stringa col nome della vista successiva
			return "addPiattiToBuffet.html"; 
		}
		else {
			model.addAttribute("buffet", buffet);
			return "buffet.html"; 
		}
	}

	//aggiunge un piatto al buffet in fase di creazione di quest'ultimo

	@GetMapping("/buffet/{idBuffet}/{idPiatto}")
	public String addPiatto(@PathVariable("idBuffet")Long idBuffet, @PathVariable("idPiatto")Long idPiatto, Model model) {
		Buffet buffet = this.buffetService.findById(idBuffet);
		Piatto piatto = this.piattoService.findById(idPiatto);
		this.buffetService.addPiatto(buffet, piatto);
		model.addAttribute("buffet", buffet);
		model.addAttribute("piattiAssenti", this.piattoService.findPiattiNotInBuffet(buffet));
		return "addPiattiToBuffet";

	}
	
	//Creazione di un nuovo piatto da aggiungere al buffet il cui id è passato nel path
	@GetMapping("/buffet/{id}/nuovoPiatto")
	public String aggiungiPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		model.addAttribute("piatto", new Piatto());
		return "piattoForm.html";
	}

	// METODI PER DELETE

	@GetMapping("/confermaDeleteBuffet/{id}")
	public String confermaDeleteBuffet(@PathVariable("id") Long id, Model model) {
		this.buffetService.deleteBuffet(id);
		model.addAttribute("buffets", this.buffetService.findAll());
		return "buffets.html";
	}

	@GetMapping("/deleteBuffet/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "deleteBuffet.html";
	}
	
	//elimina un piatto da un buffet
	@GetMapping("/buffet/{idBuffet}/removePiatto/{idPiatto}")
	public String removePiattoDaBuffet(@PathVariable("idBuffet") Long idBuffet,
			@PathVariable("idPiatto") Long idPiatto, Model model) {
		Buffet buffet = this.buffetService.findById(idBuffet);
		Piatto piatto = this.piattoService.findById(idPiatto);
		model.addAttribute("piatto", piatto);
		model.addAttribute("buffet", buffet);
		return "deletePiattoDaBuffet.html";
	}
	
	//elimina un piatto da un buffet
		@GetMapping("/buffet/{idBuffet}/confermaDeletePiattoDaBuffet/{idPiatto}")
		public String removePiatto(@PathVariable("idBuffet") Long idBuffet,
				@PathVariable("idPiatto") Long idPiatto, Model model) {
			Buffet buffet = this.buffetService.findById(idBuffet);
			Piatto piatto = this.piattoService.findById(idPiatto);
			this.buffetService.removePiattoFromBuffet(buffet, piatto); // Cancellazione
			model.addAttribute("buffet", buffet);
			model.addAttribute("piatti", this.piattoService.findPiattiInBuffet(buffet));
			model.addAttribute("piattiAssenti", this.piattoService.findPiattiNotInBuffet(buffet));
			return "buffet.html";
		}


	//METODI PER UPDATE

	@GetMapping("/updateBuffet/{id}")
	public String updateBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("piattiAssenti", this.piattoService.findPiattiNotInBuffet(buffet));
		return "addPiattiToBuffet.html";
	}

	// METODI GET

	// richiede un singolo buffet tramite id
	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) { // id è una variabile associata al path
		Buffet buffet = this.buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatti", this.piattoService.findPiattiInBuffet(buffet));
		model.addAttribute("piattiAssenti", this.piattoService.findPiattiNotInBuffet(buffet));
		return "buffet.html"; // ritorna la pagina con i dati dell'entità richiesta
	}
	
	// richiede un singolo buffet tramite id, per l'utente semplice
		@GetMapping("/buffetUtente/{id}")
		public String getBuffetUtente(@PathVariable("id") Long id, Model model) { // id è una variabile associata al path
			Buffet buffet = buffetService.findById(id);
			model.addAttribute("buffet", buffet);
			model.addAttribute("piatti", this.piattoService.findPiattiInBuffet(buffet));
			return "buffetUtente.html"; // ritorna la pagina con i dati dell'entità richiesta
		}

	// richiede tutti i buffets, non c'è id
	@GetMapping("/buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}
	
	
	// richiede tutti i buffets per l'utente semplice, non c'è id
		@GetMapping("/buffetsUtente")
		public String getBuffetsUtente(Model model) {
			List<Buffet> buffets = buffetService.findAll();
			model.addAttribute("buffets", buffets);
			return "buffetsUtente.html";
		}
	

	// crea un nuovo buffet associato allo chef passato nel path
	@GetMapping("/chef/{id}/nuovoBuffet")
	public String createBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = new Buffet();
		model.addAttribute("chef", chefService.findById(id));
		model.addAttribute("buffet", buffet);
		return "buffetForm.html";
	}


	//crea un nuovo buffet
	@GetMapping("/newBuffet")
	public String createBuffet(Model model) {
		model.addAttribute("chefs", chefService.findAll());
		return "newBuffet.html";
	}

}