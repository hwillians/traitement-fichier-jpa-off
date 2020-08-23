package traitement.serv;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import traitement.entity.Additif;
import traitement.entity.Allergene;
import traitement.entity.Ingredient;
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

		for (Produit p : produits) {
			MarqueDaoJpa.insert(p.getMarque(), factory, em);
			CategorieDaoJpa.insert(p.getCategorie(), factory, em);
			for (Ingredient ing : p.getIngredients()) {
				IngredientDaoJpa.insert(ing,factory,em);
			}
			for (Allergene alle : p.getAllergenes()) {
				AllergeneDaoJpa.insert(alle,factory,em);
			}
			for (Additif addi : p.getAdditifs()) {
				AdditifDaoJpa.insert(addi,factory,em);
			}
			System.out.println(p.getCategorie()+"\n------------------------------" );
			ProduitDaoJpa.insert(p, factory, em);
			
		}
		
		
		// ferme la transaction
		em.close();
		factory.close();
	}
}