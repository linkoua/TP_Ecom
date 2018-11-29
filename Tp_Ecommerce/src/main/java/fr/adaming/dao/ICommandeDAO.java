package fr.adaming.dao;

import java.util.List;

import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;

public interface ICommandeDAO {
	public Commande addCommande(Commande c);

	public int deleteCommande(Commande c);

	public Commande modifyCommande(Commande c);

	public Commande searchCommande(Commande c);

	public List<Commande> getAllCommande();

	public List<Commande> getAllCommandeC(Client c);
}
