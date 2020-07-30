package traitement.dao;

import java.util.List;

import traitement.entity.Additif;
import traitement.entity.Produit;

public interface AdditifDao {
	List<Additif> extraire();
	void insert (Produit p);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Additif additif);

}
