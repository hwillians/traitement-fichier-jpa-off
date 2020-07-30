/**
 * 
 */
package traitement.entity;
import java.util.Set;

/**
 * @author helvin
 *
 */
public class Produit {

	Integer id;
	String categorie;
	String marque;
	String nom;
	String grade;
	Set<Ingredient> ingredients;
	Set<Ingredient> allergenes;
	Set<Ingredient> additifs;
	// energie pour 100g
	double energie;
	// graisse100g
	double graisse;
	// sucres100g
	double sucre;
	// fibres100g
	double fibre;
	// proteines100g
	double proteine;
	// sel100g
	double sel;
	// vitA100g
	double vitA;
	// vitD100g
	double vitD;
	// vitE100g
	double vitE;
	// vitK100g
	double vitK;
	// vitC100g
	double vitC;
	// vitB1100g
	double vitB1;
	// vitB2100g
	double vitB2;
	// vitPP100g
	double vitPp;
	// vitB6100g
	double vitB6;
	// vitB9100g
	double vitB9;
	// vitB12100g
	double vitB12;
	// calcium100g
	double ca;
	// magnesium100g
	double mg;
	// iron100g
	double iron;
	// fer100g
	double fer;
	// betaCarotene100g
	double betaCaro;
	// presenceHuilePalme
	double huilePalme;

	/**
	 * @param id
	 * @param idCat
	 * @param idMrq
	 * @param nom
	 * @param grade
	 * @param ingredients
	 * @param allergenes
	 * @param additifs
	 * @param energie
	 * @param graisse
	 * @param sucre
	 * @param fibre
	 * @param proteine
	 * @param sel
	 * @param vitA
	 * @param vitD
	 * @param vitE
	 * @param vitK
	 * @param vitC
	 * @param vitB1
	 * @param vitB2
	 * @param vitPp
	 * @param vitB6
	 * @param vitB9
	 * @param vitB12
	 * @param ca
	 * @param mg
	 * @param iron
	 * @param fer
	 * @param betaCaro
	 * @param huilePalme
	 */
	public Produit(Integer id, String categorie, String marque, String nom, String grade, Set<Ingredient> ingredients,
			Set<Ingredient> allergenes, Set<Ingredient> additifs, double energie, double graisse, double sucre,
			double fibre, double proteine, double sel, double vitA, double vitD, double vitE, double vitK, double vitC,
			double vitB1, double vitB2, double vitPp, double vitB6, double vitB9, double vitB12, double ca, double mg,
			double iron, double fer, double betaCaro, double huilePalme) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.marque = marque;
		this.nom = nom;
		this.grade = grade;
		this.ingredients = ingredients;
		this.allergenes = allergenes;
		this.additifs = additifs;
		this.energie = energie;
		this.graisse = graisse;
		this.sucre = sucre;
		this.fibre = fibre;
		this.proteine = proteine;
		this.sel = sel;
		this.vitA = vitA;
		this.vitD = vitD;
		this.vitE = vitE;
		this.vitK = vitK;
		this.vitC = vitC;
		this.vitB1 = vitB1;
		this.vitB2 = vitB2;
		this.vitPp = vitPp;
		this.vitB6 = vitB6;
		this.vitB9 = vitB9;
		this.vitB12 = vitB12;
		this.ca = ca;
		this.mg = mg;
		this.iron = iron;
		this.fer = fer;
		this.betaCaro = betaCaro;
		this.huilePalme = huilePalme;
	}

	@Override
	public String toString() {
		return  nom ;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the idCat
	 */


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return the ingredients
	 */
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the allergenes
	 */
	public Set<Ingredient> getAllergenes() {
		return allergenes;
	}

	/**
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(Set<Ingredient> allergenes) {
		this.allergenes = allergenes;
	}

	/**
	 * @return the additifs
	 */
	public Set<Ingredient> getAdditifs() {
		return additifs;
	}

	/**
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(Set<Ingredient> additifs) {
		this.additifs = additifs;
	}

	/**
	 * @return the energie
	 */
	public double getEnergie() {
		return energie;
	}

	/**
	 * @param energie the energie to set
	 */
	public void setEnergie(double energie) {
		this.energie = energie;
	}

	/**
	 * @return the graisse
	 */
	public double getGraisse() {
		return graisse;
	}

	/**
	 * @param graisse the graisse to set
	 */
	public void setGraisse(double graisse) {
		this.graisse = graisse;
	}

	/**
	 * @return the sucre
	 */
	public double getSucre() {
		return sucre;
	}

	/**
	 * @param sucre the sucre to set
	 */
	public void setSucre(double sucre) {
		this.sucre = sucre;
	}

	/**
	 * @return the fibre
	 */
	public double getFibre() {
		return fibre;
	}

	/**
	 * @param fibre the fibre to set
	 */
	public void setFibre(double fibre) {
		this.fibre = fibre;
	}

	/**
	 * @return the proteine
	 */
	public double getProteine() {
		return proteine;
	}

	/**
	 * @param proteine the proteine to set
	 */
	public void setProteine(double proteine) {
		this.proteine = proteine;
	}

	/**
	 * @return the sel
	 */
	public double getSel() {
		return sel;
	}

	/**
	 * @param sel the sel to set
	 */
	public void setSel(double sel) {
		this.sel = sel;
	}

	/**
	 * @return the vitA
	 */
	public double getVitA() {
		return vitA;
	}

	/**
	 * @param vitA the vitA to set
	 */
	public void setVitA(double vitA) {
		this.vitA = vitA;
	}

	/**
	 * @return the vitD
	 */
	public double getVitD() {
		return vitD;
	}

	/**
	 * @param vitD the vitD to set
	 */
	public void setVitD(double vitD) {
		this.vitD = vitD;
	}

	/**
	 * @return the vitE
	 */
	public double getVitE() {
		return vitE;
	}

	/**
	 * @param vitE the vitE to set
	 */
	public void setVitE(double vitE) {
		this.vitE = vitE;
	}

	/**
	 * @return the vitK
	 */
	public double getVitK() {
		return vitK;
	}

	/**
	 * @param vitK the vitK to set
	 */
	public void setVitK(double vitK) {
		this.vitK = vitK;
	}

	/**
	 * @return the vitC
	 */
	public double getVitC() {
		return vitC;
	}

	/**
	 * @param vitC the vitC to set
	 */
	public void setVitC(double vitC) {
		this.vitC = vitC;
	}

	/**
	 * @return the vitB1
	 */
	public double getVitB1() {
		return vitB1;
	}

	/**
	 * @param vitB1 the vitB1 to set
	 */
	public void setVitB1(double vitB1) {
		this.vitB1 = vitB1;
	}

	/**
	 * @return the vitB2
	 */
	public double getVitB2() {
		return vitB2;
	}

	/**
	 * @param vitB2 the vitB2 to set
	 */
	public void setVitB2(double vitB2) {
		this.vitB2 = vitB2;
	}

	/**
	 * @return the vitPp
	 */
	public double getVitPp() {
		return vitPp;
	}

	/**
	 * @param vitPp the vitPp to set
	 */
	public void setVitPp(double vitPp) {
		this.vitPp = vitPp;
	}

	/**
	 * @return the vitB6
	 */
	public double getVitB6() {
		return vitB6;
	}

	/**
	 * @param vitB6 the vitB6 to set
	 */
	public void setVitB6(double vitB6) {
		this.vitB6 = vitB6;
	}

	/**
	 * @return the vitB9
	 */
	public double getVitB9() {
		return vitB9;
	}

	/**
	 * @param vitB9 the vitB9 to set
	 */
	public void setVitB9(double vitB9) {
		this.vitB9 = vitB9;
	}

	/**
	 * @return the vitB12
	 */
	public double getVitB12() {
		return vitB12;
	}

	/**
	 * @param vitB12 the vitB12 to set
	 */
	public void setVitB12(double vitB12) {
		this.vitB12 = vitB12;
	}

	/**
	 * @return the ca
	 */
	public double getCa() {
		return ca;
	}

	/**
	 * @param ca the ca to set
	 */
	public void setCa(double ca) {
		this.ca = ca;
	}

	/**
	 * @return the mg
	 */
	public double getMg() {
		return mg;
	}

	/**
	 * @param mg the mg to set
	 */
	public void setMg(double mg) {
		this.mg = mg;
	}

	/**
	 * @return the iron
	 */
	public double getIron() {
		return iron;
	}

	/**
	 * @param iron the iron to set
	 */
	public void setIron(double iron) {
		this.iron = iron;
	}

	/**
	 * @return the fer
	 */
	public double getFer() {
		return fer;
	}

	/**
	 * @param fer the fer to set
	 */
	public void setFer(double fer) {
		this.fer = fer;
	}

	/**
	 * @return the betaCaro
	 */
	public double getBetaCaro() {
		return betaCaro;
	}

	/**
	 * @param betaCaro the betaCaro to set
	 */
	public void setBetaCaro(double betaCaro) {
		this.betaCaro = betaCaro;
	}

	/**
	 * @return the huilePalme
	 */
	public double getHuilePalme() {
		return huilePalme;
	}

	/**
	 * @param huilePalme the huilePalme to set
	 */
	public void setHuilePalme(double huilePalme) {
		this.huilePalme = huilePalme;
	}

}
