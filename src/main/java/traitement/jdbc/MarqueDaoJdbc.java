package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.MarqueDao;
import traitement.entity.Marque;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class MarqueDaoJdbc implements MarqueDao {

	@Override
	public List<Marque> extraire() {
		Connection connection = null;
		List<Marque> ListeMrq = new ArrayList<Marque>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from Marque");

			while (resultat.next()) {
				ListeMrq.add(new Marque(resultat.getInt("id"), resultat.getString("nom")));
			}
			resultat.close();
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
		return ListeMrq;
	}

	@Override
	public void insert(Produit p) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			canal.executeUpdate("Insert into marque (nom) "
					+ "SELECT '" + p.getMarque() + "'"
					+ "WHERE not exists (select * from marque "
					+ "where marque.nom like '" 
					+ p.getMarque() + "')");

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage()+ " : marque of"+ p.getNom());
			}
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

}
