package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import traitement.dao.AllergeneDao;
import traitement.entity.Allergene;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class AllergeneDaoJdbc implements AllergeneDao {

	@Override
	public List<Allergene> extraire() {
		Connection connection = null;
		List<Allergene> ListeAlle = new ArrayList<Allergene>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from allergene");

			while (resultat.next()) {
				ListeAlle.add(new Allergene(resultat.getString("nom")));
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
		return ListeAlle;
	}

	@Override
	public void insert(Produit prod) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();

			if (prod.getAllergenes() != null) {
				for (Allergene a : prod.getAllergenes()) {
					canal.executeUpdate("Insert into allergene (nom)" + "SELECT '" + a.getNom() + "'"
							+ "WHERE not exists (select * from allergene " + "where allergene.nom like '" + a.getNom()
							+ "')");
					ResultSet resultat = canal.executeQuery("SELECT * FROM ALLERGENE WHERE NOM='" + a.getNom() + "'");
					if (resultat.next()) {
						a.setId(resultat.getInt("id"));
					}

					// Creation du lien entre le produit et l'ingrédient
					ResultSet res1 = canal.executeQuery("SELECT * FROM COMPO_ALLERG WHERE ID_PRO_AL=" + prod.getId()
							+ " AND ID_ALLERG=" + a.getId());

					// Si le lien n'existe pas on le créé
					if (!res1.next()) {
						canal.executeUpdate("INSERT INTO COMPO_ALLERG (ID_PRO_AL, ID_ALLERG) VALUES (" + prod.getId()
								+ ", " + a.getId() + ")");
					}

					res1.close();
					resultat.close();
				}
				canal.close();
			}

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(
						"Probleme de connection close : " + e.getMessage() + " : allergene of" + prod.getNom());
			}
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Connection connection = null;
		int nb = 0;
		try {
			connection = Connecter.getConnection();
			Statement monCanal = connection.createStatement();

			nb = monCanal
					.executeUpdate("update allergene SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

			monCanal.close();

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
	public boolean delete(Allergene allergene) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate("delete from allergene where nom =" + allergene.getNom() + ";") == 1;

			monCanal.close();
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
