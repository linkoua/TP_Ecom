package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDAO;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{
	@EJB
	private IProduitDAO prDAO;

	@Override
	public Produit addProduit(Produit p) {
		return prDAO.addProduit(p);
	}

	@Override
	public Produit modifyProduit(Produit p) {
		return prDAO.modifyProduit(p);
	}

	@Override
	public int deleteProduit(Produit p) {
		return prDAO.deleteProduit(p);
	}

	@Override
	public Produit searchProduit(Produit p) {
		return prDAO.searchProduit(p);
	}

	@Override
	public List<Produit> getAllProduit() {
	 	return prDAO.getAllProduit();
	}

	@Override
	public List<Produit> getAllProduitCat(Produit p) {
		return prDAO.getAllProduitCat(p);
	}

	@Override
	public List<Produit> getAllProduitTag(String[] tagtab) {
		List<Produit> listOut = new ArrayList<Produit>();

		for (String elem : tagtab) {

			listOut.addAll(prDAO.getAllProduitTag(elem));

		}
		return listOut;
	}

}
