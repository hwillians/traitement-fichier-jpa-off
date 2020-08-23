package traitement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import traitement.entity.Marque;
import traitement.entity.Produit;

public interface MarqueDao {List<Marque> extraire();
void insert (Produit p);
int update(String ancienNom,String nouveauNom);
boolean delete (Marque marque);
void insert(Produit prod, HashMap<String, Integer> marques);

}