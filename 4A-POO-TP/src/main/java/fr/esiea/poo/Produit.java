package fr.esiea.poo;

/**
 * Classe représentant les produits mis en enchère. Un produit possède un
 * identifiant et une description. Il possède egalement un propriétaire puisque
 * lorsque l'enchère est terminée, le produit change (si l'enchère n'est pas
 * annulée trop tot) de propriétaire.
 */
public class Produit {
	private String id;
	private String description;
	private User proprietaire;

	/** Constructeur de produit.<br><em> Attention, le proprietaire n'est pas setté tout de suite.</em>*/
	public Produit(String _id, String _description) {
		this.id = _id;
		this.description = _description;
	}

	/** Affichage simple d'un objet produit */
	@Override
	public String toString() {
		return "Produit [id=" + id + ", description=" + description + "]";
	}

	/**
	 * Fonction package non demandée dans l'énoncé mais qui permet de définir le
	 * proprietaire
	 */
	void setPropietaire(User _nouveauProprietaire) {
		this.proprietaire = _nouveauProprietaire;
	}

}
