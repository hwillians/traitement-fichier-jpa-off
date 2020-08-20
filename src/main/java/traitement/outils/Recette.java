package traitement.outils;
import java.util.HashSet;
import java.util.Set;

import traitement.entity.Ingredient;

public class Recette {

	public static Set<Ingredient> SepIng(String chaines) {

		String[] chaine = chaines.split("[\\-,;]");
		Set<Ingredient> listIng = new HashSet<Ingredient>(chaine.length);

		for (int i = 0; i < chaine.length; i++) {
			
			String chaine1 = Nettoyage.Parentheses(chaine[i]);
			String chaine2 = Nettoyage.Pourcentage(chaine1);
			String chaine3 = Nettoyage.Numerotations(chaine2);
			
			
			listIng.add(new Ingredient(i, chaine3.trim()));
		}

		return listIng;
	}

}
