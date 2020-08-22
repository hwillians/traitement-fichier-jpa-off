package traitement;

import java.util.ArrayList;
import traitement.entity.Produit;
import traitement.outils.TraitementFichier;
import traitement.serv.ToBDD;

public class Application {

	public static void main(String[] args) {

		ArrayList<Produit> produits = TraitementFichier.lire();

		long a = System.currentTimeMillis();

		ToBDD.insertion(produits);

		// Affichage du temps final de traitement
		long b = System.currentTimeMillis();
		System.out
				.println("Nombre d'éléments traités=" + produits.size() + " - Temps de traitement=" + (b - a) + " ms");

	}

}
