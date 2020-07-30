package traitement.dao;

import java.util.List;

import traitement.entity.Allergene;
import traitement.entity.Produit;

public interface AllergeneDao {
	List<Allergene> extraire();
	void insert (Produit p);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Allergene allergene);
}
