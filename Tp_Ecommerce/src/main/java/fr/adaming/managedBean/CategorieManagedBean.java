package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.modele.Administrateur;
import fr.adaming.modele.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "caMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Transformation de l'association UML en JAVA
	@EJB
	private ICategorieService caService;

	// Attribut du managed bean
	private Administrateur admin;
	private Categorie categorie;
	private int selectId;
	private List<Categorie> listCa;
	private boolean checkbox;

	private boolean indiceSearchId;
	private boolean indiceSearchNom;
	private boolean indiceSearchMotCle;

	private boolean indiceTableId;
	private boolean indiceTableNom;
	private boolean indiceTableMotCle;

	private String motCle;
	private List<Categorie> resultListCa;
	private Set<Categorie> resultSetCa;

	HttpSession maSession;

	// Creer un newfile
	private UploadedFile file;
	@Transient
	private String image;

	// Builders
	public CategorieManagedBean() {
		super();
	}

	// Getters et Setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public int getSelectId() {
		return selectId;
	}

	public void setSelectId(int selectId) {
		this.selectId = selectId;
	}

	public List<Categorie> getListCa() {
		return listCa;
	}

	public void setListCa(List<Categorie> listCa) {
		this.listCa = listCa;
	}

	public boolean isIndiceSearchId() {
		return indiceSearchId;
	}

	public void setIndiceSearchId(boolean indiceSearchId) {
		this.indiceSearchId = indiceSearchId;
	}

	public boolean isIndiceSearchNom() {
		return indiceSearchNom;
	}

	public void setIndiceSearchNom(boolean indiceSearchNom) {
		this.indiceSearchNom = indiceSearchNom;
	}

	public boolean isIndiceSearchMotCle() {
		return indiceSearchMotCle;
	}

	public void setIndiceSearchMotCle(boolean indiceSearchMotCle) {
		this.indiceSearchMotCle = indiceSearchMotCle;
	}

	public boolean isIndiceTableId() {
		return indiceTableId;
	}

	public void setIndiceTableId(boolean indiceTableId) {
		this.indiceTableId = indiceTableId;
	}

	public boolean isIndiceTableNom() {
		return indiceTableNom;
	}

	public void setIndiceTableNom(boolean indiceTableNom) {
		this.indiceTableNom = indiceTableNom;
	}

	public boolean isIndiceTableMotCle() {
		return indiceTableMotCle;
	}

	public void setIndiceTableMotCle(boolean indiceTableMotCle) {
		this.indiceTableMotCle = indiceTableMotCle;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Categorie> getResultListCa() {
		return resultListCa;
	}

	public void setResultListCa(List<Categorie> resultListCa) {
		this.resultListCa = resultListCa;

	}

	public Set<Categorie> getResultSetCa() {
		return resultSetCa;
	}

	public void setResultSetCa(Set<Categorie> resultSetCa) {
		this.resultSetCa = resultSetCa;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	// Les autres méthodes

	// Initialisation

	@PostConstruct
	public void init() {
		categorie = new Categorie();
		resultListCa = new ArrayList<Categorie>();
		resultSetCa = new HashSet<Categorie>();

		// Récup le session en cours
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// Récup l'admin de la session
		admin = (Administrateur) maSession.getAttribute("adSession");

		listCa = caService.getAllCategories();

	}

	public String addCategorie() {

		if (file != null) {
			this.categorie.setPhoto(file.getContents());
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
		}

		Categorie caOut = caService.addCategorie(categorie);

		if (caOut != null) {

			for (Categorie elem : caService.getAllCategories()) {
				System.out.println(elem.getImage());
			}

			// Mettre à jour la liste des categories
			maSession.setAttribute("listCaSession", caService.getAllCategories());

			return "listCategories";

		} else {

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec!", "La categorie n'as pas été ajouté"));
		}

		return null;
	}

	public String linkUpdate() {
		return "updateCategorie";
	}

	public String updateCategorie() {

		if (checkbox == true) {
			if (file != null) {
				this.categorie.setPhoto(file.getContents());
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Failed"));
			}
		} else {
			this.categorie.setPhoto(caService.getById(categorie).getPhoto());
		}

		Categorie caOut = caService.updateCategorie(categorie, admin);

		if (caOut != null) {

			// Mettre à jour la liste des categories
			maSession.setAttribute("listCaSession", caService.getAllCategories());

			return "listCategories";

		} else {

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec!", "La categorie n'as pas été modifié"));
		}

		return null;
	}

	public String deleteCategorie() {

		// Recherche de la catégorie par l'ID selectionné

		categorie = caService.getById(categorie);

		int verif = caService.deleteCategorie(categorie, admin);

		if (verif != 0) {

			// Mettre à jour la liste des categories
			maSession.setAttribute("listCaSession", caService.getAllCategories());

		} else {

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec!", "La categorie n'as pas été supprimé"));

		}

		return "listCategories";

	}

	public String showCategories() {
		return "categoriesList";
	}

}
