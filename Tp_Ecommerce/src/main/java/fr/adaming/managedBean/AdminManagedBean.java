package fr.adaming.managedBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "adMB")
@SessionScoped
public class AdminManagedBean {
	// Appel des services admin
	@EJB
	private IAdminService adService;
	private Administrateur administrateur;
	// Utilisation d'un boolean pour le filtre login
	private boolean logIn;

	// Appel des services Categorie
	@EJB
	private ICategorieService caService;

	// Appel des services Produit
	@EJB
	private IProduitService prService;

	// Constructeur vide
	public AdminManagedBean() {
		super();
	}

	// Initier les instatiation
	@PostConstruct
	public void init() {
		this.administrateur = new Administrateur();
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	// Les getter et setter
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public boolean isLogIn() {
		return logIn;
	}

	public void setLogIn(boolean logIn) {
		this.logIn = logIn;
	}

	// Les méthodes
	public String login() {
		try {
			Administrateur aOut = adService.isExist(this.administrateur);
			// Le mettre dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adSession", aOut);

			// Mettre la liste des catégories et des produits dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listCaSession",
					caService.getAllCategories());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listPrSession",
					prService.getAllProduit());

			// Acceptation du logIn (boolean true)
			// Utilisation de la méthode de redirection
			logIn = true;
			return "/secured/espace.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mail ou MdP Invalide"));
			return "/login.xhtml";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/home.xhtml";
	}

}
