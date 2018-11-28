package fr.adaming.service;

import fr.adaming.dao.IClientDAO;
import fr.adaming.modele.Client;

public class ClientServiceImpl implements IClientService {
	private IClientDAO clDAO;

	@Override
	public Client addClient(Client c) {
		return clDAO.addClient(c); 
	}

}
