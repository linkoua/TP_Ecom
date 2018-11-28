package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.modele.Client;
@Local
public interface IClientDAO {
	public Client addClient(Client c);
}
