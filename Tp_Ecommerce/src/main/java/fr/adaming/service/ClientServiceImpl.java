package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDAO;
import fr.adaming.modele.Client;

@Service("clService")
@Transactional
public class ClientServiceImpl implements IClientService {
	// Transformation de UML en JAVA
	@Autowired
	private IClientDAO clDAO;

	@Override
	public Client addClient(Client c) {
		return clDAO.addClient(c);
	}

	@Override
	public Client modifyClient(Client c) {

		return clDAO.modifyClient(c);
	}

	@Override
	public Client isExist(Client c) {
		return clDAO.isExist(c);
	}

}
