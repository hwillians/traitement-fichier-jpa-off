package traitement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALLERGENE")
public class Allergene {

	@Id
	private Integer id;
	@Column(name = "NOM", length = 50, nullable = false)
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
	public Allergene(String nom) {
		this.nom = nom;
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
