package fr.adaming.dao;

import java.util.List;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

public interface IProduitDAO {

	public Produit addProduit(Produit p);

	public Produit modifyProduit(Produit p);

	public int deleteProduit(Produit p);

	public Produit searchProduit(Produit p);

	public List<Produit> getAllProduit();

	public List<Produit> getAllProduitTag(String tag);

}
