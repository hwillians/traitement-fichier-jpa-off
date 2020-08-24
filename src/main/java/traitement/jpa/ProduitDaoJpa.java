package traitement.jpa;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import traitement.entity.Produit;


public class ProduitDaoJpa {

	public static void insert(Produit prod, EntityManagerFactory factory, EntityManager em) {
		
		try {
			Produit newProd = new Produit();
			String myProd = prod.getNom().toLowerCase();

			if (em != null) {
				String query = "SELECT p FROM Produit p";
				TypedQuery<Produit> q = em.createQuery(query, Produit.class);
				List<String> nomProduits = new ArrayList<>();

				for (Produit p : q.getResultList()) {
					nomProduits.add(p.getNom());
				}

				if (!nomProduits.contains(myProd)) {
					newProd.setNom(myProd);
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

					// ouvre transaction
					em.getTransaction().begin();

					// ajoute dans la BDD
					em.persist(newProd);

					// commit
					em.getTransaction().commit();
				}
					// récupére l’ID dans la BDD
					TypedQuery<Produit> query1 = em.createQuery(
							"SELECT p FROM Produit p WHERE p.nom = '" + myProd + "'", Produit.class);
					Produit p = query1.getResultList().get(0);
					Integer id = p.getId();
					prod.setId(id);
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + " In produit");

		}

	}

}
