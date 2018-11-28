package fr.adaming.dao;

import javax.ejb.Local;
import fr.adaming.modele.Administrateur;

@Local
public interface IAdminDAO {
	public Administrateur isExist(Administrateur a);

}
