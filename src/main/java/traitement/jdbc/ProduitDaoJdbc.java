package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.ProduitDao;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class ProduitDaoJdbc implements ProduitDao {

	@Override
	public List<Produit> extraire() {
		Connection connection = null;
		List<Produit> ListeProd = new ArrayList<Produit>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from produit");

			while (resultat.next()) {
				ListeProd.add(new Produit(null, null, resultat.getString("nom"), resultat.getString("grade"), null,
						null, null, resultat.getDouble("energie"), resultat.getDouble("graisse"),
						resultat.getDouble("sucre"), resultat.getDouble("fibre"), resultat.getDouble("proteine"),
						resultat.getDouble("sel"), resultat.getDouble("vitA"), resultat.getDouble("vitD"),
						resultat.getDouble("vitE"), resultat.getDouble("vitK"), resultat.getDouble("vitC"),
						resultat.getDouble("vitB1"), resultat.getDouble("vitB2"), resultat.getDouble("vitPP"),
						resultat.getDouble("vitB6"), resultat.getDouble("vitB9"), resultat.getDouble("vitB12"),
						resultat.getDouble("ca"), resultat.getDouble("mg"), resultat.getDouble("iron"),
						resultat.getDouble("fer"), resultat.getDouble("betaCaro"), resultat.getDouble("huilePalme")));
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
		return ListeProd;
	}

	@Override
	public void insert(Produit prod) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();

			canal.executeUpdate("INSERT INTO produit (nom, id_cat, id_mrq, grade, energie, graisse, sucre,"
					+ "fibre, proteine, sel, vitA, vitD, vitE, vitK, vitC, vitB1, vitB2, vitPP, "
					+ "vitB6, vitB9, vitB12, ca, mg, iron, fer, betaCaro, huilePalme) " + "select'" + prod.getNom()
					+ "', " + prod.getCategorie().getId() + ", " + prod.getMarque().getId() + ", '" + prod.getGrade()
					+ "', " + prod.getEnergie() + ", " + prod.getGraisse() + ", " + prod.getSucre() + ", "
					+ prod.getFibre() + ", " + prod.getProteine() + ", " + prod.getSel() + ", " + prod.getVitA() + ", "
					+ prod.getVitD() + ", " + prod.getVitE() + ", " + prod.getVitK() + ", " + prod.getVitC() + ", "
					+ prod.getVitB1() + ", " + prod.getVitB2() + ", " + prod.getVitPp() + ", " + prod.getVitB6() + ", "
					+ prod.getVitB9() + ", " + prod.getVitB12() + ", " + prod.getCa() + ", " + prod.getMg() + ", "
					+ prod.getIron() + ", " + prod.getFer() + ", " + prod.getBetaCaro() + ", " + prod.getHuilePalme()
					+ "WHERE NOT EXISTS ( SELECT * FROM produit where produit.nom = '" + prod.getNom() + "');");

			ResultSet resultat = canal.executeQuery("SELECT * FROM PRODUIT WHERE NOM='" + prod.getNom() + "'");
			if (resultat.next()) {
				prod.setId(resultat.getInt("id"));
			}
			resultat.close();

		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage() + " " + prod.getNom());
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
			Statement monCanal = connection.createStatement();

			nb = monCanal.executeUpdate("update produit SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

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
	public boolean delete(Produit prod) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate("delete from produit where nom =" + prod.getNom() + ";") == 1;

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
