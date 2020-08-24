package traitement.serv;

import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import traitement.entity.Produit;
import traitement.jpa.AdditifDaoJpa;
import traitement.jpa.AllergeneDaoJpa;
import traitement.jpa.CategorieDaoJpa;
import traitement.jpa.IngredientDaoJpa;
import traitement.jpa.MarqueDaoJpa;
import traitement.jpa.ProduitDaoJpa;

/**
 * @author helvin insère les produits dans la base de données.
 */
public class ToBDD {

	public static void insertion(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {

		// ouvre transaction
		em.getTransaction().begin();

		CategorieDaoJpa.insert(produits, factory, em);
		MarqueDaoJpa.insert(produits, factory, em);
		IngredientDaoJpa.insert(produits, factory, em);
		AdditifDaoJpa.insert(produits, factory, em);
		AllergeneDaoJpa.insert(produits, factory, em);
		ProduitDaoJpa.insert(produits, factory, em);
		
		em.close();
		factory.close();

	}

}