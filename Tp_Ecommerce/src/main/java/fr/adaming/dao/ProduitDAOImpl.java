package fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.modele.Produit;

public class ProduitDAOImpl implements IProduitDAO {
	@PersistenceContext(unitName = "pu_com")
	private EntityManager em;

	@Override
	public Produit addProduit(Produit p) {
			em.persist(p);
			return p;

	}

	@Override
	public Produit modifyProduit(Produit p) {

		Produit pOut = em.merge(p);
		if (pOut != null) {
			pOut.setImage("data:image/png;base64," + Base64.encodeBase64String(pOut.getPhoto()));
		}
		return pOut;
	}

	@Override
	public int deleteProduit(Produit p) {
		String req = "DELETE FROM Produit pr WHERE id_pr=:pId";

		Query query = em.createQuery(req);
		// Assigner les paramètres à la requète
		query.setParameter("pId", p.getId());

		return query.executeUpdate();

	}

	@Override
	public Produit searchProduit(Produit p) {

		Produit pOut = em.find(Produit.class, p.getId());
		pOut.setImage("data:image/png;base64," + Base64.encodeBase64String(pOut.getPhoto()));
		return pOut;
	}

	@Override
	public List<Produit> getAllProduit() {

		String req = "SELECT pr FROM Produit pr";
		Query query = em.createQuery(req);
		// FOR ELEMENT LIST set image
		List<Produit> listIn = query.getResultList();
		List<Produit> listOut = new ArrayList<Produit>();
		if (listIn != null) {
			for (Produit pr : listIn) {
				pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
				listOut.add(pr);
			}
			return listOut;
		} else {

			return listIn;
		}
	}

	@Override
	public List<Produit> getAllProduitCat(Produit p) {
		String req = "SELECT pr FROM Produit pr WHERE pr.categorie.id=:pCat";
		Query query = em.createQuery(req);
		query.setParameter("pCat", p.getCategorie().getId());
		query.getResultList();
		List<Produit> listIn = query.getResultList();
		List<Produit> listOut = new ArrayList<Produit>();
		if (listIn != null) {
			for (Produit pr : listIn) {
				pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
				listOut.add(pr);
			}
			return listOut;
		} else {
			return listIn;
		}
	}

	@Override
	public List<Produit> getAllProduitTag(String tag) {
	
		String req = "SELECT pr FROM Produit WHERE pr.descre LIKE :pDescre";
		Query query = em.createQuery(req);
		// Construction de la chaine de caractère
		tag = "%" + tag + "%";
		// Assigner les paramètres à la requète
		query.setParameter("pDescre", tag);
		List<Produit> listIn = query.getResultList();
		List<Produit> listOut = new ArrayList<Produit>();
		if (listIn != null) {
			for (Produit pr : listIn) {
				pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
				listOut.add(pr);
			}
			return listOut;
		} else {
			return listIn;
		}
	}
}
