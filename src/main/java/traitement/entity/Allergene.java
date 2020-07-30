package traitement.entity;

public class Allergene extends Ingredient {
	
	private Integer id;
	private String nom;
	/**
	 * @param id
	 * @param nom
	 */
	
	@Override
	public String toString() {
		return nom;
	}
	/**
	 * @param id
	 * @param nom
	 */
	public Allergene(Integer id, String nom) {
		super(id, nom);
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
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
