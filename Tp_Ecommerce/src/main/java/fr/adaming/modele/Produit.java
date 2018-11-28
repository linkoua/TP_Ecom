package fr.adaming.modele;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produits")

public class Produit implements Serializable {
	// Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pr")
	private int id;
	private String designation;
	private String descre;
	private double prix;
	private int quantite;
	private boolean selectionne;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	

	// Transformation de l'asso UML en Java
	@ManyToOne
	@JoinColumn(name = "ca_id", referencedColumnName = "id_ca")
	private Categorie categorie;
	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LigneCommande> listelcommande;

	// Declaration des constructeurs

	public Produit() {
		super();
	}

	public Produit(String designation, String descre, double prix, int quantite, boolean selectionne, byte[] photo) {
		super();
		this.designation = designation;
		this.descre = descre;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	public Produit(int id, String designation, String descre, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.id = id;
		this.designation = designation;
		this.descre = descre;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}

	// Declaration des getter et des setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescre() {
		return descre;
	}

	public void setDescre(String descre) {
		this.descre = descre;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

}
