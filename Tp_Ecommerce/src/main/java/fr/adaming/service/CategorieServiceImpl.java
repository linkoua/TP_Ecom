package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {

	// Transformation de l'associationUML en JAVA
	@EJB
	private ICategorieDao caDao;

	@Override
	public List<Categorie> getAllCategories() {

		return caDao.getAllCategories();
	}

	@Override
	public Categorie addCategorie(Categorie ca) {

		return caDao.addCategorie(ca);
	}

	@Override
	public Categorie updateCategorie(Categorie ca, Administrateur ad) {

		return caDao.updateCategorie(ca);
	}

	@Override
	public int deleteCategorie(Categorie ca, Administrateur ad) {

		return caDao.deleteCategorie(ca);
	}

	@Override
	public Categorie getById(Categorie ca) {

		return caDao.getById(ca);
	}

	@Override
	public List<Categorie> getByName(Categorie ca) {

		return caDao.getByName(ca);
	}

	@Override
	public List<Categorie> getByMotCles(String[] tableMotCle) {

		List<Categorie> listOut = new ArrayList<Categorie>();

		for (String elem : tableMotCle) {

			listOut.addAll(caDao.getByMotCle(elem));

		}

		return listOut;
	}

}
