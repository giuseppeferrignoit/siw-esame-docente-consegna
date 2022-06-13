package it.uniroma3.catering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Ingrediente;
import it.uniroma3.catering.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator {

	@Autowired
	private IngredienteService ingredienteService;
	
	
	// Una specifica validazione per non duplicati
	// In questo caso per gli ingredienti non facciamo nessun controllo di duplicazione
	@Override
	public void validate(Object o, Errors errors) {
		if(this.ingredienteService.alreadyExists((Ingrediente)o)) {
			errors.reject("ingrediente.duplicato");
		}
	}
	
	// Indica quale è la classe su cui facciamo le validazioni
	@Override
	public boolean supports(Class<?> aClass) {
		return Ingrediente.class.equals(aClass);
	}
}
