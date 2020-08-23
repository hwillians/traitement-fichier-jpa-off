package traitement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Application {

	public static void main(String[] args) {

	//	ArrayList<Produit> produits = TraitementFichier.lire();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_essai");
		
		


	}

}
