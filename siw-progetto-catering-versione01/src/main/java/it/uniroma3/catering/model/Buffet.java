package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
@ NamedQuery (name =  "findAllBuffets", query = "SELECT b FROM Buffet b")
public class Buffet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@ManyToOne
	private Chef chef;
	
	@OneToMany(mappedBy="buffet", cascade = CascadeType.ALL)
	private List<Piatto> piatti;
	
	//----------Getter & Setter ----------------
	
	public Buffet() {
		this.piatti = new ArrayList<Piatto>();
	}
	
	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto piatto) {
		this.getPiatti().add(piatto);
		piatto.setBuffet(this);
	}
	

	public void removePiatto(Piatto piatto) {
		this.getPiatti().remove(piatto);
		piatto.setBuffet(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if(obj.getClass() != Buffet.class)
			return false;
		Buffet that = (Buffet)obj;
		return this.nome.equals(that.getNome());
	}
	
}