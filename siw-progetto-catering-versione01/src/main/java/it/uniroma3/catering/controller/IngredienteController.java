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

import it.uniroma3.catering.model.Ingrediente;
import it.uniroma3.catering.model.Piatto;
import it.uniroma3.catering.service.IngredienteService;
import it.uniroma3.catering.service.PiattoService;
import it.uniroma3.catering.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@Autowired
	private PiattoService piattoService;
	
	/*
	 * convenzione: get per le operazioni di lettura, post per gli aggiornamenti
	 * il path è associato alle classi del dominio
	*/
	
	// METODO POST PER INSERIRE UN NUOVO INGREDIENTE

	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute(value="ingrediente") Ingrediente ingrediente, 
			BindingResult bindingResult, Model model) {
		
		/* Se non ci sono errori inserisce la ricorrenza di ingrediente 
		 * tramite la save del service 
		 * */
		
		/* Si invoca anche il metodo validate del Validator, oltre 
		 * alle validazioni automatiche dell'annotazione @valid
		 */
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			
			this.ingredienteService.save(ingrediente); // salvo un oggetto Ingrediente
			model.addAttribute("ingrediente", ingrediente);
			// Ogni metodo ritorna la stringa col nome della vista successiva
			// se NON ci sono errori si va alla pagina di visualizzazione dati inseriti
			return "ingrediente.html"; 
		}
		else {
			model.addAttribute("ingrediente", ingrediente);
			// se ci sono errori si rimanda alla form di inserimento
			return "ingredienteForm.html"; 
		}
	}
	
	//crea e aggiunge un nuovo ingrediente al piatto passato nel path
	@PostMapping("/piatto/{idPiatto}/ingrediente")
	public String addNewIngredienteToPiatto(@PathVariable("idPiatto") Long idPiatto, 
			@Valid @ModelAttribute (value="ingrediente")Ingrediente ingrediente, 
			BindingResult bindingResult, Model model) {
		
		Piatto piatto = this.piattoService.findById(idPiatto);
		
		this.ingredienteValidator.validate(ingrediente, bindingResult);
		if (!bindingResult.hasErrors()) {
			
			this.ingredienteService.save(ingrediente); // salvo un oggetto Ingrediente
			this.piattoService.addIngrediente(piatto, ingrediente);
			model.addAttribute("piatto", piatto);
			// Ogni metodo ritorna la stringa col nome della vista successiva
			// se NON ci sono errori si va alla pagina di visualizzazione dati inseriti
			return "addIngredientiToPiatto.html"; 
		}
		else {
			model.addAttribute("ingrediente", ingrediente);
			// se ci sono errori si rimanda alla form di inserimento
			return "ingredienteForm.html"; 
		}
	}

	// METODI PER DELETE
	
		@GetMapping("/confermaDeleteIngrediente/{id}")
		public String confermaDeleteIngrediente(@PathVariable("id") Long id, Model model) {
			this.ingredienteService.deleteById(id);
			model.addAttribute("ingredienti", this.ingredienteService.findAll());
			return "ingredienti.html";
		}
		
		@GetMapping("/deleteIngrediente/{id}")
		public String deleteIngrediente(@PathVariable("id") Long id, Model model) {
			Ingrediente ingrediente = this.ingredienteService.findById(id);
			model.addAttribute("ingrediente", ingrediente);
			return "deleteIngrediente.html";
		}
	
	// METODI GET

	// richiede un singolo ingrediente tramite id
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id")Long id, Model model) {
		// id è una variabile associata al path
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		// ritorna la pagina con i dati dell'entità richiesta
		return "ingrediente.html";
	}
	
	// richiede un singolo ingrediente tramite id
		@GetMapping("/ingredienteUtente/{id}")
		public String getIngredienteUtente(@PathVariable("id")Long id, Model model) {
			// id è una variabile associata al path
			Ingrediente ingrediente = ingredienteService.findById(id);
			model.addAttribute("ingrediente", ingrediente);
			// ritorna la pagina con i dati dell'entità richiesta
			return "ingredienteUtente.html";
		}
	
	// richiede tutti gli ingredienti, non c'è id
	@GetMapping("/ingredienti")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
	
	// richiede tutti gli ingredienti per utenti semplici, non c'è id
		@GetMapping("/ingredientiUtente")
		public String getIngredientiUtente(Model model) {
			List<Ingrediente> ingredienti = ingredienteService.findAll();
			model.addAttribute("ingredienti", ingredienti);
			return "ingredientiUtente.html";
		}
	
	@GetMapping("/ingredienteForm")
	public String ingredienteForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingredienteForm.html";
	}
	
	/*
	//richiede tutti i piatti in cui è presente l'ingrediente passato nel path
		@GetMapping("/ingrediente/{id}/piatti")
		public String getPiatti(@Valid @PathVariable("id") Long id, Model model) {
			Ingrediente ingrediente = ingredienteService.findById(id);
			model.addAttribute("ingredienti", piattoService.findAllByIngrediente(ingrediente));
			return "ingredienti.html";
		}
		
		//richiede tutti i piatti, per utente semplice, in cui è presente l'ingrediente passato nel path
		@GetMapping("/ingrediente/{id}/piatti")
		public String getPiattiUtente(@Valid @PathVariable("id") Long id, Model model) {
			Ingrediente ingrediente = ingredienteService.findById(id);
			model.addAttribute("ingredienti", piattoService.findAllByIngrediente(ingrediente));
			return "ingredientiUtente.html";
		}
		*/
}
