package fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

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
	@ManagedProperty(value="#{prService}")
	private IProduitService prService;
	@ManagedProperty(value="#{caService}")
	private ICategorieService caService;
		
	public void setCaService(ICategorieService caService) {
		this.caService = caService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}


	private Produit produit, produitselected;
	private List<Produit> listproduct;
	private List<Produit> listproductSelect;
	private List<Produit> listproductTag;
	private List<Categorie> listcategorie;
	private Panier panier;
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
		this.produitselected=new Produit();
		// Récup session
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listproduct = prService.getAllProduit();
		this.panier= new Panier();
		this.categorie= new Categorie();
		this.listcategorie= caService.getAllCategories();
	}

	// Declaration des getters et des setters
	public Produit getProduit() {
		return produit;
	}

	public Produit getProduitselected() {
		return produitselected;
	}

	public void setProduitselected(Produit produitselected) {
		this.produitselected = produitselected;
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


	// Les méthodes du ManagedBean
	public String ajouterProduit() {
		this.categorie=caService.getById(categorie);
		if (file != null) {
			this.produit.setPhoto(file.getContents());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
		}
		if (prService.addProduit(this.produit,this.categorie) != null) {
			this.produit = prService.addProduit(this.produit,this.categorie);
			// Mettre à jour la liste des produits
			maSession.setAttribute("listPrSession", prService.getAllProduit());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de l'ajout"));
		}
		return "listproduct.xhtml";

	}

	public String modifierProduit() {
		this.categorie=caService.getById(categorie);
		if (this.checkbox==true) {
		if (file != null) {
			this.produit.setPhoto(file.getContents());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
		}
		if (prService.modifyProduit(this.produit,this.categorie) != null) {
			this.produit = prService.modifyProduit(this.produit,this.categorie);
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
			if (prService.addProduit(this.produit,this.categorie) != null) {
				this.produit = prService.modifyProduit(this.produit,this.categorie);
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

	public String modifierLien() {
		return "updateproduct.xhtml";
	}
}
