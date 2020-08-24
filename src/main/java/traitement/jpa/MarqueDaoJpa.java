package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import traitement.entity.Marque;
import traitement.entity.Produit;

public class MarqueDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {
		HashSet<String> myNomMarques = new HashSet<>();
		for (Produit p : produits) {

			myNomMarques.add(p.getMarque().getNom().trim());

		}

		try {

			// Recupère les Marque qui existent dans la BDD
			String query = "SELECT m FROM Marque m";
			TypedQuery<Marque> q = em.createQuery(query, Marque.class);

			// Cree un list avec les noms des Marque de la BDD
			List<String> nomMarques = new ArrayList<>();
			for (Marque m : q.getResultList()) {
				nomMarques.add(m.getNom());
			}

			for (String m : myNomMarques) {
				// Si la marque n'existe pas on l'ajoute dans la BDD
				if (!nomMarques.contains(m)) {
					Marque newMrq = new Marque();
					newMrq.setNom(m);
					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newMrq);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
			}
			// récupére l’ID dans la BDD
			for (Produit p : produits) {
				TypedQuery<Marque> query1 = em.createQuery(
						"SELECT m FROM Marque m WHERE m.nom = '" + p.getMarque().getNom().trim() + "'", Marque.class);
				if (query1.getResultList().size() > 0) {
					Marque m = query1.getResultList().get(0);
					Integer id = m.getId();
					p.getMarque().setId(id);
				}

			}
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
	}
}
