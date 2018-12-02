package fr.adaming.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@SessionScoped
public class ClientManagedBean {
	// Injection de dépendance des services
	@ManagedProperty(value = "#{clService}")
	private IClientService clService;

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	private Client client;
	private boolean logInCl;

	private boolean checkbox;
	private String ancienMdp;
	private String nouveauMdp;
	private String confirmMdp;

	private Commande commande;

	HttpSession maSession;

	public ClientManagedBean() {
		super();

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isLogInCl() {
		return logInCl;
	}

	public void setLogInCl(boolean logInCl) {
		this.logInCl = logInCl;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getAncienMdp() {
		return ancienMdp;
	}

	public void setAncienMdp(String ancienMdp) {
		this.ancienMdp = ancienMdp;
	}

	public String getNouveauMdp() {
		return nouveauMdp;
	}

	public void setNouveauMdp(String nouveauMdp) {
		this.nouveauMdp = nouveauMdp;
	}

	public String getConfirmMdp() {
		return confirmMdp;
	}

	public void setConfirmMdp(String confirmMdp) {
		this.confirmMdp = confirmMdp;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// Les méthodes
	@PostConstruct
	public void init() {
		this.client = new Client();
	}

	public String suscribe() {
		this.client = clService.addClient(this.client);
		if (this.client != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'inscription a été réalisée avec succès"));
			return "/login.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un problème a été rencontré"));
			return "/login.xhtml";
		}
	}

	public String login() {

		Client clOut = clService.isExist(this.client);

		if (clOut != null) {

			// Le mettre dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clOut);

			// Mettre la liste des commandes du client deans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Commande",
					clOut.getpCommandes());

			return "/home.xhtml";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mail ou MdP Invalide"));
			return "/login.xhtml";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/home.xhtml";
	}

	public String lienUpdate() {
		return "updateClient";
	}

	public String updateClient() {

		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Client clientSession = (Client) maSession.getAttribute("clSession");

		if (checkbox == true) {

			// Check mot de passe
			if (!ancienMdp.equals(clientSession.getPassword())) {

				// Message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Echec!", "Le mot de passe actuel ne correspond pas."));

				ancienMdp = "";
				nouveauMdp = "";
				confirmMdp = "";

				return null;

			} else if (!nouveauMdp.equals(confirmMdp)) {

				// Message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Echec!", "Le mot de passe de confirmation ne correspond pas au nouveau mot de passe."));

				ancienMdp = "";
				nouveauMdp = "";
				confirmMdp = "";

				return null;

			} else {
				client.setPassword(nouveauMdp);
			}

		} else {

			client.setPassword(clientSession.getPassword());
		}

		Client clOut = clService.modifyClient(client);

		if (clOut != null) {

			// Mettre à jour la liste des categories
			maSession.setAttribute("clSession", clOut);

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Réussi!", "Vos informations ont bien été modifié."));

			return "compteClient";

		} else {

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec!",
					"Une erreur est survenue dans la modification de vos informations personnelles."));
		}

		return null;
	}
}
