package fr.adaming.modele;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Categorie implements Serializable {

	// Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ca")
	private int id;
	private String nom;
	@Lob
	private byte[] photo;
	private String descre;

	@Transient
	private String image;

	// Transformation de l'association UML
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Produit> listep;

	// Declaration des constructeurs

	public Categorie() {
		super();
	}

	public Categorie(String nom, byte[] photo, String descre) {
		super();
		this.nom = nom;
		this.photo = photo;
		this.descre = descre;
	}

	public Categorie(int id, String nom, byte[] photo, String descre) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.descre = descre;
	}

	// Declaration des getter et setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescre() {
		return descre;
	}

	public void setDescre(String descre) {
		this.descre = descre;
	}

	public Set<Produit> getListep() {
		return listep;
	}

	public void setListep(Set<Produit> listep) {
		this.listep = listep;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
