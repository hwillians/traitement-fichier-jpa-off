package traitement.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import traitement.entity.Additif;
import traitement.entity.Produit;

/**
 * @author helvin
 *
 */
public class AdditifDaoJpa {
	/**
	 * @param produits a insérer
	 * @param factory 
	 * 
	 */
	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory) {

		HashMap<String, Integer> myNomAdditifs = new HashMap<>();
		for (Produit p : produits) {
			for (Additif a : p.getAdditifs()) {
				myNomAdditifs.put(a.getNom().trim(), null);
			}
		}

		try {
			// Recupère les Additifq qui existent dans la BDD
			String query = "SELECT a FROM Additif a";
			EntityManager em =  factory.createEntityManager(); 
			TypedQuery<Additif> q = em.createQuery(query, Additif.class);
			// Cree un list avec les noms d'ingredients de la BDD
			List<String> nomAdditifs = new ArrayList<>();
			for (Additif a : q.getResultList()) {
				nomAdditifs.add(a.getNom().trim());
			}
			for (String s : myNomAdditifs.keySet()) {
				// Si l'additif n'existe pas on l'ajoute dans la BDD
				if (nomAdditifs.contains(s)) {
					TypedQuery<Additif> query1 = em.createQuery("SELECT a FROM Additif a WHERE a.nom = '" + s + "'",
							Additif.class);

					Additif a = query1.getResultList().get(0);
					Integer id = a.getId();
					myNomAdditifs.put(s, id);
				} else {

					Additif newAddi = new Additif();
					newAddi.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newAddi);
					// commit
					em2.getTransaction().commit();
					// recuperer id
					Integer id = newAddi.getId();
					myNomAdditifs.put(s, id);
					// ferme la transaction
					em2.close();
				}
			}

			for (Produit p : produits) {
				for (Additif a : p.getAdditifs()) {
					for (String nomAddi : myNomAdditifs.keySet()) {
						if (a.getNom().trim().equals(nomAddi)) {
							a.setId(myNomAdditifs.get(nomAddi));
						}
					}
				}
			}

		em.close();
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + "In additif");
		}
	}
}
