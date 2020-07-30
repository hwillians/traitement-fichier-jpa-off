package traitement.outils;

import java.util.List;

public class Nettoyage {

	public static String Remplacer(List<String> lignes, int i) {

		return lignes.get(i).replaceAll("_", " ").replaceAll("\\|’", "l\'").replaceAll("/' ", "/'")
				.replaceAll("\\|ac", "lac")
				.replaceAll("acty\\|ate", "actylate")
				.replaceAll("\\|as", "las")
				.replaceAll("\\|ant", "ant").replaceAll("\\|%", "%").replaceAll(",,", ",").replaceAll("\\*", "")
				.replaceAll("\\\\'","\\'" ).replaceAll("\\'", "\\\\'").replaceAll("  ", " ");
	}
}
