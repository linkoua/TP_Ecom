package fr.adaming.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Client;
import fr.adaming.modele.Produit;

@Repository
public class ClientDAOImpl implements IClientDAO {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Client addClient(Client c) {
		Session s = sf.getCurrentSession();
		s.persist(c);
		return c;
	}

	@Override
	public Client modifyClient(Client c) {
		Session s = sf.getCurrentSession();

		Client clOut = (Client) s.get(Client.class, c.getId());
		clOut.setNom(c.getNom());
		clOut.setMail(c.getMail());
		clOut.setAdresse(c.getAdresse());
		clOut.setTel(c.getTel());
		clOut.setPassword(c.getPassword());
		s.saveOrUpdate(clOut);
		return clOut;
	}

	@Override
	public Client isExist(Client c) {
		Session s = sf.getCurrentSession();
		// Requete HQL
		String req="SELECT Client c WHERE c.mail=:pMail AND c.password=:pPässword";
		Query query = s.createQuery(req);

		// Assignation des valeurs aux param
		query.setParameter("pMail", c.getMail());
		query.setParameter("pPassword", c.getPassword());
		
		return (Client) query.uniqueResult();
	}
}
