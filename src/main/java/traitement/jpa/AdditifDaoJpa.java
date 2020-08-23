package traitement.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Additif;

public class AdditifDaoJpa {
	public static void insert(Additif addi, EntityManagerFactory factory, EntityManager em) {

		try {
			Additif newAddi = new Additif();

			if (em != null) {
				String query = "SELECT a FROM Additif a";
				TypedQuery<Additif> q = em.createQuery(query, Additif.class);
				List<String> nomAdditifs = new ArrayList<>();

				for (Additif a : q.getResultList()) {
					nomAdditifs.add(a.getNom());
				}

				if (!nomAdditifs.contains(addi.getNom())) {
					newAddi.setNom(addi.getNom());

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newAddi);

					// commit
					em.getTransaction().commit();

					// récupére l’ID dans la BDD
					TypedQuery<Additif> query1 = em.createQuery(
							"SELECT a FROM Additif a WHERE a.nom = '" + addi.getNom() + "'", Additif.class);
					Additif a = query1.getSingleResult();
					Integer id = a.getId();
					addi.setId(id);
				}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}

	}

}
