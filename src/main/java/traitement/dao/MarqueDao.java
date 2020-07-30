package traitement.dao;

import java.util.List;

import traitement.entity.Marque;
import traitement.entity.Produit;

public interface MarqueDao {List<Marque> extraire();
void insert (Produit p);
int update(String ancienNom,String nouveauNom);
boolean delete (Marque marque);}
