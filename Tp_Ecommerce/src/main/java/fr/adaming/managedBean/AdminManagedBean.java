package fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "adMB")
@SessionScoped
public class AdminManagedBean {

	// Injection de dépendance des services
	@ManagedProperty(value = "#{adService}")
	private IAdminService adService;
	@ManagedProperty(value = "#{caService}")
	private ICategorieService caService;
	@ManagedProperty(value = "#{prService}")
	private IProduitService prService;

	// Les setters pour les services
	public void setAdService(IAdminService adService) {
		this.adService = adService;
	}

	public void setCaService(ICategorieService caService) {
		this.caService = caService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	// Les attribut du managedBean
	private Administrateur administrateur;

	// Utilisation d'un boolean pour le filtre login
	private boolean logInAd;

	// Constructeur vide
	public AdminManagedBean() {
		super();
	}

	// Initier les instantiations
	@PostConstruct
	public void init() {
		this.administrateur = new Administrateur();
	}

	// Les getters et setters
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public boolean isLogInAd() {
		return logInAd;
	}

	public void setLogInAd(boolean logIn) {
		this.logInAd = logIn;
	}

	// Les méthodes
	public String login() {

		Administrateur aOut = adService.isExist(this.administrateur);

		if (aOut != null) {
			List<Categorie> listeCat = caService.getAllCategories();
			List<Produit> listePro = prService.getAllProduit();

			// Le mettre dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adSession", aOut);

			// Mettre la liste des catégories et des produits dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listCaSession", listeCat);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listPrSession", listePro);

			// Acceptation du logIn (boolean true)
			// Utilisation de la méthode de redirection
			logInAd = true;
			return "/secured/espace.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mail ou MdP Invalide"));
			return "/login.xhtml";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/home.xhtml";
	}

}
