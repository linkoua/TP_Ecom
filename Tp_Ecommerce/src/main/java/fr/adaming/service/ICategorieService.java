package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;

@Local
public interface ICategorieService {

	public List<Categorie> getAllCategories();

	public Categorie addCategorie(Categorie ca);

	public Categorie updateCategorie(Categorie ca, Administrateur ad);

	public int deleteCategorie(Categorie ca, Administrateur ad);

	public Categorie getById(Categorie ca);

	public List<Categorie> getByName(Categorie ca);

	public List<Categorie> getByMotCles(String[] tableMotCle);

}
