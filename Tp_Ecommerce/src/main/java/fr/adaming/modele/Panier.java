package fr.adaming.modele;

import java.util.List;
public class Panier {
	// Attribut
	private int id;

	// Transformation de l'association UML
	private List<LigneCommande> listCommande;
	private LigneCommande lcommande;
	private double total;

	// Builder
	public Panier() {
		super();
	}

	// Getter et Setters
	public Panier(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<LigneCommande> getListCommande() {
		return listCommande;
	}

	public void setListCommande(List<LigneCommande> listCommande) {
		this.listCommande = listCommande;
	}

	public LigneCommande getLcommande() {
		return lcommande;
	}

	public void setLcommande(LigneCommande lcommande) {
		this.lcommande = lcommande;
	}

	// toString (debug usefull)

	@Override
	public String toString() {
		return "Panier [id=" + id + ", listCommande=" + listCommande + ", lcommande=" + lcommande + "]";
	}



}
