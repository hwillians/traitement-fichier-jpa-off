package traitement.outils;

import java.util.ArrayList;

import traitement.entity.Additif;
import traitement.entity.Allergene;
import traitement.entity.Ingredient;

/**
 * @author helvin Créer listes des ingredients, allergenes et additifs.
 */
public class Recette {

	/**
	 * @param chaines d'ingredientes dans une ligne
	 * @return liste d'ingredients.
	 */
	public static ArrayList<Ingredient> SepIng(String chaines) {

		String[] chaine = chaines.split("[\\-,;]");
		ArrayList<Ingredient> listIng = new ArrayList<Ingredient>(chaine.length);

		for (int i = 0; i < chaine.length; i++) {

			String chaine1 = Nettoyage.Parentheses(chaine[i]);
			String chaine2 = Nettoyage.Pourcentage(chaine1);
			String chaine3 = Nettoyage.Numerotations(chaine2);

			listIng.add(new Ingredient(chaine3.trim()));
		}

		return listIng;
	}

	/**
	 * @param chaines d'allergènes dans une ligne.
	 * @return liste d'allergènes
	 */
	public static ArrayList<Allergene> SepAllergene(String chaines) {

		String[] chaine = chaines.split("[\\-,;]");
		ArrayList<Allergene> listAll = new ArrayList<Allergene>(chaine.length);

		for (int i = 0; i < chaine.length; i++) {

			String chaine1 = Nettoyage.Parentheses(chaine[i]);
			String chaine2 = Nettoyage.Pourcentage(chaine1);
			String chaine3 = Nettoyage.Numerotations(chaine2);

			listAll.add(new Allergene(chaine3.trim()));
		}

		return listAll;
	}

	/**
	 * @param chaines d'allergenes dans une ligne
	 * @return liste d'additif.
	 */
	public static ArrayList<Additif> SepAdditif(String chaines) {

		String[] chaine = chaines.split("[\\-,;]");
		ArrayList<Additif> listAdd = new ArrayList<Additif>(chaine.length);

		for (int i = 0; i < chaine.length; i++) {

			String chaine1 = Nettoyage.Parentheses(chaine[i]);
			String chaine2 = Nettoyage.Pourcentage(chaine1);
			String chaine3 = Nettoyage.Numerotations(chaine2);

			listAdd.add(new Additif(chaine3.trim()));
		}

		return listAdd;
	}

}
