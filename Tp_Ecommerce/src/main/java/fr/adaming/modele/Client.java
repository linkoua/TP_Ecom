package fr.adaming.modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends Utilisateur {

	// Attributs
	private String adresse;
	private String tel;

	// Transformation de l'association UML
	@OneToMany(mappedBy = "pClient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Commande> pCommandes;

	// Builder
	public Client() {
		super();
	}

	public Client(String nom, String mail, String password, String adresse, String tel) {
		super(nom, mail, password);
		this.adresse = adresse;
		this.tel = tel;
	}

	public Client(int id, String nom, String mail, String password, String adresse, String tel) {
		super(id, nom, mail, password);
		this.adresse = adresse;
		this.tel = tel;
	}

	// Getters et Setters
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<Commande> getpCommandes() {
		return pCommandes;
	}

	public void setpCommandes(Set<Commande> pCommandes) {
		this.pCommandes = pCommandes;
	}

	// toString (debug usefull)

	@Override
	public String toString() {
		return "Client [adresse=" + adresse + ", tel=" + tel + ", listeco=" + pCommandes + ", getId()=" + getId()
				+ ", getNom()=" + getNom() + ", getMail()=" + getMail() + "]";
	}

}
