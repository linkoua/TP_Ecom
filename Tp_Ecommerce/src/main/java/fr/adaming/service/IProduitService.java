package fr.adaming.service;

import java.util.List;
import javax.ejb.Local;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

@Local
public interface IProduitService {

	public Produit addProduit(Produit p);

	public Produit modifyProduit(Produit p);

	public int deleteProduit(Produit p);

	public Produit searchProduit(Produit p);

	public List<Produit> getAllProduit();

	public List<Produit> getAllProduitCat(Produit p);

	public List<Produit> getAllProduitTag(String[] tag);

}
