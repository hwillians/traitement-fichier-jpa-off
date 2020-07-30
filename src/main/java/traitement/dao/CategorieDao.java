package traitement.dao;

import java.util.List;

import traitement.entity.Categorie;
import traitement.entity.Produit;

public interface CategorieDao {
	List<Categorie> extraire();
	void insert (Produit p);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Categorie categorie);
}
