package traitement.dao;

import java.util.List;

import traitement.entity.Produit;

public interface ProduitDao {
	List<Produit> extraire();
	void insert (Produit prod);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Produit prod);
}
