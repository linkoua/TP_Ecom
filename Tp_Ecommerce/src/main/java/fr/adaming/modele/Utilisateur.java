package fr.adaming.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Utilisateur implements Serializable {

	// Attributs
	@Id
	@Column(name = "id_ut")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String mail;

	// Builder
	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String mail) {
		super();
		this.nom = nom;
		this.mail = mail;
	}

	public Utilisateur(int id, String nom, String mail) {
		super();
		this.id = id;
		this.nom = nom;
		this.mail = mail;
	}

	// Getters et Setters
	public long getId() {
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// toString (debug usefull)

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", mail=" + mail + "]";
	}

}
