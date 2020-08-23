package traitement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MARQUE")
public class Marque {
	@Id
	private Integer id;
	@Column(name = "NOM", length = 250, nullable = false)
	private String nom;

	/**
	 * @param id
	 * @param nom
	 */
	public Marque(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Marque : " + nom;
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
