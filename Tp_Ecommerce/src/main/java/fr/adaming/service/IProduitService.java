package fr.adaming.service;

import java.util.List;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

public interface IProduitService {

	public Produit addProduit(Produit p, Categorie cat);

	public Produit modifyProduit(Produit p, Categorie cat);

	public int deleteProduit(Produit p);

	public Produit searchProduit(Produit p);

	public List<Produit> getAllProduit();

	public List<Produit> getAllProduitTag(String[] tag);

}
