package fr.adaming.service;

import java.io.File;
import java.util.List;

import fr.adaming.dao.ICommandeDAO;
import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;
import fr.adaming.util.CommandePDF;
import fr.adaming.util.Mail;

public class CommandeServiceImpl implements ICommandeService{
	private ICommandeDAO coDAO;
	
	@Override
	public Commande addCommande(Commande c) {
		if (coDAO.addCommande(c)!=null) {
			//Envoi du mail au client
			//Rechercher le client 
			Client cOut=new Client();
			//Generation du pdf
			CommandePDF cPDF= new CommandePDF();
			File temp=cPDF.createPDF(c);
			
			//Envoi du mail au client
			String to=cOut.getMail();
			//Definir l'adresse de l'envoyeur (en final au pire)
			String from="no-reply@totoshop.com";
			String subject= "Commande n° "+c.getId();
			String bodytext= "Bonjour "+cOut.getNom()+", \n Nous vous informons que votre commande a bien été prise en compte. \n "+
			"Veuillez trouver ci-joint le recapitulatif de votre commande \n Bonne fin de journée.\n TotoShop";
			Mail mail= new Mail (to, from, subject,bodytext, temp);
			Mail.createEmail(mail);
			temp.delete();
		}
		return coDAO.addCommande(c);
	}

	@Override
	public int deleteCommande(Commande c) {
		return coDAO.deleteCommande(c);
	}

	@Override
	public Commande modifyCommande(Commande c) {
		return coDAO.modifyCommande(c);
	}

	@Override
	public Commande searchCommande(Commande c) {
		return coDAO.searchCommande(c);
	}

	@Override
	public List<Commande> getAllCommande() {
		return coDAO.getAllCommande();
	}

	@Override
	public List<Commande> getAllCommandeC(Client c) {
		return coDAO.getAllCommandeC(c);
	}
	

}
