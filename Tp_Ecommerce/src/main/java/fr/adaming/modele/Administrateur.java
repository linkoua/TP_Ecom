package fr.adaming.modele;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Administrateur extends Utilisateur {

	// Declarations des attributs
	private String password;

	// Declaration des constructeurs
	public Administrateur() {
		super();

	}

	public Administrateur(String nom, String mail, String password) {
		super(nom, mail);
		this.password = password;
	}

	public Administrateur(int id, String nom, String mail, String password) {
		super(id, nom, mail);
		this.password = password;
	}

	// Declaration Getter/Setter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString (debug usefull)

	@Override
	public String toString() {
		return "Administrateur [password=" + password + ", getId()=" + getId() + ", getNom()=" + getNom()
				+ ", getMail()=" + getMail() + "]";
	}

}
