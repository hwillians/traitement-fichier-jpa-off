package traitement.serv;

import java.util.ArrayList;
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

	public static void insertion(ArrayList<Produit> produits, EntityManagerFactory factory) {

		// ouvre transaction
		CategorieDaoJpa.insert(produits, factory);
		MarqueDaoJpa.insert(produits, factory);
		IngredientDaoJpa.insert(produits, factory);
		AdditifDaoJpa.insert(produits, factory);
		AllergeneDaoJpa.insert(produits, factory);
		ProduitDaoJpa.insert(produits, factory);
	}

}