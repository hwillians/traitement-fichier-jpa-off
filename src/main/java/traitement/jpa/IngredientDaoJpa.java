package traitement.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Ingredient;
import traitement.entity.Produit;

public class IngredientDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {

		HashMap<String, Integer> mesIngredients = new HashMap<>();
		for (Produit p : produits) {
			for (Ingredient i : p.getIngredients()) {
				mesIngredients.put(i.getNom().trim(), null);
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

			for (String s : mesIngredients.keySet()) {
				// Si l'ingredient n'existe pas on l'ajoute dans la BDD
				if (nomIngredients.contains(s)) {
					TypedQuery<Ingredient> query1 = em
							.createQuery("SELECT i FROM Ingredient i WHERE i.nom = '" + s + "'", Ingredient.class);

					Ingredient i = query1.getResultList().get(0);
					Integer id = i.getId();
					mesIngredients.put(s,id);
				} else {
					Ingredient newIng = new Ingredient();
					newIng.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newIng);
					// commit
					em2.getTransaction().commit();
					// recupere l'ID
					Integer id = newIng.getId();
					mesIngredients.put(s, id);
					// ferme la transaction
					em2.close();
				}
			}

			for (Produit p : produits) {
				for (Ingredient i : p.getIngredients()) {
					for (String nomIng : mesIngredients.keySet()) {
						if (i.getNom().trim().equals(nomIng)) {
							i.setId(mesIngredients.get(nomIng));
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}

	}

}
