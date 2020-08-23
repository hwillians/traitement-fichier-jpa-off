package traitement;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import traitement.entity.Marque;
import traitement.entity.Produit;
import traitement.jpa.MarqueDaoJpa;
import traitement.outils.TraitementFichier;
import traitement.serv.ToBDD;


public class Application {

	public static void main(String[] args) {

		ArrayList<Produit> produits = TraitementFichier.lire();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = factory.createEntityManager();
		
		ToBDD.insertion(produits, factory, em);
	
	
		
		

	}

}
