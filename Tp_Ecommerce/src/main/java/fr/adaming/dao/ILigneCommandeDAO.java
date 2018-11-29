package fr.adaming.dao;

import java.util.List;
import fr.adaming.modele.Commande;
import fr.adaming.modele.LigneCommande;
import fr.adaming.modele.Produit;

public interface ILigneCommandeDAO {
	public LigneCommande addLigneCommande(Commande c, Produit p);

	public LigneCommande modifyLigneCommande(LigneCommande lc);

	public int deleteLigneCommande(LigneCommande lc);

	public Produit searchProduit(Produit p);

	public List<LigneCommande> getAllLigneCommande();

}
