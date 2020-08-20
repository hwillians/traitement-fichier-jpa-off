package traitement.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import traitement.dao.AllergeneDao;
import traitement.entity.Allergene;
import traitement.entity.Ingredient;
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
				ListeAlle.add(new Allergene(resultat.getInt("id"), resultat.getString("nom")));
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
	public void insert(Produit p) {
		Connection connection = null;
		try {
			connection = Connecter.getConnection();
			Statement canal = connection.createStatement();
			
			if(p.getAllergenes() != null) {
			for(Ingredient a : p.getAllergenes()) {
			canal.executeUpdate("Insert into allergene (nom)"
					+"SELECT '"+a.getNom()+"'"
					+"WHERE not exists (select * from allergene "
					+ "where allergene.nom like '"+a.getNom()+"')");
			}
			}
			
		} catch (Exception e) {
			System.err.println("Erreur d'éxecution : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage()+ " : allergene of"+ p.getNom());
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

			nb = monCanal.executeUpdate("update allergene SET nom = '" + nouveauNom + "' where nom='" + ancienNom + "';");

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
