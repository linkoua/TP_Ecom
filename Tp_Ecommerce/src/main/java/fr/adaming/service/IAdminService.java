package fr.adaming.service;
import javax.ejb.Local;
import fr.adaming.modele.Administrateur;

@Local
public interface IAdminService {
	public Administrateur isExist(Administrateur a);
}
