package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import fr.adaming.dao.IAdminDAO;
import fr.adaming.modele.Administrateur;

@Stateful
public class AdminServiceImpl implements IAdminService {
	// Appel des m�thodes de DAO
	@EJB
	private IAdminDAO aDAO;

	@Override
	public Administrateur isExist(Administrateur a) {
		return aDAO.isExist(a);
	}

}
