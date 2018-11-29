package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDAO;
import fr.adaming.modele.Administrateur;

@Service("adService")
@Transactional
public class AdminServiceImpl implements IAdminService {
	
	// Transformation de UML en JAVA
	@Autowired
	private IAdminDAO aDAO;

	@Override
	public Administrateur isExist(Administrateur a) {
		return aDAO.isExist(a);
	}

}
