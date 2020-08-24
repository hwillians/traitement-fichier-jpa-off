package traitement.outils;

import java.util.List;

/**
 * @author helvin
 * suppresion des élements parasites.
 */
/**
 * @author helvin
 *
 */
/**
 * @author helvin
 *
 */
/**
 * @author helvin
 *
 */
public class Nettoyage {

	/**
	 * suppresion des charactères parasites et des erreurs de frappe.
	 * 
	 * @param lignes
	 * @return chaine après traitement.
	 */
	public static String Remplacer(List<String> lignes, int i) {

		return lignes.get(i).replaceAll("_", " ").replaceAll("\\|’", "l\'").replaceAll("/' ", "/'")
				.replaceAll("\\|ac", "lac").replaceAll("acty\\|ate", "actylate").replaceAll("\\|as", "las")
				.replaceAll("\\|ant", "ant").replaceAll("\\\\is\\\\", "is").replaceAll("\\|%", "%")
				.replaceAll(",,", ",").replaceAll("\\*", "").replaceAll("\\\\'", "\\'").replaceAll("  ", " ")
				.replace("'", " ").toLowerCase();
	}

	/**
	 * Pour supprimer des Parentheses
	 * 
	 * @param chaine
	 * @return chaine après traitement.
	 */
	public static String Parentheses(String chaine) {
		return chaine.replaceAll("\\(.*?\\)", "").trim();
	}

	/**
	 * Pour supprimer des pourcentages
	 * 
	 * @param chaine
	 * @return
	 */
	public static String Pourcentage(String chaine) {
		return chaine.replaceAll("[0-9]*\\s*%", "").trim();
	}

	/**
	 * Suppression des numérotations
	 * 
	 * @param chaine
	 * @return
	 */
	public static String Numerotations(String chaine) {
		return chaine.replaceAll("[0-9]*\\.*", "").trim();
	}

}
