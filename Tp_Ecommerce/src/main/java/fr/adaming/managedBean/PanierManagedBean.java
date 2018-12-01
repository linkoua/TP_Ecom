package fr.adaming.managedBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Commande;
import fr.adaming.modele.LigneCommande;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;

@ManagedBean(name = "paMB")
@SessionScoped
public class PanierManagedBean {

	private Produit produit;
	private Commande commande;
	private LigneCommande lignecommande;
	private List<LigneCommande> listlc;
	private Panier panier;

	public PanierManagedBean() {
		super();
	}

	// Init des objets
	@PostConstruct
	public void init() {
		this.produit = new Produit();
		this.commande = new Commande();
		this.lignecommande = new LigneCommande();
		this.listlc = new ArrayList<LigneCommande>();
		this.panier = new Panier();
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

	public LigneCommande getLignecommande() {
		return lignecommande;
	}

	public void setLignecommande(LigneCommande lignecommande) {
		this.lignecommande = lignecommande;
	}



	public List<LigneCommande> getListlc() {
		return listlc;
	}

	public void setListlc(List<LigneCommande> listlc) {
		this.listlc = listlc;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	// Les méthodes de Panier

	public void addPanier() {
//Verifier que le produit n'est pas deja dans la liste
		int verif= 0;
		if (!this.listlc.isEmpty()) {
			for(LigneCommande lc: listlc) {
				if (lc.getProduit().getId()==this.produit.getId()) {
					verif=1;
				}
				
			}
		}
		if(verif!=1) {
					if(this.lignecommande.getQuantite()>this.lignecommande.getProduit().getQuantite()) {

						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le stock n'est pas suffisant"));
					} else {
					
					this.lignecommande.setPrix(this.lignecommande.getQuantite()*this.lignecommande.getProduit().getPrix());
					this.listlc.add(this.lignecommande);
					this.panier.setListCommande(this.listlc);
					} }else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'article est déja dans le panier"));
					}}
					
			

		

	public void calculPrice(){
		this.lignecommande.setPrix(this.lignecommande.getProduit().getPrix()*this.lignecommande.getQuantite());
	}
}

