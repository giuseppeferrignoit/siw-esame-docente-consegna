package it.uniroma3.catering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.catering.model.Utente;

public interface UserRepository extends CrudRepository<Utente, Long> {


}
