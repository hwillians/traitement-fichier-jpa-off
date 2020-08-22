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
				ListeIng.add(new Ingredient(resultat.getString("nom")));
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
	public void insert(Produit prod) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();

			for (Ingredient ing : prod.getIngredients()) {

				canal.executeUpdate("Insert into ingredient (nom)" + "SELECT '" + ing.getNom() + "'"
						+ "WHERE not exists (select * from ingredient " + "where ingredient.nom like '" + ing.getNom()
						+ "')");

				ResultSet resultat = canal.executeQuery("SELECT * FROM INGREDIENT WHERE NOM='" + ing.getNom() + "'");
				if (resultat.next()) {
					ing.setId(resultat.getInt("id"));
				}

				// Creation du lien entre le produit et l'ingrédient
				ResultSet res1 = canal.executeQuery(
						"SELECT * FROM COMPO_ING WHERE ID_PRO_ING=" + prod.getId() + " AND ID_ING=" + ing.getId());

				// Si le lien n'existe pas on le créé
				if (!res1.next()) {
					canal.executeUpdate("INSERT INTO COMPO_ING (ID_PRO_ING, ID_ING) VALUES (" + prod.getId() + ", "
							+ ing.getId() + ")");
				}

				res1.close();
				resultat.close();
			}
			canal.close();

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + prod.getIngredients() + " : ingredient of "
					+ prod.getNom());
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
