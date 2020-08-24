package traitement.jpa;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import traitement.entity.Marque;
import traitement.entity.Produit;

public class MarqueDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {
		HashMap<String,Integer> myNomMarques = new HashMap<>();
		for (Produit p : produits) {

			myNomMarques.put(p.getMarque().getNom().trim(),null);

		}

		try {

			// Recupère les Marque qui existent dans la BDD
			String query = "SELECT m FROM Marque m";
			TypedQuery<Marque> q = em.createQuery(query, Marque.class);

			// Cree un list avec les noms des Marque de la BDD
			List<String> nomMarques = new ArrayList<>();
			for (Marque m : q.getResultList()) {
				nomMarques.add(m.getNom().trim());
			}

			for (String s : myNomMarques.keySet()) {
				// Si la marque n'existe pas on l'ajoute dans la BDD
				if (nomMarques.contains(s)) {
					TypedQuery<Marque> query1 = em.createQuery(
							"SELECT m FROM Marque m WHERE m.nom = '" + s + "'", Marque.class);
						Marque mrq = query1.getResultList().get(0);
						Integer id = mrq.getId();
						myNomMarques.put(s,id);
				}else			{
					Marque newMrq = new Marque();
					newMrq.setNom(s);
					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newMrq);
					// commit
					em2.getTransaction().commit();
					Integer id = newMrq.getId();
					myNomMarques.put(s,id);
					// ferme la transaction
					em2.close();
				}
			}
				
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}
		for(Produit p : produits) {
			for(String nomCat : myNomMarques.keySet()) {
				if(p.getMarque().getNom().trim().equals(nomCat)) {
					p.getMarque().setId(myNomMarques.get(nomCat));
				}
			}
		}
		
	}
}
