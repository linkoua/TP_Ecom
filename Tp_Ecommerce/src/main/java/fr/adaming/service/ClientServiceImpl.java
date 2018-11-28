package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDAO;
import fr.adaming.dao.IProduitDAO;
import fr.adaming.modele.Client;

@Stateful
public class ClientServiceImpl implements IClientService {
	@EJB
	private IClientDAO clDAO;

	@Override
	public Client addClient(Client c) {
		return clDAO.addClient(c); 
	}

}
