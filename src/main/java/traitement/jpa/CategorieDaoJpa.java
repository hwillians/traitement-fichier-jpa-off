package traitement.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Categorie;
import traitement.entity.Produit;

/**
 * @author helvin
 *
 */
public class CategorieDaoJpa {

	/**
	 * @param produits
	 * @param factory
	 */
	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory) {
		HashMap<String, Integer> myNomCategories = new HashMap<>();
		for (Produit p : produits) {
			myNomCategories.put(p.getCategorie().getNom().trim(), null);
		}
		try {

			// Recupère les catégories qui existent dans la BDD
			String query = "SELECT c FROM Categorie c";
			EntityManager em = factory.createEntityManager();
			TypedQuery<Categorie> q = em.createQuery(query, Categorie.class);
			
			// Cree un list avec les noms des càtegories de la BDD
			List<String> nomCateogries = new ArrayList<>();
			for (Categorie c : q.getResultList()) {
				nomCateogries.add(c.getNom());
			}

			for (String s : myNomCategories.keySet()) {
				// Si la categorie n'existe pas on l'ajoute dans la BDD
				if (nomCateogries.contains(s)) {
					TypedQuery<Categorie> query1 = em.createQuery("SELECT c FROM Categorie c WHERE c.nom = '" + s + "'",
							Categorie.class);
					Categorie c = query1.getResultList().get(0);
					Integer id = c.getId();
					myNomCategories.put(s, id);
				} else {
					Categorie newCat = new Categorie();
					newCat.setNom(s);
					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newCat);
					// commit
					em2.getTransaction().commit();
					// récupérer l’ID
					Integer id = newCat.getId();
					myNomCategories.put(s, id);
					// ferme la transaction
					em2.close();
				}

			}
			em.close();
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
		for (Produit p : produits) {
			for (String nomCat : myNomCategories.keySet()) {
				if (p.getCategorie().getNom().trim().equals(nomCat)) {
					p.getCategorie().setId(myNomCategories.get(nomCat));
				}
			}
		}

	}

}
