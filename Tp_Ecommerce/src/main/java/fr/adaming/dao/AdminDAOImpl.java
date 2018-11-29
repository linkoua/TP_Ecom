package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Administrateur;

@Repository
public class AdminDAOImpl implements IAdminDAO {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Administrateur isExist(Administrateur a) {

		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Administrateur a WHERE a.mail=:pMail AND a.password=:pPass";

		Query query = s.createQuery(req);

		// Assignation des valeurs aux param
		query.setParameter("pMail", a.getMail());
		query.setParameter("pPass", a.getPassword());

		return (Administrateur) query.uniqueResult();
	}

}
