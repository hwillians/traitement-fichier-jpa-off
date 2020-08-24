package traitement.jpa;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Allergene;

public class AllergeneDaoJpa {

	public static void insert(Allergene alle, EntityManagerFactory factory, EntityManager em) {

		try {
			Allergene newAlle = new Allergene();
			String myAllergene = alle.getNom().toLowerCase();

			if (em != null) {
				String query = "SELECT a FROM Allergene a";
				TypedQuery<Allergene> q = em.createQuery(query, Allergene.class);
				List<String> nomAllergenes = new ArrayList<>();

				for (Allergene a : q.getResultList()) {
					nomAllergenes.add(a.getNom());
				}

				if (!nomAllergenes.contains(myAllergene)) {
					newAlle.setNom(myAllergene);

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newAlle);

					// commit
					em.getTransaction().commit();
				}
					// récupére l’ID dans la BDD
					TypedQuery<Allergene> query1 = em.createQuery(
							"SELECT a FROM Allergene a WHERE a.nom = '" + myAllergene + "'", Allergene.class);
					Allergene a = query1.getResultList().get(0);
					Integer id = a.getId();
					alle.setId(id);
				
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + "in Allergene");
		}

	}

}
