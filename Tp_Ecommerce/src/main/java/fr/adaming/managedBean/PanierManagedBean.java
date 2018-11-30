package fr.adaming.managedBean;

import java.util.ArrayList;
import java.util.List;

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
			this.produit= new Produit();
			this.commande= new Commande();
			this.lignecommande= new LigneCommande();
			this.listlc= new ArrayList<LigneCommande>();
			this.panier=new Panier();
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
	
		//Les méthodes de Panier
		
		public void addPanier() {
			if(this.lignecommande.getQuantite()>=this.lignecommande.getProduit().getQuantite()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le stock actuel n'est pas suffisant pour répondre a votre demande"));
			} else {
			
			this.lignecommande.setPrix(this.lignecommande.getQuantite()*this.lignecommande.getProduit().getPrix());
			System.out.println(this.lignecommande.getPrix());
			this.listlc.add(this.lignecommande);
			this.panier.setListCommande(this.listlc);
			}
			

		}

}
