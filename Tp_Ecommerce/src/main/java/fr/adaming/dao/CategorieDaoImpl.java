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

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Categorie> getAllCategories() {

		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Categorie ca";

		Query query = s.createQuery(req);

		// FOR ELEMENT LIST set image
		List<Categorie> listIn = query.list();
		List<Categorie> listOut = new ArrayList<Categorie>();
		if (listIn != null) {
			for (Categorie elem : listIn) {
				elem.setImage("data:image/png;base64," + Base64.encodeBase64String(elem.getPhoto()));
				listOut.add(elem);
			}
			return listOut;
		} else {
			return listIn;
		}
	}

	@Override
	public Categorie addCategorie(Categorie ca) {

		// Ouvrir une session
		Session s = sf.getCurrentSession();

		s.save(ca);

		return ca;
	}

	@Override
	public Categorie updateCategorie(Categorie ca) {

//		Categorie caOut = em.merge(ca);
//		if (caOut != null) {
//			caOut.setImage("data:image/png;base64," + Base64.encodeBase64String(caOut.getPhoto()));
//		}

		return ca;
	}

	@Override
	public int deleteCategorie(Categorie ca) {

//		String req = "DELETE FROM Categorie ca WHERE id_ca=:pId";
//
//		Query query = em.createQuery(req);
//
//		// Assigner les paramètres à la requète
//		query.setParameter("pId", ca.getId());

		return 0; // query.executeUpdate();

	}

	@Override
	public Categorie getById(Categorie ca) {

//		Categorie caOut = em.find(Categorie.class, ca.getId());
//		if (caOut != null) {
//			caOut.setImage("data:image/png;base64," + Base64.encodeBase64String(caOut.getPhoto()));
//		}

		return null; // caOut;
	}

	@Override
	public List<Categorie> getByName(Categorie ca) {

//		String req = "SELECT ca FROM Categorie ca WHERE ca.nom LIKE :pNom";
//
//		Query query = em.createQuery(req);
//
//		// Construction de la chaine de caractère
//		String name = "%" + ca.getNom() + "%";
//
//		// Assigner les paramètres à la requète
//		query.setParameter("pNom", name);
//
//		// FOR ELEMENT LIST set image
//		List<Categorie> listIn = query.getResultList();
//		List<Categorie> listOut = new ArrayList<Categorie>();
//		if (listIn != null) {
//			for (Categorie elem : listIn) {
//				elem.setImage("data:image/png;base64," + Base64.encodeBase64String(elem.getPhoto()));
//				listOut.add(elem);
//			}
//			return listOut;
//		} else {
//
//			return listIn;
//		}

		return null;
	}

	@Override
	public List<Categorie> getByMotCle(String motCle) {

//		String req = "SELECT ca FROM Categorie ca WHERE ca.descre LIKE :pDescre";
//
//		Query query = em.createQuery(req);
//
//		// Construction de la chaine de caractère
//		motCle = "%" + motCle + "%";
//
//		// Assigner les paramètres à la requète
//		query.setParameter("pDescre", motCle);
//
//		// FOR ELEMENT LIST set image
//		List<Categorie> listIn = query.getResultList();
//		List<Categorie> listOut = new ArrayList<Categorie>();
//		if (listIn != null) {
//			for (Categorie elem : listIn) {
//				elem.setImage("data:image/png;base64," + Base64.encodeBase64String(elem.getPhoto()));
//				listOut.add(elem);
//			}
//			return listOut;
//		} else {
//
//			return listIn;
//		}
		return null;
	}

}
