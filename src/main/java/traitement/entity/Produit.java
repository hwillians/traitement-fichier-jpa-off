/**
 * 
 */
package traitement.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author helvin
 *
 */
@Entity
@Table
public class Produit {

	@Id
	private Integer id;
	
	// Categorie
	@ManyToOne
	@JoinColumn(name="ID_CAT")
	private Categorie categorie;
	
	// marque
	@ManyToOne
	@JoinColumn(name="ID_MRQ")
	private Marque marque;
	@Column(name = "NOM", length = 250, nullable = false)
	private String nom;

	// Nutrition Grade Fr
	@Column(name = "GRADE", length = 250, nullable = false)
	private String grade;

	// Liste d'ingredients
	@ManyToMany
	@JoinTable(name = "COMPO_ING",
				joinColumns = @JoinColumn(name ="ID_PRO_ING"),
				inverseJoinColumns = @JoinColumn(name ="ID_ING"))
	private ArrayList<Ingredient> ingredients;
	
	// Liste d'allergènes
	@ManyToMany
	@JoinTable(name = "COMPO_ALLERG",
				joinColumns = @JoinColumn(name ="ID_PRO_AL"),
				inverseJoinColumns = @JoinColumn(name ="ID_ALLERG"))
	private ArrayList<Allergene> allergenes;
	
	// Liste d'additifs
	@ManyToMany
	@JoinTable(name = "COMPO_ADDI",
				joinColumns = @JoinColumn(name ="ID_PRO_AD"),
				inverseJoinColumns = @JoinColumn(name ="ID_ADDI"))
	private ArrayList<Additif> additifs;

	// Energie pour 100g
	@Column(name = "ENERGIE", nullable = false)
	private double energie;
	
	// Graisse pour 100g
	@Column(name = "GRAISSE", nullable = false)
	private double graisse;
	
	// Sucres pour 100g
	@Column(name = "SUCRE", nullable = false)
	private double sucre;
	
	// Fibres pour 100g
	@Column(name = "FIBRE", nullable = false)
	private double fibre;
	
	// Proteines pour 100g
	@Column(name = "PROTEINE", nullable = false)
	private double proteine;
	
	// Sel pour 100g
	@Column(name = "SEL", nullable = false)
	private double sel;
	
	// Vitamine A pour 100g
	@Column(name = "VITA", nullable = false)
	private double vitA;
	
	// Vitamine D pour 100g
	@Column(name = "VITD", nullable = false)
	private double vitD;
	
	// Vitamine tE pour 100g
	@Column(name = "VITE", nullable = false)
	private double vitE;
	
	// Vitamine K pour 100g
	@Column(name = "VITK", nullable = false)
	private double vitK;
	
	// Vitamine C pour 100g
	@Column(name = "VITC", nullable = false)
	private double vitC;
	
	// Vitamine B1 pour 100g
	@Column(name = "VITB1", nullable = false)
	private double vitB1;
	
	// Vitamine B2 pour 100g
	@Column(name = "VITB2", nullable = false)
	private double vitB2;
	
	// Vitamine PP pour 100g
	@Column(name = "VITPP", nullable = false)
	private double vitPp;
	
	// Vitamine B6 pour 100g
	@Column(name = "VITB6", nullable = false)
	private double vitB6;
	
	// Vitamine B9 pour 100g
	@Column(name = "VITB9", nullable = false)
	private double vitB9;
	
	// Vitamine B12 pour 100g
	@Column(name = "VITB12", nullable = false)
	private double vitB12;
	
	// Calcium pour 100g
	@Column(name = "CA", nullable = false)
	private double ca;
	
	// Magnesium pour 100g
	@Column(name = "MG", nullable = false)
	private double mg;
	
	// Iron pour 100g
	@Column(name = "IRON", nullable = false)
	private double iron;
	
	// Fer pour 100g
	@Column(name = "FER", nullable = false)
	private double fer;
	
	// Beta Carotene pour 100g
	@Column(name = "BETACARO", nullable = false)
	private double betaCaro;
	
	// Presence d'Huile Palme
	@Column(name = "HUILEPALME", nullable = false)
	private double huilePalme;

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
	public Produit(Categorie categorie, Marque marque, String nom, String grade, ArrayList<Ingredient> ingredients,
			ArrayList<Allergene> allergenes, ArrayList<Additif> additifs, double energie, double graisse, double sucre,
			double fibre, double proteine, double sel, double vitA, double vitD, double vitE, double vitK, double vitC,
			double vitB1, double vitB2, double vitPp, double vitB6, double vitB9, double vitB12, double ca, double mg,
			double iron, double fer, double betaCaro, double huilePalme) {
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
		return "Produit [id=" + id + ", categorie=" + categorie + ", marque=" + marque + ", nom=" + nom + ", grade="
				+ grade + ", ingredients=" + ingredients + ", allergenes=" + allergenes + ", additifs=" + additifs
				+ ", energie=" + energie + ", graisse=" + graisse + ", sucre=" + sucre + ", fibre=" + fibre
				+ ", proteine=" + proteine + ", sel=" + sel + ", vitA=" + vitA + ", vitD=" + vitD + ", vitE=" + vitE
				+ ", vitK=" + vitK + ", vitC=" + vitC + ", vitB1=" + vitB1 + ", vitB2=" + vitB2 + ", vitPp=" + vitPp
				+ ", vitB6=" + vitB6 + ", vitB9=" + vitB9 + ", vitB12=" + vitB12 + ", ca=" + ca + ", mg=" + mg
				+ ", iron=" + iron + ", fer=" + fer + ", betaCaro=" + betaCaro + ", huilePalme=" + huilePalme
				+ ", getId()=" + getId() + ", getNom()=" + getNom() + ", getCategorie()=" + getCategorie()
				+ ", getMarque()=" + getMarque() + ", getGrade()=" + getGrade() + ", getIngredients()="
				+ getIngredients() + ", getAllergenes()=" + getAllergenes() + ", getAdditifs()=" + getAdditifs()
				+ ", getEnergie()=" + getEnergie() + ", getGraisse()=" + getGraisse() + ", getSucre()=" + getSucre()
				+ ", getFibre()=" + getFibre() + ", getProteine()=" + getProteine() + ", getSel()=" + getSel()
				+ ", getVitA()=" + getVitA() + ", getVitD()=" + getVitD() + ", getVitE()=" + getVitE() + ", getVitK()="
				+ getVitK() + ", getVitC()=" + getVitC() + ", getVitB1()=" + getVitB1() + ", getVitB2()=" + getVitB2()
				+ ", getVitPp()=" + getVitPp() + ", getVitB6()=" + getVitB6() + ", getVitB9()=" + getVitB9()
				+ ", getVitB12()=" + getVitB12() + ", getCa()=" + getCa() + ", getMg()=" + getMg() + ", getIron()="
				+ getIron() + ", getFer()=" + getFer() + ", getBetaCaro()=" + getBetaCaro() + ", getHuilePalme()="
				+ getHuilePalme() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
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
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the allergenes
	 */
	public ArrayList<Allergene> getAllergenes() {
		return allergenes;
	}

	/**
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(ArrayList<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	/**
	 * @return the additifs
	 */
	public ArrayList<Additif> getAdditifs() {
		return additifs;
	}

	/**
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(ArrayList<Additif> additifs) {
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
