package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App {

	public static void main(String[] args) {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	//Affichage des données du livre ayant l'ID 2 : 
	Livre selectLivre = em.find(Livre.class, 2);
	if (selectLivre != null) {
		System.out.println(selectLivre.toString());
	}
	

	EntityTransaction et = em.getTransaction();
	//Ajout d'un livre en BDD : 
	et.begin();
	Livre addLivre = new Livre();
	addLivre.setTitre("Le Meilleur Des Mondes");
	addLivre.setAuteur("Aldous Huxley");
	em.persist(addLivre);
	et.commit();
	
	//Modif du titre du livre ID 5 : 
	et.begin();
	Livre updateLivre = em.find(Livre.class,5);
	if(updateLivre!=null) {
		updateLivre.setTitre("1001 Recettes de Cuisine");
	}
	et.commit();
	
	//Suppression d'un livre
	et.begin();
	Livre removeLivre = em.find(Livre.class, 13);
	if (removeLivre != null) {
		em.remove(removeLivre);
	}
	et.commit();
	
	//Extraction de la base d'un livre en fonction de l'auteur :
	TypedQuery<Livre>selectLivreByAuteur = em.createQuery("SELECT selectLivre FROM Livre selectLivre WHERE selectLivre.auteur  = 'Emile Zola'", Livre.class);
	Livre selectedLivre = selectLivreByAuteur.getResultList().get(0);
	System.out.println("Livre par auteur : " + selectedLivre.toString());
	
	//Extraction de la base d'un livre en fonction du titre :
	TypedQuery<Livre>selectLivreByTitre = em.createQuery("SELECT selectLivre FROM Livre selectLivre WHERE selectLivre.titre  = 'Le Meilleur Des Mondes'", Livre.class);
	Livre selectedLivre2 = selectLivreByTitre.getResultList().get(0);
	System.out.println("Livre par titre : " + selectedLivre2.toString());

	//Extraction de tous les livres : 
	TypedQuery<Livre>selectAllLivres = em.createQuery("SELECT selectLivre FROM Livre selectLivre", Livre.class);
	List<Livre> listeLivres = new ArrayList<>();
	listeLivres = selectAllLivres.getResultList();

	for (Livre livre : listeLivres) {
		System.out.println(livre.toString());
	}
	
	em.close();
	emf.close();
	
	}
}
