package it.uniroma3.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class CateringMainTest {
	
	public static void main(String[] args) {
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("catering-unit");
		EntityManager em = emf.createEntityManager();
		
		// CREA UNO CHEF
		
		Chef chef = new Chef();
		chef.setNome("Antonino");
		chef.setCognome("Cannavacciuolo");
		chef.setNazionalita("Italiana");
		
		// CREA DUE BUFFET
		
		Buffet italiano = new Buffet();
		italiano.setNome("Buffet Italiano");
		italiano.setDescrizione("Buffet con cibi italiani");
		italiano.setChef(chef);
		
		Buffet greco = new Buffet();
		greco.setNome("Buffet Greco");
		greco.setDescrizione("Buffet con cibi greci");
		greco.setChef(chef);
		
		List<Buffet> listaBuffet = new ArrayList<>();
		listaBuffet.add(italiano);
		listaBuffet.add(greco);
		
		chef.setBuffet(listaBuffet);

		
		// CREA DUE PIATTI PER BUFFET
		
		Piatto piattoIt1 = new Piatto();
		piattoIt1.setNome("Lenticchie");
		piattoIt1.setDescrizione("Pasta con lenticchie");
		piattoIt1.setBuffet(italiano);
		
		Piatto piattoIt2 = new Piatto();
		piattoIt2.setNome("Bolognese");
		piattoIt2.setDescrizione("Pasta al sugo bolognese");
		piattoIt2.setBuffet(italiano);

		
		Piatto piattoGr1 = new Piatto();
		piattoGr1.setNome("Insalata");
		piattoGr1.setDescrizione("Insalata Greca");
		piattoGr1.setBuffet(greco);
		
		Piatto piattoGr2 = new Piatto();
		piattoGr2.setNome("Moussaka");
		piattoGr2.setDescrizione("Moussaka Greca");
		piattoGr2.setBuffet(greco);

		
		//----------------------------------------------------
		
		List<Piatto> piattiItaliani = new ArrayList<>();
		piattiItaliani.add(piattoIt1);
		piattiItaliani.add(piattoIt2);
		
		List<Piatto> piattiGreci = new ArrayList<>();
		piattiItaliani.add(piattoGr1);
		piattiItaliani.add(piattoGr2);

		italiano.setPiatti(piattiItaliani);
		greco.setPiatti(piattiGreci);
		
		// CREA TRE INGREDIENTI PER PIATTO
		
		Ingrediente ingredLenticchie1 = new Ingrediente();
		ingredLenticchie1.setNome("Pasta corta");
		ingredLenticchie1.setDescrizione("Pasta corta");
		ingredLenticchie1.setOrigine("Pasta di Gragnano");
		//ingredLenticchie1.setPiatto(piattoIt1);
		
		Ingrediente ingredLenticchie2 = new Ingrediente();
		ingredLenticchie2.setNome("Pomodoro");
		ingredLenticchie2.setDescrizione("Pomodoro per sugo");
		ingredLenticchie2.setOrigine("Pomodoro di Sanmarzano");
		//ingredLenticchie2.setPiatto(piattoIt1);

		Ingrediente ingredLenticchie3 = new Ingrediente();
		ingredLenticchie3.setNome("Olio");
		ingredLenticchie3.setDescrizione("Olio di oliva");
		ingredLenticchie3.setOrigine("Olio di Puglia");
		//ingredLenticchie3.setPiatto(piattoIt1);

		Ingrediente ingredBolognese1 = new Ingrediente();
		ingredBolognese1.setNome("Pasta lunga");
		ingredBolognese1.setDescrizione("Pasta lunga");
		ingredBolognese1.setOrigine("Pasta di Gragnano");
		//ingredBolognese1.setPiatto(piattoIt2);
		
		
		Ingrediente ingredBolognese2 = new Ingrediente();
		ingredBolognese2.setNome("Pomodoro");
		ingredBolognese2.setDescrizione("Pomodoro per sugo");
		ingredBolognese2.setOrigine("Pomodoro di Sanmarzano");
		//ingredBolognese2.setPiatto(piattoIt2);

		
		Ingrediente ingredBolognese3 = new Ingrediente();
		ingredBolognese3.setNome("Carne");
		ingredBolognese3.setDescrizione("Carne macinata");
		ingredBolognese3.setOrigine("Vitello toscano");
		//ingredBolognese3.setPiatto(piattoIt2);

		
		//----------------------------------------------------
		
		Ingrediente ingredInsalata1 = new Ingrediente();
		ingredInsalata1.setNome("Lattuga");
		ingredInsalata1.setDescrizione("Lattuga fresca");
		ingredInsalata1.setOrigine("Italia");
		//ingredInsalata1.setPiatto(piattoGr1);

		Ingrediente ingredInsalata2 = new Ingrediente();
		ingredInsalata2.setNome("Formaggio");
		ingredInsalata2.setDescrizione("Formaggio Feta");
		ingredInsalata2.setOrigine("Grecia");
		//ingredInsalata2.setPiatto(piattoGr1);

		Ingrediente ingredInsalata3 = new Ingrediente();
		ingredInsalata3.setNome("Olio");
		ingredInsalata3.setDescrizione("Olio di oliva");
		ingredInsalata3.setOrigine("Olio di Puglia");
		//ingredInsalata3.setPiatto(piattoGr1);

		Ingrediente ingredMoussaka1 = new Ingrediente();
		ingredMoussaka1.setNome("Melanzane");
		ingredMoussaka1.setDescrizione("Melanzane fresche");
		ingredMoussaka1.setOrigine("Grecia");
		//ingredMoussaka1.setPiatto(piattoGr2);
		
		Ingrediente ingredMoussaka2 = new Ingrediente();
		ingredMoussaka2.setNome("Patate");
		ingredMoussaka2.setDescrizione("Patate novelle");
		ingredMoussaka2.setOrigine("Grecia");
		//ingredMoussaka2.setPiatto(piattoGr2);

		Ingrediente ingredMoussaka3 = new Ingrediente();
		ingredMoussaka3.setNome("Carne");
		ingredMoussaka3.setDescrizione("Carne di vitello");
		ingredMoussaka3.setOrigine("Argentina");
		//ingredMoussaka3.setPiatto(piattoGr2);

		//---------------------------------------------------------------------
		
		
		List<Ingrediente> ingredientiItalianiPiattoIt1 = new ArrayList<>();
		ingredientiItalianiPiattoIt1.add(ingredLenticchie1);
		ingredientiItalianiPiattoIt1.add(ingredLenticchie2);
		ingredientiItalianiPiattoIt1.add(ingredLenticchie3);
		piattoIt1.setIngredienti(ingredientiItalianiPiattoIt1);
		
		
		List<Ingrediente> ingredientiItalianiPiattoIt2 = new ArrayList<>();
		ingredientiItalianiPiattoIt2.add(ingredBolognese1);
		ingredientiItalianiPiattoIt2.add(ingredBolognese2);
		ingredientiItalianiPiattoIt2.add(ingredBolognese3);
		piattoIt2.setIngredienti(ingredientiItalianiPiattoIt2);
		
		
		List<Ingrediente> ingredientiGreciPiattoGr1 = new ArrayList<>();
		ingredientiGreciPiattoGr1.add(ingredInsalata1);
		ingredientiGreciPiattoGr1.add(ingredInsalata2);
		ingredientiGreciPiattoGr1.add(ingredInsalata3);
		piattoGr1.setIngredienti(ingredientiGreciPiattoGr1);
		
		List<Ingrediente> ingredientiGreciPiattoGr2 = new ArrayList<>();
		ingredientiGreciPiattoGr2.add(ingredMoussaka1);
		ingredientiGreciPiattoGr2.add(ingredMoussaka2);
		ingredientiGreciPiattoGr2.add(ingredMoussaka3);
		piattoGr2.setIngredienti(ingredientiGreciPiattoGr2);

		EntityTransaction tx = em.getTransaction ();

		tx.begin ();
		em.persist (chef);
		em.persist(italiano);
		em.persist(greco);
		tx.commit ();
		
		// SELECT E STAMPE
		
		// Lista buffet
		
		TypedQuery<Buffet> queryBuffet = em.createNamedQuery("findAllBuffets", Buffet.class);
		List<Buffet> listaTuttiBuffet = queryBuffet.getResultList();
		
		System.out.println("\nStampa lista buffet con Chef:");
		for (Buffet b : listaTuttiBuffet) {
			System.out.println("\nBuffet:" + b.getNome() + 
					" - Chef: " + b.getChef().getNome() + " " + b.getChef().getCognome());
		}
		System.out.println("\n");

		
		// Lista piatti per buffet
		
		
		TypedQuery<Piatto> queryPiatti = em.createNamedQuery("findAllPiatti", Piatto.class);
		List<Piatto> listaTuttiPiatti = queryPiatti.getResultList();
		
		System.out.println("\nStampa lista piatti con Buffet:");
		for (Piatto p : listaTuttiPiatti) {
			System.out.println("\nPiatto:" + p.getNome() + 
					" - Buffet: " +  p.getBuffet().getDescrizione());
		}
		System.out.println("\n");
		
		
		// Lista piatti con ingredienti
		
		TypedQuery<Piatto> queryPiattiIngredienti = 
				em.createQuery("SELECT p FROM Piatto p", Piatto.class);
		List<Piatto> listaPiattiIngredienti = queryPiattiIngredienti.getResultList();

		System.out.println("\nStampa lista piatti con Ingredienti:");
		for (Piatto p : listaPiattiIngredienti) {
			System.out.println("\nPiatto:" + p.getNome());
			for (Ingrediente i : p.getIngredienti()) {
				System.out.println("\nIngrediente:" + i.getNome());
			}
		}
		System.out.println("\n");
		
		em.close();
		emf.close();
	}
}
