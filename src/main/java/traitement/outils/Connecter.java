package traitement.outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author helvin
 * crée une connection avec las base de données.
 */
public class Connecter {
	public static Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			
			Class.forName(db.getString("db.driver"));

			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}