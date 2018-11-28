package fr.adaming.modele;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commandes")
public class Commande {

	// Attribut
	@Id
	@Column(name = "id_co")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date date;

	// Transformation de l'association UML
	@ManyToOne
	@JoinColumn(name = "ut_id", referencedColumnName = "id_ut")
	private Client pClient;
	
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LigneCommande> listelcommande;

	// Builder
	public Commande() {
		super();
	}

	public Commande(Date date) {
		super();
		this.date = date;
	}

	public Commande(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getpClient() {
		return pClient;
	}

	public void setpClient(Client pClient) {
		this.pClient = pClient;
	}

	public List<LigneCommande> getListelcommande() {
		return listelcommande;
	}

	public void setListelcommande(List<LigneCommande> listelcommande) {
		this.listelcommande = listelcommande;
	}

	// toString (debug usefull)
	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", client=" + pClient + "]";
	}

}
