package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.IngredientDao;
import traitement.entity.Ingredient;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class IngredientDaoJdbc implements IngredientDao {

	@Override
	public List<Ingredient> extraire() {
		Connection connection = null;
		List<Ingredient> ListeIng = new ArrayList<Ingredient>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from ingredient");

			while (resultat.next()) {
				ListeIng.add(new Ingredient(resultat.getInt("id"), resultat.getString("nom")));
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
		return ListeIng;
	}

	@Override
	public void insert(Produit p) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();

			for (Ingredient ing : p.getIngredients()) {

				canal.executeUpdate("Insert into ingredient (nom)" + "SELECT '" + ing.getNom() + "'"
						+ "WHERE not exists (select * from ingredient " + "where ingredient.nom like '" + ing.getNom()
						+ "')");

			}

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

			nb = canal.executeUpdate("update ingredient SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

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
	public boolean delete(Ingredient ing) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			nb = canal.executeUpdate("delete from ingredient where nom =" + ing.getNom() + ";") == 1;

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
