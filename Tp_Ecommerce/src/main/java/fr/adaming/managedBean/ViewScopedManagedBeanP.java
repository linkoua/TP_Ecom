package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "vspMB")
@ViewScoped
public class ViewScopedManagedBeanP implements Serializable {

	// Attribut
	private Produit produit;
	private String tag;
	private List<Produit> resultListPr;
	private Set<Produit> resultSetPr;

	private boolean indiceSearchId;
	private boolean indiceSearchCat;
	private boolean indiceSearchMotCle;

	private boolean indiceTableId;
	private boolean indiceTableCat;
	private boolean indiceTableMotCle;

	// Transformation de l'association UML
	private IProduitService prService;

	// Builder
	public ViewScopedManagedBeanP() {
		super();
	}

	// Getters et Setters


	public boolean isIndiceSearchId() {
		return indiceSearchId;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Produit> getResultListPr() {
		return resultListPr;
	}

	public void setResultListPr(List<Produit> resultListPr) {
		this.resultListPr = resultListPr;
	}

	public Set<Produit> getResultSetPr() {
		return resultSetPr;
	}

	public void setResultSetPr(Set<Produit> resultSetPr) {
		this.resultSetPr = resultSetPr;
	}

	public void setIndiceSearchId(boolean indiceSearchId) {
		this.indiceSearchId = indiceSearchId;
	}

	public boolean isIndiceSearchCat() {
		return indiceSearchCat;
	}

	public void setIndiceSearchNom(boolean indiceSearchNom) {
		this.indiceSearchCat = indiceSearchNom;
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
		return indiceTableCat;
	}

	public void setIndiceTableNom(boolean indiceTableNom) {
		this.indiceTableCat = indiceTableNom;
	}

	public boolean isIndiceTableMotCle() {
		return indiceTableMotCle;
	}

	public void setIndiceTableMotCle(boolean indiceTableMotCle) {
		this.indiceTableMotCle = indiceTableMotCle;
	}

	// Les autres méthodes

	// Initialisation

	@PostConstruct
	public void init() {
		produit = new Produit();
		resultListPr = new ArrayList<Produit>();
		resultSetPr = new HashSet<Produit>();
	}

	public void changeSearch(ValueChangeEvent event) {

		String newVal = event.getNewValue().toString();

		if (newVal.equals("ID")) {

			indiceSearchId = true;
			indiceSearchCat = false;
			indiceSearchMotCle = false;

		} else if (newVal.equals("Nom")) {

			indiceSearchId = false;
			indiceSearchCat = true;
			indiceSearchMotCle = false;

		} else if (newVal.equals("MotCle")) {

			indiceSearchId = false;
			indiceSearchCat = false;
			indiceSearchMotCle = true;

		} else {

			indiceSearchId = false;
			indiceSearchCat = false;
			indiceSearchMotCle = false;

		}

	}

	public void searchprById() {

		produit = prService.searchProduit(produit);

		if (produit != null) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTableId = true;
			indiceTableCat = false;
			indiceTableMotCle = false;

		} else {

			// Cacher tous les tableaux de résultats
			indiceTableId = false;
			indiceTableCat = false;
			indiceTableMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de produit correspondant à l'id"));
			produit= new Produit();
		}

	}


	public void searchprByTag() {

		String[] listMotCle = tag.split(" ");

		resultListPr = prService.getAllProduitTag(listMotCle);

		if (resultListPr.size() != 0) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTableId = false;
			indiceTableCat = false;
			indiceTableMotCle = true;

			// instanciation du set
			resultSetPr = new HashSet<Produit>(resultListPr);

			for (Produit elem : resultSetPr) {
				System.out.println(elem.getId());
			}

		} else {

			// Cacher tous les tableaux de résultats
			indiceTableId = false;
			indiceTableCat = false;
			indiceTableMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de produit correspondant à ces mots clés"));

		}
	}

}
