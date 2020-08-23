package traitement.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import traitement.entity.Marque;

public class MarqueDaoJpa {

	public static void insert(Marque marque, EntityManagerFactory factory, EntityManager em) {

		try {

			Marque newMrq = new Marque();

			if (em != null) {
				String query = "SELECT m FROM Marque m";
				TypedQuery<Marque> q = em.createQuery(query, Marque.class);
				List<String> nomMarques = new ArrayList<>();

				for (Marque m : q.getResultList()) {
					nomMarques.add(m.getNom());
				}

				if (!nomMarques.contains(marque.getNom())) {
					newMrq.setNom(marque.getNom());

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newMrq);

					// commit
					em.getTransaction().commit();
				}
					// récupére l’ID dans la BDD
					TypedQuery<Marque> query1 = em.createQuery(
							"SELECT m FROM Marque m WHERE m.nom = '" + marque.getNom() + "'", Marque.class);
					Marque m = query1.getSingleResult();
					Integer id = m.getId();
					marque.setId(id);
				
			}
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
	}
}
