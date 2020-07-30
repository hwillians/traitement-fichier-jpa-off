package traitement.dao;

import java.util.List;

import traitement.entity.Ingredient;
import traitement.entity.Produit;

public interface IngredientDao {
	List<Ingredient> extraire();
	void insert (Produit p);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Ingredient ing);
}
