package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.modele.Administrateur;

@Stateless
public class AdminDAOImpl implements IAdminDAO {
	@PersistenceContext(unitName="pu_com")
	private EntityManager em;

	@Override
	public Administrateur isExist(Administrateur a) {
		//Requete JPQL 
		String req="Select a FROM Administrateur a WHERE a.mail=:pMail AND a.password=:pPass";
		Query query=em.createQuery(req);
		//Assignation des valeurs aux param
		query.setParameter("pMail", a.getMail());
		query.setParameter("pPass", a.getPassword());
		return (Administrateur) query.getSingleResult(); 
	}

}
