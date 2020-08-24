package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Ingredient;
import traitement.entity.Produit;

public class IngredientDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {
		
		HashSet<String> myNomIngredeints = new HashSet<>();
		for (Produit p : produits) {
			for (Ingredient i : p.getIngredients()) {
				myNomIngredeints.add(i.getNom().trim());
			}
		}
		try {

			// Recupère les ingredients qui existent dans la BDD
			String query = "SELECT ing FROM Ingredient ing";
			TypedQuery<Ingredient> q = em.createQuery(query, Ingredient.class);
			// Cree un list avec les noms d'ingredients de la BDD
			List<String> nomIngredients = new ArrayList<>();
			for (Ingredient i : q.getResultList()) {
				nomIngredients.add(i.getNom().trim());
			}
			
			for (String s : myNomIngredeints) {
				// Si l'ingredient n'existe pas on l'ajoute dans la BDD
				if (!nomIngredients.contains(s)) {
					Ingredient newIng = new Ingredient();
					newIng.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newIng);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
			}
			// récupére l’ID dans la BDD
			for (Produit p : produits) {
				for (Ingredient ing : p.getIngredients()) {

					TypedQuery<Ingredient> query1 = em.createQuery(
							"SELECT i FROM Ingredient i WHERE i.nom = '" + ing.getNom().trim() + "'", Ingredient.class);
					if (query1.getResultList().size() > 0) {
						Ingredient i = query1.getResultList().get(0);
						Integer id = i.getId();
						ing.setId(id);
					}
				}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}

	}

}
