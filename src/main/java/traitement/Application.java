package traitement;

import java.util.ArrayList;

import traitement.entity.Produit;
import traitement.jdbc.AdditifDaoJdbc;
import traitement.jdbc.AllergeneDaoJdbc;
import traitement.jdbc.CategorieDaoJdbc;
import traitement.jdbc.IngredientDaoJdbc;
import traitement.jdbc.MarqueDaoJdbc;
import traitement.jdbc.ProduitDaoJdbc;
import traitement.outils.TraitementFichier;

public class Application {

	public static void main(String[] args) {
		
		ArrayList <Produit> produits =  TraitementFichier.lire();
	
		
		MarqueDaoJdbc mrq = new MarqueDaoJdbc();
		CategorieDaoJdbc cat = new CategorieDaoJdbc();
		IngredientDaoJdbc ing = new IngredientDaoJdbc();
		AdditifDaoJdbc add = new AdditifDaoJdbc();
		AllergeneDaoJdbc all = new AllergeneDaoJdbc();
		ProduitDaoJdbc prod = new ProduitDaoJdbc();
	
		for (Produit p : produits) {

			mrq.insert(p);
			cat.insert(p);
			ing.insert(p);
			add.insert(p);
			all.insert(p);
			prod.insert(p);
		}
		
		System.out.println("fin !");
	}

}
