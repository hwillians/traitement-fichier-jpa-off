package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionJdbc {

	public static void main(String[] args) {

		// récupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");
		Connection connection = null;
		try {
			// enregistre le pilote
			Class.forName(db.getString("db.driver"));

			// cree la connection
			connection = DriverManager.getConnection(
					db.getString("db.url"),
					db.getString("db.user"),
					db.getString("db.pass"));

			// affiche la connection
			boolean valid = connection.isValid(500);
			if (valid) {
				System.out.println("Connection OK");
			} else {
				System.err.println("Erreur de connection");
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erreur de communication avec la base : " + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Erreur de connection" + e.getMessage());
			}
			System.out.println("Basse déconnectée !");
		}
	}
}
