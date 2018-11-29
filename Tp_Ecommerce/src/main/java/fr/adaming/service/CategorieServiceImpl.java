package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;

@Service("caService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	// Transformation de l'associationUML en JAVA
	@Autowired
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

		Categorie caOut = caDao.getById(ca);

		if (caOut != null) {
			caOut.setNom(ca.getNom());
			caOut.setDescre(ca.getDescre());
			caOut.setPhoto(ca.getPhoto());
			caOut.setImage(ca.getImage());
			caOut.setListep(ca.getListep());

			return caDao.updateCategorie(caOut);
		} else {

			return null;
		}

	}

	@Override
	public int deleteCategorie(Categorie ca, Administrateur ad) {

		ca = caDao.getById(ca);

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
