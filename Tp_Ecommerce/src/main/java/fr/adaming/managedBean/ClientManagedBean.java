package fr.adaming.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Client;
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

	public ClientManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {
		this.client = new Client();
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

// Les méthodes
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
}
