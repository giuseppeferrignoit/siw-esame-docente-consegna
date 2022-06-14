package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@ManyToMany	
	private List<Buffet> buffets;

	@ManyToMany(mappedBy="piatti")
	private List<Ingrediente> ingredienti;
	
	//-----------------------------
	
	public Piatto() {
		this.ingredienti = new ArrayList<Ingrediente>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngrediente(Ingrediente ingrediente) {
		this.getIngredienti().add(ingrediente);
		ingrediente.getPiatti().add(this);
	}
	
	public void removeIngrediente(Ingrediente ingrediente) {
		this.getIngredienti().remove(ingrediente);
		ingrediente.getPiatti().remove(this);
	}
	
	public void addBuffet(Buffet buffet) {
		this.getBuffets().add(buffet);
		buffet.getPiatti().add(this);
	}
	
	public void removeBuffet(Buffet buffet) {
		this.getBuffets().remove(buffet);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffets(List<Buffet> buffets) {
		this.buffets = buffets;
	}


	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != Piatto.class)
			return false;
		Piatto that = (Piatto)obj;
		return this.nome.equals(that.getNome());
	}
	
}
