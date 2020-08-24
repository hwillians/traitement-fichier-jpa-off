package traitement.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Ingredient;
import traitement.entity.Produit;

public class ProduitDaoJpa {

	public static void insert(ArrayList<Produit> produits, EntityManagerFactory factory, EntityManager em) {

		try {

			// Recupère les Produits qui existent dans la BDD
			String query = "SELECT p FROM Produit p";
			TypedQuery<Produit> q = em.createQuery(query, Produit.class);
			// Cree un list avec les noms de produits de la BDD
			List<String> nomProduits = new ArrayList<>();
			for (Produit p : q.getResultList()) {
				nomProduits.add(p.getNom());
			}

			HashSet<String> myNomProduits = new HashSet<>();
			
			

			for (Produit prod : produits) {
				if (!nomProduits.contains(prod.getNom().trim())) {
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

					// ajoute dans la BDD
					em2.persist(newProd);
					// commit
					em2.getTransaction().commit();
					// ferme la transaction
					em2.close();
				}
				

			}

			// récupére l’ID dans la BDD
			for (Produit prod : produits) {
				
			TypedQuery<Produit> query1 = em.createQuery("SELECT p FROM Produit p WHERE p.nom = '" + prod.getNom().trim() + "'",
					Produit.class);
			if (query1.getResultList().size() > 0) {
			Produit p = query1.getResultList().get(0);
			Integer id = p.getId();
			prod.setId(id);
			}
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + " In produit");

		}

	}

}
