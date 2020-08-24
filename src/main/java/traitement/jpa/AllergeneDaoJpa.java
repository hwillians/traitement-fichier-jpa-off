package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Allergene;
import traitement.entity.Produit;

public class AllergeneDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {

		HashSet<String> myNomAllergenes = new HashSet<>();
		for (Produit p : produits) {
			for (Allergene a : p.getAllergenes()) {
				myNomAllergenes.add(a.getNom().trim());
			}
		}
		try {
			// Recupère les Allergenes qui existent dans la BDD
			String query = "SELECT a FROM Allergene a";
			TypedQuery<Allergene> q = em.createQuery(query, Allergene.class);
			// Cree un list avec les noms d'allergenes de la BDD
			List<String> nomAllergenes = new ArrayList<>();

			for (Allergene a : q.getResultList()) {
				nomAllergenes.add(a.getNom().trim());
			}

			for (String s : myNomAllergenes) {
				// Si l'allergène n'existe pas on l'ajoute dans la BDD
				if (!nomAllergenes.contains(s)) {

					Allergene newAlle = new Allergene();
					newAlle.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newAlle);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
			}
			// récupére l’ID dans la BDD
			for (Produit p : produits) {
				for (Allergene alle : p.getAllergenes()) {
					TypedQuery<Allergene> query1 = em.createQuery(
							"SELECT a FROM Allergene a WHERE a.nom = '" + alle.getNom().trim() + "'", Allergene.class);
					Allergene a = query1.getResultList().get(0);
					Integer id = a.getId();
					alle.setId(id);
				}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + "in Allergene");
		}

	}

}
