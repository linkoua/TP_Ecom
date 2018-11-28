package fr.adaming.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;
import fr.adaming.modele.Produit;

public class CommandeDAOImpl implements ICommandeDAO{
	@PersistenceContext(unitName = "pu_com")
	private EntityManager em;

	@Override
	public Commande addCommande(Commande c) {
		
		em.persist(c);
		return c;
	}

	@Override
	public int deleteCommande(Commande c) {
		try {
			em.remove(c);
			return 1;
		} catch (Exception ex) {
			return 0;
		}
	}

	@Override
	public Commande modifyCommande(Commande c) {
		return em.merge(c);
	}

	@Override
	public Commande searchCommande(Commande c) {
		return em.find(Commande.class, c.getId());
	}
	

	@Override
	public List<Commande> getAllCommande() {
		String req = "SELECT co FROM Commande co";
		Query query = em.createQuery(req);
		return query.getResultList();
	}

	@Override
	public List<Commande> getAllCommandeC(Client c) {
		
		String req = "SELECT co FROM Commande co WHERE co.id=:pCID";
		Query query = em.createQuery(req);
		query.setParameter("pCID", c.getId());
		return query.getResultList();
	}
	
	
	
}
