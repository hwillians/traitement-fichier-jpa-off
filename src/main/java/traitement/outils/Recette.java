package traitement.outils;
import java.util.HashSet;
import java.util.Set;

import traitement.entity.Ingredient;

public class Recette {

	public static Set<Ingredient> SepIng(String chaines) {

		String[] chaine = chaines.split("[\\-,;]");
		Set<Ingredient> listIng = new HashSet<Ingredient>(chaine.length);

		for (int i = 0; i < chaine.length; i++) {
			chaine[i]=Nettoyage.Parentheses(Nettoyage.numerotations(Nettoyage.Parentheses(chaine[i])));
			
			listIng.add(new Ingredient(i, chaine[i].trim()));
		}

		return listIng;
	}

}
