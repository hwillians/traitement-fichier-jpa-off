package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Categorie;
import traitement.entity.Produit;

public class CategorieDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {
		HashSet<String> myNomCategories = new HashSet<>();
		for (Produit p : produits) {
			myNomCategories.add(p.getCategorie().getNom().trim());
		}
		try {

			// Recupère les catégories qui existent dans la BDD
			String query = "SELECT c FROM Categorie c";
			TypedQuery<Categorie> q = em.createQuery(query, Categorie.class);

			// Cree un list avec les noms des càtegories de la BDD
			List<String> nomCateogries = new ArrayList<>();
			for (Categorie c : q.getResultList()) {
				nomCateogries.add(c.getNom());
			}

			for (String s : myNomCategories) {
				// Si la categorie n'existe pas on l'ajoute dans la BDD
				if (!nomCateogries.contains(s)) {
					Categorie newCat = new Categorie();
					newCat.setNom(s);
					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newCat);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
			}
			// récupére l’ID dans la BDD
			for (Produit p : produits) {
				TypedQuery<Categorie> query1 = em.createQuery(
						"SELECT c FROM Categorie c WHERE c.nom = '" + p.getCategorie().getNom().trim() + "'", Categorie.class);
				Categorie c = query1.getResultList().get(0);
				Integer id = c.getId();
				p.getCategorie().setId(id);
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
	}

}
