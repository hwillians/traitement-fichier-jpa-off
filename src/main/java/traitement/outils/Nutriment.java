package traitement.outils;

public class Nutriment {

	public static double extraire(String[] chaines, int i) {

		while(!isDouble(chaines[i])&&!chaines[i].equals("")) {
			i--;
		}
		Double n = !chaines[i].equals("") ? Double.parseDouble(chaines[i]) : 0.0;
		return n;
	}

	public static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
