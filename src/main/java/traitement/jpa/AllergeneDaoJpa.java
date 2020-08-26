package traitement.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import traitement.entity.Allergene;
import traitement.entity.Produit;

/**
 * @author helvin
 *
 */
public class AllergeneDaoJpa {

	/**
	 * @param produits à insérer
	 * @param factory manager de l'aplication
	 */
	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory) {

		HashMap<String, Integer> mesAllergenes = new HashMap<>();
		for (Produit p : produits) {
			for (Allergene a : p.getAllergenes()) {
				mesAllergenes.put(a.getNom().trim(), null);
			}
		}
		try {
			// Recupère les Allergenes qui existent dans la BDD
			String query = "SELECT a FROM Allergene a";
			EntityManager em = factory.createEntityManager();
			TypedQuery<Allergene> q = em.createQuery(query, Allergene.class);
			// Cree un list avec les noms d'allergenes de la BDD
			List<String> nomAllergenes = new ArrayList<>();

			for (Allergene a : q.getResultList()) {
				nomAllergenes.add(a.getNom().trim());
			}

			for (String s : mesAllergenes.keySet()) {
				// Si l'allergène n'existe pas on l'ajoute dans la BDD
				if (nomAllergenes.contains(s)) {

					TypedQuery<Allergene> query1 = em.createQuery("SELECT a FROM Allergene a WHERE a.nom = '" + s + "'",
							Allergene.class);
					Allergene a = query1.getResultList().get(0);
					Integer id = a.getId();
					mesAllergenes.put(s, id);
				} else {

					Allergene newAlle = new Allergene();
					newAlle.setNom(s);

					// ouvre transaction
					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();
					// ajoute dans la BDD
					em2.persist(newAlle);
					// commit
					em2.getTransaction().commit();
					// recuperer id
					Integer id = newAlle.getId();
					mesAllergenes.put(s, id);
					// ferme la transaction
					em2.close();
				}
			}
			for (Produit p : produits) {
				for (Allergene a : p.getAllergenes()) {
					for (String nomAlle : mesAllergenes.keySet()) {
						if (a.getNom().trim().equals(nomAlle)) {
							a.setId(mesAllergenes.get(nomAlle));
						}
					}
				}
			}
			em.close();
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + "in Allergene");
		}

	}

}
