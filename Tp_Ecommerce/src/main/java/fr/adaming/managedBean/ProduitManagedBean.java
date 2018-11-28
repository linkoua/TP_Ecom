package fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.LigneCommande;
import fr.adaming.modele.Panier;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "prMB")
@RequestScoped
public class ProduitManagedBean {

	// Appel des services admin
	private IProduitService prService;
	private ICategorieService caService;
	private Produit produit, produitselected;
	private List<Produit> listproduct;
	private List<Produit> listproductCat;
	private List<Produit> listproductSelect;
	private List<Produit> listproductTag;
	private List<Categorie> listcategorie;
	private Panier panier;
	private LigneCommande lcommande;
	private Categorie categorie;
	private boolean checkbox;
	HttpSession maSession;

	// Creer un newfile
	private UploadedFile file;
	@Transient
	private String image;

	// Constructeur vide
	public ProduitManagedBean() {
		super();
	}

	// Init des objets
	@PostConstruct
	public void init() {
		this.produit = new Produit();
		this.produitselected = new Produit();
		// Récup session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listproduct = prService.getAllProduit();
		this.lcommande= new LigneCommande();
		this.panier= new Panier();
		this.listcategorie= caService.getAllCategories();
		this.categorie= new Categorie();
	}

	// Declaration des getters et des setters
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Produit> getListproduct() {
		return listproduct;
	}

	public void setListproduct(List<Produit> listproduct) {
		this.listproduct = listproduct;
	}

	public List<Produit> getListproductCat() {
		return listproductCat;
	}

	public void setListproductCat(List<Produit> listproductCat) {
		this.listproductCat = listproductCat;
	}

	public List<Produit> getListproductSelect() {
		return listproductSelect;
	}

	public void setListproductSelect(List<Produit> listproductSelect) {
		this.listproductSelect = listproductSelect;
	}

	public List<Produit> getListproductTag() {
		return listproductTag;
	}

	public void setListproductTag(List<Produit> listproductTag) {
		this.listproductTag = listproductTag;
	}

	public Produit getProduitselected() {
		return produitselected;
	} 

	public boolean isCheckbox() {
		return checkbox;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public List<Categorie> getListcategorie() {
		return listcategorie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public void setListcategorie(List<Categorie> listcategorie) {
		this.listcategorie = listcategorie;
	}

	public void setProduitselected(Produit produitselected) {
		this.produitselected = produitselected;
	}

	// Les méthodes du ManagedBean
	public String ajouterProduit() {
		this.produit.setCategorie(this.categorie);
		if (file != null) {
			this.produit.setPhoto(file.getContents());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
		}
		if (prService.addProduit(this.produit) != null) {
			this.produit = prService.addProduit(this.produit);
			// Mettre à jour la liste des produits
			maSession.setAttribute("listPrSession", prService.getAllProduit());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de l'ajout"));
		}
		return "listproduct.xhtml";

	}

	public String modifierProduit() {
		this.produit.setCategorie(this.categorie);
		if (this.checkbox==true) {
		if (file != null) {
			this.produit.setPhoto(file.getContents());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
		}
		if (prService.addProduit(this.produit) != null) {
			this.produit = prService.modifyProduit(this.produit);
			this.listproduct = prService.getAllProduit();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de l'ajout"));
		}}
		else {
			if (file != null) {
				this.produit.setPhoto(prService.searchProduit(this.produit).getPhoto());
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
			}
			if (prService.addProduit(this.produit) != null) {
				this.produit = prService.modifyProduit(this.produit);
				this.listproduct = prService.getAllProduit();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de l'ajout"));
			}
			
		}
		return "listproduct.xhtml";
	}

	public String supprimerProduit() {
		if (prService.deleteProduit(this.produit) != 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectuée"));
			this.listproduct = prService.getAllProduit();
			return "listproduct.xhtml";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec lors de la suppression"));
			return "listproduct.xhtml";
		}
	}

	public String chercherProduit() {
		if (prService.searchProduit(this.produit) != null) {
			this.produit = prService.searchProduit(this.produit);
			return "resultproduct.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de la recherche"));
			return "resultproduct.xhtml";
		}
	}

	public String chercherProduitCat() {
		if (prService.getAllProduitCat(this.produit) != null) {
			this.listproductCat = prService.getAllProduitCat(this.produit);
			return "resultproduct.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de la recherche"));
			return "resultproduct.xhtml";
		}
	}
	
	public String chercherProduitTag() {
		if (prService.getAllProduitCat(this.produit) != null) {
			this.listproductCat = prService.getAllProduitCat(this.produit);
			return "resultproduct.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de la recherche"));
			return "resultproduct.xhtml";
		}
	}
	
	public String ajouterPanier() {
		lcommande.setProduit(produit);
		lcommande.setPrix(produit.getPrix());
		lcommande.setQuantite(1);
		panier.setLcommande(lcommande);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a correctement été ajouté au panier"));
		return "productlist.xhtml";
	}


	public String modifierLien() {
		return "updateproduct.xhtml";
	}
}
