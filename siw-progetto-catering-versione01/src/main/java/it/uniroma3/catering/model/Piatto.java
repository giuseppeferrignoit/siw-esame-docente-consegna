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
	
	@ManyToOne	
	private Buffet buffet;

	@ManyToMany(mappedBy="piatti", cascade = CascadeType.ALL)
	private List<Ingrediente> ingredienti;
	
	//-----------------------------
	
	public Piatto() {
		this.ingredienti = new ArrayList<Ingrediente>();
	}
	
	public Buffet getBuffet() {
		return buffet;
	}

	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
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
