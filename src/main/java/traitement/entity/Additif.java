package traitement.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author helvin
 *
 */
@Entity
@Table(name = "ADDITIF")
public class Additif {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "NOM", length = 250, nullable = false)
	private String nom;
	@ManyToMany(mappedBy = "additifs",cascade = CascadeType.ALL)
	private Set<Produit> produits;

	/**
	 * 
	 */
	public Additif() {

	}
	
	/**
	 * @param nom nom de l'additif
	 * 
	 */
	public Additif(String nom) {
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

	/**
	 * @return the produits
	 */
	public Set<Produit> getProduits() {
		return produits;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}


}
