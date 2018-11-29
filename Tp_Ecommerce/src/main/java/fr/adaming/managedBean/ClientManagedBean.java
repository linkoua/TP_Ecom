package fr.adaming.managedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
}
