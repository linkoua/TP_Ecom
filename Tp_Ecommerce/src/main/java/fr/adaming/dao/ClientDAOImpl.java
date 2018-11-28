package fr.adaming.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.modele.Client;
import fr.adaming.modele.Produit;

public class ClientDAOImpl implements IClientDAO {
	@PersistenceContext(unitName = "pu_com")
	private EntityManager em;
	@Override
	public Client addClient(Client c) {
				em.persist(c);
				return c;

		}
	}

