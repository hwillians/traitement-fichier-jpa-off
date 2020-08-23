package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import traitement.dao.MarqueDao;
import traitement.entity.Marque;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class MarqueDaoJpa implements MarqueDao {

	@Override
	public List<Marque> extraire() {

		return null;
	}

	public void insert(Produit prod) {

		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ing");
			EntityManager em = factory.createEntityManager();
			// ouvre transaction
			em.getTransaction().begin();

			
			TypedQuery<Marque> query = em.createQuery("select m from marque m where M.nom='"+prod.getMarque().getNom()+"'", Marque.class);
			
			Marque mrq = query.getResultList().get(0);
			
		
				
				// ajoute dans la BDD
				em.persist(mrq);
			
			
		
				

				

				// commit
				em.getTransaction().commit();

				// récupére l’ID dans la BDD
				prod.setId(mrq.getId());

		
			// ferme la transaction
			em.close();
			factory.close();

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		}

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Connection connection = null;
		int nb = 0;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();

			nb = canal.executeUpdate("update marque SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

			canal.close();

		} catch (Exception e) {
			System.out.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage());
			}
		}
		return nb;
	}

	@Override
	public boolean delete(Marque mrq) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			nb = canal.executeUpdate("delete from marque where nom =" + mrq.getNom() + ";") == 1;

			canal.close();
		} catch (Exception e) {

			System.out.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage());
			}
		}
		return nb;
	}

	@Override
	public void insert(Produit prod, HashMap<String, Integer> marques) {
		// TODO Auto-generated method stub
		
	}

	

}
