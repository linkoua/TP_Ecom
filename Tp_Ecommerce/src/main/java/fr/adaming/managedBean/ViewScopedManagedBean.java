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
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "vsMB")
@ViewScoped
public class ViewScopedManagedBean implements Serializable {

	// Attribut
	private Produit produit;
	private Categorie categorie;
	private String motCle;
	private List<Produit> resultListPr;
	private Set<Produit> resultSetPr;
	private List<Categorie> resultListCa;
	private Set<Categorie> resultSetCa;

	private boolean indiceSearchId;
	private boolean indiceSearchNom;
	private boolean indiceSearchMotCle;

	private boolean indiceTablePrId;
	private boolean indiceTablePrCat;
	private boolean indiceTablePrMotCle;

	private boolean indiceTableCaId;
	private boolean indiceTableCaNom;
	private boolean indiceTableCaMotCle;

	private String selectedItem;
	private List<SelectItem> items;

	private boolean indiceCaId;
	private boolean indiceCaNom;
	private boolean indiceCaMotCle;

	private boolean indicePrId;
	private boolean indicePrCa;
	private boolean indicePrMotCle;

	// Transformation de l'association UML
	private ICategorieService caService;
	private IProduitService prService;

	// Builder
	public ViewScopedManagedBean() {
		super();
	}

	// Getters et Setters

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
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

	public boolean isIndiceTablePrId() {
		return indiceTablePrId;
	}

	public void setIndiceTablePrId(boolean indiceTablePrId) {
		this.indiceTablePrId = indiceTablePrId;
	}

	public boolean isIndiceTablePrCat() {
		return indiceTablePrCat;
	}

	public void setIndiceTablePrCat(boolean indiceTablePrCat) {
		this.indiceTablePrCat = indiceTablePrCat;
	}

	public boolean isIndiceTablePrMotCle() {
		return indiceTablePrMotCle;
	}

	public void setIndiceTablePrMotCle(boolean indiceTablePrMotCle) {
		this.indiceTablePrMotCle = indiceTablePrMotCle;
	}

	public boolean isIndiceTableCaId() {
		return indiceTableCaId;
	}

	public void setIndiceTableCaId(boolean indiceTableCaId) {
		this.indiceTableCaId = indiceTableCaId;
	}

	public boolean isIndiceTableCaNom() {
		return indiceTableCaNom;
	}

	public void setIndiceTableCaNom(boolean indiceTableCaNom) {
		this.indiceTableCaNom = indiceTableCaNom;
	}

	public boolean isIndiceTableCaMotCle() {
		return indiceTableCaMotCle;
	}

	public void setIndiceTableCaMotCle(boolean indiceTableCaMotCle) {
		this.indiceTableCaMotCle = indiceTableCaMotCle;
	}

	public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public boolean isIndiceCaId() {
		return indiceCaId;
	}

	public void setIndiceCaId(boolean indiceCaId) {
		this.indiceCaId = indiceCaId;
	}

	public boolean isIndiceCaNom() {
		return indiceCaNom;
	}

	public void setIndiceCaNom(boolean indiceCaNom) {
		this.indiceCaNom = indiceCaNom;
	}

	public boolean isIndiceCaMotCle() {
		return indiceCaMotCle;
	}

	public void setIndiceCaMotCle(boolean indiceCaMotCle) {
		this.indiceCaMotCle = indiceCaMotCle;
	}

	public boolean isIndicePrId() {
		return indicePrId;
	}

	public void setIndicePrId(boolean indicePrId) {
		this.indicePrId = indicePrId;
	}

	public boolean isIndicePrCa() {
		return indicePrCa;
	}

	public void setIndicePrCa(boolean indicePrCa) {
		this.indicePrCa = indicePrCa;
	}

	public boolean isIndicePrMotCle() {
		return indicePrMotCle;
	}

	public void setIndicePrMotCle(boolean indicePrMotCle) {
		this.indicePrMotCle = indicePrMotCle;
	}

	// Les autres méthodes

	// Initialisation

	@PostConstruct
	public void init() {
		produit = new Produit();
		resultListPr = new ArrayList<Produit>();
		resultSetPr = new HashSet<Produit>();

		categorie = new Categorie();
		resultListCa = new ArrayList<Categorie>();
		resultSetCa = new HashSet<Categorie>();

		SelectItemGroup g1 = new SelectItemGroup("Recherche Catégorie par");
		g1.setSelectItems(new SelectItem[] { new SelectItem("CaId", "Référence de catégorie"),
				new SelectItem("CaNom", "Nom de catégorie"), new SelectItem("CaMotsCles", "Mots clés de catégorie") });

		SelectItemGroup g2 = new SelectItemGroup("Recherche Produit par");
		g2.setSelectItems(new SelectItem[] { new SelectItem("PrId", "Référence de produit"),
				new SelectItem("PrCategorie", "Catégorie de produit"),
				new SelectItem("PrMotsCles", "Mots clés de produit") });

		items = new ArrayList<SelectItem>();
		items.add(g1);
		items.add(g2);
	}

	public void changeSearch(ValueChangeEvent event) {

		String newVal = event.getNewValue().toString();

		if (newVal.equals("ID")) {

			indiceSearchId = true;
			indiceSearchNom = false;
			indiceSearchMotCle = false;

		} else if (newVal.equals("Nom")) {

			indiceSearchId = false;
			indiceSearchNom = true;
			indiceSearchMotCle = false;

		} else if (newVal.equals("MotCle")) {

			indiceSearchId = false;
			indiceSearchNom = false;
			indiceSearchMotCle = true;

		} else {

			indiceSearchId = false;
			indiceSearchNom = false;
			indiceSearchMotCle = false;

		}

	}

	public void searchCaById() {

		categorie = caService.getById(categorie);

		if (categorie != null) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTableCaId = true;
			indiceTableCaNom = false;
			indiceTableCaMotCle = false;

		} else {

			// Cacher tous les tableaux de résultats
			indiceTableCaId = false;
			indiceTableCaNom = false;
			indiceTableCaMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de catégorie correspondant à l'id"));

			categorie = new Categorie();

		}

	}

	public void searchCaByName() {

		resultListCa = caService.getByName(categorie);

		if (resultListCa.size() != 0) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTableCaId = false;
			indiceTableCaNom = true;
			indiceTableCaMotCle = false;

		} else {

			// Cacher tous les tableaux de résultats
			indiceTableCaId = false;
			indiceTableCaNom = false;
			indiceTableCaMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de catégorie correspondant à ce nom"));

		}

	}

	public void searchCaByMotCle() {

		String[] listMotCle = motCle.split(" ");

		resultListCa = caService.getByMotCles(listMotCle);

		if (resultListCa.size() != 0) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTableCaId = false;
			indiceTableCaNom = false;
			indiceTableCaMotCle = true;

			// instanciation du set
			resultSetCa = new HashSet<Categorie>(resultListCa);

			for (Categorie elem : resultSetCa) {
				System.out.println(elem.getId());
			}

		} else {

			// Cacher tous les tableaux de résultats
			indiceTableCaId = false;
			indiceTableCaNom = false;
			indiceTableCaMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de catégorie correspondant à ces mots clés"));

		}
	}

	public void searchPrById() {

		produit = prService.searchProduit(produit);

		if (produit.getId() != 0) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTablePrId = true;
			indiceTablePrCat = false;
			indiceTablePrMotCle = false;

		} else {

			// Cacher tous les tableaux de résultats
			indiceTablePrId = false;
			indiceTablePrCat = false;
			indiceTablePrMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de produit correspondant à l'id"));

			produit = new Produit();

		}

	}


	public void searchPrByMotCle() {

		String[] listMotCle = motCle.split(" ");

		resultListPr = prService.getAllProduitTag(listMotCle);

		if (resultListPr.size() != 0) {

			// Afficher le tableau de résultat et cacher les autres
			indiceTablePrId = false;
			indiceTablePrCat = false;
			indiceTablePrMotCle = true;

			// instanciation du set
			resultSetPr = new HashSet<Produit>(resultListPr);

			for (Produit elem : resultSetPr) {
				System.out.println(elem.getId());
			}

		} else {

			// Cacher tous les tableaux de résultats
			indiceTablePrId = false;
			indiceTablePrCat = false;
			indiceTablePrMotCle = false;

			// Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Aucun résultat!", "Pas de produit correspondant à ces mots clés"));
		}

	}

	public void selectedSearch(ValueChangeEvent event) {

		String newVal = event.getNewValue().toString();

		if (newVal.equals("CaId")) {

			indiceCaId = true;
			indiceCaNom = false;
			indiceCaMotCle = false;

			indicePrId = false;
			indicePrCa = false;
			indicePrMotCle = false;

		} else if (newVal.equals("CaNom")) {

			indiceCaId = false;
			indiceCaNom = true;
			indiceCaMotCle = false;

			indicePrId = false;
			indicePrCa = false;
			indicePrMotCle = false;

		} else if (newVal.equals("CaMotsCles")) {

			indiceCaId = false;
			indiceCaNom = false;
			indiceCaMotCle = true;

			indicePrId = false;
			indicePrCa = false;
			indicePrMotCle = false;

		} else if (newVal.equals("PrId")) {

			indiceCaId = false;
			indiceCaNom = false;
			indiceCaMotCle = false;

			indicePrId = true;
			indicePrCa = false;
			indicePrMotCle = false;

		} else if (newVal.equals("PrCategorie")) {

			indiceCaId = false;
			indiceCaNom = false;
			indiceCaMotCle = false;

			indicePrId = false;
			indicePrCa = true;
			indicePrMotCle = false;

		} else if (newVal.equals("PrMotsCles")) {

			indiceCaId = false;
			indiceCaNom = false;
			indiceCaMotCle = false;

			indicePrId = false;
			indicePrCa = false;
			indicePrMotCle = true;

		} else {

			indiceCaId = false;
			indiceCaNom = false;
			indiceCaMotCle = false;

			indicePrId = false;
			indicePrCa = false;
			indicePrMotCle = false;

		}

	}

}
