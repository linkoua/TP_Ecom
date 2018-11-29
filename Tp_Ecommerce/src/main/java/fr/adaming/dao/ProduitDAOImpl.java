package fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Produit;

@Repository
public class ProduitDAOImpl implements IProduitDAO {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit addProduit(Produit p) {
//		em.persist(p);
		return p;

	}

	@Override
	public Produit modifyProduit(Produit p) {

//		Produit pOut = em.merge(p);
//		if (pOut != null) {
//			pOut.setImage("data:image/png;base64," + Base64.encodeBase64String(pOut.getPhoto()));
//		}
		return null; // pOut;
	}

	@Override
	public int deleteProduit(Produit p) {
//		String req = "DELETE FROM Produit pr WHERE id_pr=:pId";
//
//		Query query = em.createQuery(req);
//		// Assigner les paramètres à la requète
//		query.setParameter("pId", p.getId());

		return 0; // query.executeUpdate();

	}

	@Override
	public Produit searchProduit(Produit p) {

//		Produit pOut = em.find(Produit.class, p.getId());
//		pOut.setImage("data:image/png;base64," + Base64.encodeBase64String(pOut.getPhoto()));
		return null; // pOut;
	}

	@Override
	public List<Produit> getAllProduit() {

		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Produit pr";

		Query query = s.createQuery(req);

		// FOR ELEMENT LIST set image
		List<Produit> listIn = query.list();
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
//		String req = "SELECT pr FROM Produit pr WHERE pr.categorie.id=:pCat";
//		Query query = em.createQuery(req);
//		query.setParameter("pCat", p.getCategorie().getId());
//		query.getResultList();
//		List<Produit> listIn = query.getResultList();
//		List<Produit> listOut = new ArrayList<Produit>();
//		if (listIn != null) {
//			for (Produit pr : listIn) {
//				pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
//				listOut.add(pr);
//			}
//			return listOut;
//		} else {
//			return listIn;
//		}
		return null;
	}

	@Override
	public List<Produit> getAllProduitTag(String tag) {

//		String req = "SELECT pr FROM Produit WHERE pr.descre LIKE :pDescre";
//		Query query = em.createQuery(req);
//		// Construction de la chaine de caractère
//		tag = "%" + tag + "%";
//		// Assigner les paramètres à la requète
//		query.setParameter("pDescre", tag);
//		List<Produit> listIn = query.getResultList();
//		List<Produit> listOut = new ArrayList<Produit>();
//		if (listIn != null) {
//			for (Produit pr : listIn) {
//				pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
//				listOut.add(pr);
//			}
//			return listOut;
//		} else {
//			return listIn;
//		}

		return null;
	}
}
