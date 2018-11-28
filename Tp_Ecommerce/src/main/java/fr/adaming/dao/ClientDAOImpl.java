package fr.adaming.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.modele.Client;
import fr.adaming.modele.Produit;
@Stateless
public class ClientDAOImpl implements IClientDAO {
	@PersistenceContext(unitName = "pu_com")
	private EntityManager em;
	@Override
	public Client addClient(Client c) {
				em.persist(c);
				return c;

		}
	}

