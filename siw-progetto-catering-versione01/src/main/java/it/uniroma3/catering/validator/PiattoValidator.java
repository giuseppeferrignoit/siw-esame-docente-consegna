package it.uniroma3.catering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.catering.model.Piatto;
import it.uniroma3.catering.service.PiattoService;


@Component
public class PiattoValidator implements Validator {

	@Autowired
	private PiattoService piattoService;

	// Una specifica validazione per non duplicati
	@Override
	public void validate(Object o, Errors errors) { // (1)Oggetto da validare (2)esito validazione
		if(this.piattoService.alreadyExists((Piatto)o)) {
			// si rejecta la validazione registrando un codice di errore
			errors.reject("piatto.duplicato"); 
		}
	}
	
	// Indica quale è la classe su cui facciamo le validazioni
	@Override
	public boolean supports(Class<?> aClass) {
		return Piatto.class.equals(aClass);
	}
}
