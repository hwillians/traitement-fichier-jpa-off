package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Additif;
import traitement.entity.Produit;

public class AdditifDaoJpa {
	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {

		HashSet<String> myNomAdditifs = new HashSet<>();
		for (Produit p : produits) {

			for (Additif a : p.getAdditifs()) {
				myNomAdditifs.add(a.getNom().trim());
			}
		}

		try {
			// Recupère les Additifq qui existent dans la BDD
			String query = "SELECT a FROM Additif a";
			TypedQuery<Additif> q = em.createQuery(query, Additif.class);
			// Cree un list avec les noms d'ingredients de la BDD
			List<String> nomAdditifs = new ArrayList<>();
			for (Additif a : q.getResultList()) {
				nomAdditifs.add(a.getNom());
			}
			for (String s : myNomAdditifs) {
				// Si l'additif n'existe pas on l'ajoute dans la BDD
				if (!nomAdditifs.contains(s)) {
					Additif newAddi = new Additif();
					newAddi.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newAddi);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
			}
			// récupére l’ID dans la BDD
			for (Produit p : produits) {
				for (Additif addi : p.getAdditifs()) {
					TypedQuery<Additif> query1 = em.createQuery(
							"SELECT a FROM Additif a WHERE a.nom = '" + addi.getNom().trim() + "'", Additif.class);
					if (query1.getResultList().size() > 0) {
						Additif a = query1.getResultList().get(0);
						Integer id = a.getId();
						addi.setId(id);
					}
				}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + "In additif");
		}
	}
}
