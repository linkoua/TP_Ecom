package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;

@Local
public interface ICommandeService {
	public Commande addCommande(Commande c);
	public int deleteCommande(Commande c);
	public Commande modifyCommande(Commande c);
	public Commande searchCommande(Commande c);
	public List<Commande> getAllCommande();
	public List<Commande> getAllCommandeC(Client c);

}
