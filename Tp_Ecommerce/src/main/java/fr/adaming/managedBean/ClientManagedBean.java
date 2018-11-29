package fr.adaming.managedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@SessionScoped
public class ClientManagedBean {
	// Injection de dépendance des services
		@ManagedProperty(value = "#{adService}")
		private IClientService clService;
		
		
		private Client client;
		private boolean logInCl;



		public ClientManagedBean() {
			super();
			
		}
		
@PostConstruct
public void init() {
	this.client=new Client();
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
public String login() {
	System.out.println(client.getMail());
	System.out.println(client.getPassword());
	try {
		Client clOut = clService.isExist(this.client);

		// Le mettre dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clSession", clOut);

		// Mettre la liste des catégories et des produits dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Commande",
				clOut.getpCommandes());


		// Acceptation du logIn (boolean true)
		// Utilisation de la méthode de redirection
		logInCl = true;
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
