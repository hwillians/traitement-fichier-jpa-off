package traitement.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Additif;
import traitement.entity.Allergene;
import traitement.entity.Ingredient;
import traitement.entity.Produit;

/**
 * @author helvin
 *
 */
public class ProduitDaoJpa {

	/**
	 * @param produits
	 * @param factory
	 */
	
	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory) {

		try {
			// Recupère les Produits qui existent dans la BDD
			String query = "SELECT p FROM Produit p";
			EntityManager em = factory.createEntityManager();
			TypedQuery<Produit> q = em.createQuery(query, Produit.class);
			// Cree un list avec les noms de produits de la BDD
			List<String> nomProduitsBdd = new ArrayList<>();
			for (Produit p : q.getResultList()) {
				nomProduitsBdd.add(p.getNom());
			}

			for (Produit prod : produits) {
				if (nomProduitsBdd.contains(prod.getNom().trim())) {
					TypedQuery<Produit> query1 = em.createQuery(
							"SELECT p FROM Produit p WHERE p.nom = '" + prod.getNom().trim() + "'", Produit.class);
					Produit p = query1.getResultList().get(0);
					Integer id = p.getId();
					prod.setId(id);

				} else {

					EntityManager em2 = factory.createEntityManager();
					em2.getTransaction().begin();

					Produit newProd = new Produit();
					newProd.setNom(prod.getNom().trim());
					newProd.setCategorie(prod.getCategorie());
					newProd.setMarque(prod.getMarque());
					newProd.setGrade(prod.getGrade());
					newProd.setEnergie(prod.getEnergie());
					newProd.setGraisse(prod.getGraisse());
					newProd.setSucre(prod.getSucre());
					newProd.setFibre(prod.getFibre());
					newProd.setProteine(prod.getProteine());
					newProd.setSel(prod.getSel());
					newProd.setVitA(prod.getVitA());
					newProd.setVitD(prod.getVitD());
					newProd.setVitE(prod.getVitE());
					newProd.setVitK(prod.getVitK());
					newProd.setVitC(prod.getVitC());
					newProd.setVitB1(prod.getVitB1());
					newProd.setVitB2(prod.getVitB2());
					newProd.setVitPp(prod.getVitPp());
					newProd.setVitB6(prod.getVitB6());
					newProd.setVitB9(prod.getVitB9());
					newProd.setVitB12(prod.getVitB12());
					newProd.setCa(prod.getCa());
					newProd.setMg(prod.getMg());
					newProd.setIron(prod.getIron());
					newProd.setFer(prod.getFer());
					newProd.setBetaCaro(prod.getBetaCaro());
					newProd.setHuilePalme(prod.getHuilePalme());

					for (Ingredient ing : prod.getIngredients()) {
						prod.addIngredient(ing);
					}
					for (Additif addi : prod.getAdditifs()) {
						prod.addAdditif(addi);
					}
					for (Allergene alle : prod.getAllergenes()) {
						prod.addAllergenes(alle);
					}

					// ajoute dans la BDD
					em2.persist(newProd);
					// commit
					em2.getTransaction().commit();
					// recupererId
					Integer id = newProd.getId();
					prod.setId(id);
					// ferme la transaction
					em2.close();

				}

			}
			em.close();
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + " In produit");

		}

	}

}
