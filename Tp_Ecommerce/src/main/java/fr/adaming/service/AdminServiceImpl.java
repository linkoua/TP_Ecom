package fr.adaming.service;

import fr.adaming.dao.IAdminDAO;
import fr.adaming.modele.Administrateur;

public class AdminServiceImpl implements IAdminService {
	// Appel des méthodes de DAO
	private IAdminDAO aDAO;

	@Override
	public Administrateur isExist(Administrateur a) {
		return aDAO.isExist(a);
	}

}
