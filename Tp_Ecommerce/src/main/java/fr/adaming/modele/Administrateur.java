package fr.adaming.modele;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Administrateur extends Utilisateur {

	// Declarations des attributs

	// Declaration des constructeurs
	public Administrateur() {
		super();

	}

	public Administrateur(String nom, String mail, String password) {
		super(nom, mail,password);
		
	}

	public Administrateur(int id, String nom, String mail, String password) {
		super(id, nom, mail,password);
		
	}

	// Declaration Getter/Setter
	
	// toString (debug usefull)

	@Override
	public String toString() {
		return "Administrateur [getId()=" + getId() + ", getNom()=" + getNom() + ", getMail()=" + getMail()
				+ ", getPassword()=" + getPassword() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
