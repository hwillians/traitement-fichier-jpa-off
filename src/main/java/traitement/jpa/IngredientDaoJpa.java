package traitement.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Ingredient;

public class IngredientDaoJpa {

	public static void insert(Ingredient ing, EntityManagerFactory factory, EntityManager em) {

		try {
			Ingredient newIng = new Ingredient();

			if (em != null) {
				String query = "SELECT ing FROM Ingredient ing";
				TypedQuery<Ingredient> q = em.createQuery(query, Ingredient.class);
				List<String> nomIngredients = new ArrayList<>();

				for (Ingredient i : q.getResultList()) {
					nomIngredients.add(i.getNom());
				}

				if (!nomIngredients.contains(ing.getNom())) {
					newIng.setNom(ing.getNom());

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newIng);

					// commit
					em.getTransaction().commit();

					// récupére l’ID dans la BDD
					TypedQuery<Ingredient> query1 = em.createQuery(
							"SELECT i FROM Ingredient i WHERE i.nom = '" + ing.getNom() + "'", Ingredient.class);
					Ingredient i = query1.getSingleResult();
					Integer id = i.getId();
					ing.setId(id);
				}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}

	}

}
