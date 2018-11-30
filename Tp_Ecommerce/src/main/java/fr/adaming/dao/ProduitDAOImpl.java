package fr.adaming.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Categorie;
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
		Session s = sf.getCurrentSession();
		s.merge(p);
		return p;

	}

	@Override
	public Produit modifyProduit(Produit p) {
		Session s = sf.getCurrentSession();

		Produit prOut = (Produit) s.get(Produit.class, p.getId());
		prOut.setDesignation(p.getDesignation());
		prOut.setDescre(p.getDescre());
		prOut.setPrix(p.getPrix());
		prOut.setQuantite(p.getQuantite());
		prOut.setImage("data:image/png;base64," + Base64.encodeBase64String(prOut.getPhoto()));
		s.saveOrUpdate(prOut);
		return prOut;
	}

	@Override
	public int deleteProduit(Produit p) {
		// Ouvrir une session
				Session s = sf.getCurrentSession();

				// Requète HQL
				String req = "DELETE FROM Produit pr WHERE id_pr=:pId";

				Query query = s.createQuery(req);

				// Assigner les paramètres à la requète
				query.setParameter("pId", p.getId());

				return query.executeUpdate();

	}

	@Override
	public Produit searchProduit(Produit p) {

		Session s=sf.getCurrentSession();
		Produit prOut = (Produit) s.get(Produit.class, p.getId());
		return prOut;
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
	public List<Produit> getAllProduitTag(String tag) {
		Session s = sf.getCurrentSession();
		String req = "FROM Produit pr WHERE pr.descre LIKE :pDescre";
		Query query = s.createQuery(req);
		// Construction de la chaine de caractère
		tag = "%" + tag + "%";
		// Assigner les paramètres à la requète
		query.setParameter("pDescre", tag);
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

}
		
