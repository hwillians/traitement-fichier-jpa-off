package traitement;

import java.util.ArrayList;
import java.util.List;

import traitement.entity.Additif;
import traitement.entity.Allergene;
import traitement.entity.Categorie;
import traitement.entity.Ingredient;
import traitement.entity.Marque;
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

		ArrayList<Produit> produits = TraitementFichier.lire();
		

//		int i = 1;
//		for (Produit p : produits) {
//			i++;
//			System.out.println(i + "._ " + p);
//			if (i == 3) {
//				return;
//			}
//		}
//
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

		List<Categorie> listCat = cat.extraire();
		System.out.println(listCat.size());

		List<Marque> listMrq = mrq.extraire();
		System.out.println(listMrq.size());

		List<Ingredient> listIng = ing.extraire();
		System.out.println(listIng.size());

		List<Additif> listAdd = add.extraire();
		System.out.println(listAdd.size());

		List<Allergene> listAll = all.extraire();
		System.out.println(listAll.size());

		List<Produit> listProd = prod.extraire();
		System.out.println(listProd.size());

	}

}
