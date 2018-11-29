package fr.adaming.service;

import fr.adaming.modele.Client;

public interface IClientService {
	public Client addClient(Client c);

	public Client modifyClient(Client c);

	public Client isExist(Client c);
}
