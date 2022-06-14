package it.uniroma3.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Ingrediente;
import it.uniroma3.catering.model.Piatto;
import it.uniroma3.catering.repository.IngredienteRepository;
import it.uniroma3.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public void save(Piatto piatto) { 
		// Il save è di tipo transactional
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public void delete(Piatto piatto) { 
		// Il save è di tipo transactional
		piattoRepository.delete(piatto);
	}
	
	@Transactional
	public void deleteById(Long id) {
		Piatto piatto = this.findById(id);
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		for (Ingrediente ingrediente : ingredienti) {
			ingrediente.getPiatti().remove(piatto);
			ingredienteRepository.save(ingrediente);
		}
		piattoRepository.deleteById(id);
	}
	
	public Piatto findById (Long id) {
		return piattoRepository.findById(id).get();
	}
	
	public List<Piatto> findAll() {
		List<Piatto> piatti = new ArrayList<Piatto>();
		for (Piatto p : piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	
	// Metodo che risponde ad una validazione del Validator
	public boolean alreadyExists(Piatto piatto) {
		return piattoRepository.existsByNome(piatto.getNome());
	}
	
	public List<Piatto> findPiattiNotInBuffet(Buffet buffet) {
		List<Piatto> piatti = this.findAll();
		for (Piatto p : buffet.getPiatti()) {
			piatti.remove(p);
		}
		return piatti;
	}
	
	public List<Piatto> findPiattiInBuffet(Buffet buffet) {
		List<Piatto> piatti = this.findAll();
		for(Piatto p : this.findPiattiNotInBuffet(buffet))
			piatti.remove(p);
		return piatti;
	}

	@Transactional
	public void addIngrediente(Piatto piatto, Ingrediente ingrediente) {
		piatto.addIngrediente(ingrediente);
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public void addBuffet(Piatto piatto, Buffet buffet) {
		piatto.addBuffet(buffet);
		piattoRepository.save(piatto);
	}

	@Transactional
	public void removeIngredienteFromPiatto(Piatto piatto, Ingrediente ingrediente) {
		piatto.removeIngrediente(ingrediente);
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public void removeBuffetFromPiatto(Piatto piatto, Buffet buffet) {
		piatto.removeBuffet(buffet);
		piattoRepository.save(piatto);
	}
}
