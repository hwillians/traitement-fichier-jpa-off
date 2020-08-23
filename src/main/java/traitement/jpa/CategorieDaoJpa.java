package traitement.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Categorie;

public class CategorieDaoJpa {

	public static void insert(Categorie cat, EntityManagerFactory factory, EntityManager em) {

		try {
			Categorie newCat = new Categorie();

			if (em != null) {

				// Recupère les catégories qui existent dans la BDD
				String query = "SELECT c FROM Categorie c";
				TypedQuery<Categorie> q = em.createQuery(query, Categorie.class);

				// Cree un list avec les noms des càtegories de la BDD
				List<String> nomCateogries = new ArrayList<>();
				for (Categorie c : q.getResultList()) {
					nomCateogries.add(c.getNom());
				}

				// Si la categorie n'existe pas on l'ajoute dans la BDD
				if (!nomCateogries.contains(cat.getNom())) {
					newCat.setNom(cat.getNom());

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newCat);

					// commit
					em.getTransaction().commit();

					// récupére l’ID dans la BDD
					TypedQuery<Categorie> query1 = em.createQuery(
							"SELECT c FROM Categorie c WHERE c.nom = '" + cat.getNom() + "'", Categorie.class);
					Categorie c = query1.getSingleResult();
					Integer id = c.getId();
					cat.setId(id);
				}

			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
	}

}
