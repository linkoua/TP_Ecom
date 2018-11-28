package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.modele.Categorie;

@Local
public interface ICategorieDao {

	public List<Categorie> getAllCategories();

	public Categorie addCategorie(Categorie ca);

	public Categorie updateCategorie(Categorie ca);

	public int deleteCategorie(Categorie ca);

	public Categorie getById(Categorie ca);

	public List<Categorie> getByName(Categorie ca);

	public List<Categorie> getByMotCle(String motCle);

}
