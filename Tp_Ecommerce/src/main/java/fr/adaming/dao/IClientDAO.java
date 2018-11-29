package fr.adaming.dao;

import fr.adaming.modele.Client;

public interface IClientDAO {
	public Client addClient(Client c);

	public Client modifyClient(Client c);

	public Client isExist(Client c);
	
}
