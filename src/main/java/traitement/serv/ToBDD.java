package traitement.serv;

import java.util.ArrayList;
import traitement.entity.Produit;
import traitement.jdbc.AdditifDaoJdbc;
import traitement.jdbc.AllergeneDaoJdbc;
import traitement.jdbc.CategorieDaoJdbc;
import traitement.jdbc.IngredientDaoJdbc;

import traitement.jdbc.MarqueDaoJpa;
import traitement.jdbc.ProduitDaoJdbc;

/**
 * @author helvin insère les produits dans la base de données.
 */
public class ToBDD {

	public static void insertion(ArrayList<Produit> produits) {
		MarqueDaoJpa mrq = new MarqueDaoJpa();
//		CategorieDaoJdbc cat = new CategorieDaoJdbc();
//		IngredientDaoJdbc ing = new IngredientDaoJdbc();
//		AdditifDaoJdbc add = new AdditifDaoJdbc();
//		AllergeneDaoJdbc all = new AllergeneDaoJdbc();
//		ProduitDaoJdbc prod = new ProduitDaoJdbc();

		for (Produit p : produits) {
			mrq.insert(p);
//			cat.insert(p);
//			prod.insert(p);
//			ing.insert(p);
//			add.insert(p);
//			all.insert(p);

		}
	}
}