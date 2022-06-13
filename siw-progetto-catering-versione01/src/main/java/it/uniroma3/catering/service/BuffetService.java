package it.uniroma3.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.catering.model.Buffet;
import it.uniroma3.catering.model.Chef;
import it.uniroma3.catering.model.Piatto;
import it.uniroma3.catering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Autowired
	private ChefService chefService;
	
	@Transactional
	public void save(Buffet buffet, Chef chef) { 
		// Il save è di tipo transactional
		buffet.setChef(chef);
		chef.addBuffet(buffet);
		buffetRepository.save(buffet);
	}
	
	@Transactional
	public void delete(Buffet buffet) { 
		// Il save è di tipo transactional
		buffetRepository.delete(buffet);
	}
	
	@Transactional
	public void deleteById(Long id) {
		buffetRepository.deleteById(id);
		
	}
	
	public Buffet findById (Long id) {
		return buffetRepository.findById(id).get();
	}
	
	public List<Buffet> findAll() {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for (Buffet b : buffetRepository.findAll()) {
			buffets.add(b);
		}
		return buffets;
	}
	
	// Metodo che risponde ad una validazione del Validator
	public boolean alreadyExists(Buffet buffet) {
		//return buffetRepository.existsByNome(buffet.getNome());
		return buffetRepository.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());	
	}
	
	public List<Buffet> findAllByChef(Chef chef) {
		List<Buffet> buffets = new ArrayList<Buffet>();
		for(Buffet b : buffetRepository.findAllByChef(chef)) {
			buffets.add(b);
		}
		return buffets;
	}
	
	@Transactional
	public void addPiatto(Buffet buffet, Piatto piatto) {
		buffet.addPiatto(piatto);
		buffetRepository.save(buffet);
	}

	@Transactional
	public void removePiattoFromBuffet(Buffet buffet, Piatto piatto) {
		buffet.removePiatto(piatto);
		buffetRepository.save(buffet);
	}
}