package traitement.outils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import traitement.entity.Produit;

public class TraitementFichier {

	public static ArrayList<Produit> lire() {

		ArrayList<Produit> produits = new ArrayList<>();
		try {
			File file = new File("/Users/helvin/Desktop/openFoodFacts.csv");
			List<String> lignes = FileUtils.readLines(file, "UTF-8");
			lignes.remove(0);
			
			for (int i = 0; i < lignes.size(); i++) {
				String ligne = lignes.get(i);
				ligne = Nettoyage.Remplacer(lignes, i);
				Produit prod = Separer.Separateur(ligne);
				produits.add(prod);
			}
			return produits;
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
