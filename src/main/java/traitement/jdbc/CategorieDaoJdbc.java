package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.CategorieDao;
import traitement.entity.Categorie;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class CategorieDaoJdbc implements CategorieDao {

	@Override
	public List<Categorie> extraire() {
		Connection connection = null;
		List<Categorie> ListeCat = new ArrayList<Categorie>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from categorie");

			while (resultat.next()) {
				ListeCat.add(new Categorie(resultat.getInt("id"), resultat.getString("nom")));
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
		return ListeCat;
	}

	@Override
	public void insert(Produit p) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			canal.executeUpdate("Insert into categorie (nom)"
					+"SELECT '"+p.getCategorie()+"'"
					+"WHERE not exists (select * from categorie "
					+ "where categorie.nom like '"+p.getCategorie()+"')");
			
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage());
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

			nb = canal.executeUpdate("update categorie SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

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
	public boolean delete(Categorie categorie) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			nb = canal.executeUpdate("delete from categorie where nom =" + categorie.getNom() + ";") == 1;

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
