package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.AdditifDao;
import traitement.entity.Additif;
import traitement.entity.Ingredient;
import traitement.entity.Produit;
import traitement.outils.Connecter;

public class AdditifDaoJdbc implements AdditifDao {

	@Override
	public List<Additif> extraire() {
		Connection connection = null;
		List<Additif> ListeAddi = new ArrayList<Additif>();
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			ResultSet resultat = canal.executeQuery("select * from additif");

			while (resultat.next()) {
				ListeAddi.add(new Additif(resultat.getInt("id"), resultat.getString("nom")));
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
		return ListeAddi;
	}

	@Override
	public void insert(Produit p) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			
			if(p.getAdditifs() != null) {
			for(Ingredient a : p.getAdditifs()) {
			 canal.executeUpdate("Insert into additif (nom)"
					+"SELECT '"+a.getNom()+"'"
					+"WHERE not exists (select * from additif "
					+ "where additif.nom like '"+a.getNom()+"')");
			}
			}
		
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage()+ " : additif of"+ p.getNom());
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

			nb = monCanal.executeUpdate("update additif SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

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
	public boolean delete(Additif additif) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = Connecter.getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate("delete from additif where nom =" + additif.getNom() + ";") == 1;

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
