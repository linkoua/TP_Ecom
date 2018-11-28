 	package fr.adaming.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lcommandes")
public class LigneCommande implements Serializable {

//Declaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lc")
	private int id;
	private int quantite;
	private double prix;
	// Transformation de l'association UML en Java
	@ManyToOne
	@JoinColumn(name = "pr_id", referencedColumnName = "id_pr")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "co_id", referencedColumnName = "id_co")
	// Transformation de la seconde association
	private Commande commande;

	// Declaration des constructeurs

	public LigneCommande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(int id, int quantite, double prix) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.prix = prix;
	}

	// Declaration des getters et setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// toString (debug usefull)

	@Override
	public String toString() {
		return "LigneCommande [id=" + id + ", quantite=" + quantite + ", prix=" + prix + ", produit=" + produit + "]";
	}

}
