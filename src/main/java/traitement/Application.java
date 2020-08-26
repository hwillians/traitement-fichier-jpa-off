package traitement;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import traitement.entity.Produit;

import traitement.outils.TraitementFichier;
import traitement.serv.ToBDD;


public class Application {

	public static void main(String[] args) {

		ArrayList<Produit> produits = TraitementFichier.lire();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = factory.createEntityManager();
		
		ToBDD.insertion(produits, factory);
		
		em.close();
		factory.close();


	}

}
