package fr.esiea.poo;

/**
 * Classe de représentation d'une offre faite par un acheteur.<br>
 * L'offre contient une référence vers l'acheteur qui l'emet.<br>
 * 
 * */
public class Offre {

	/**
	 * Compter les instances pour leur associer un numéro unique (plage de int),
	 * les numéros libérés sont redonnés aux instances nouvellement crées.<br>
	 * /!\
	 * <em>On ne peut pas se fier à un numéro pour répertorier une Offre</em>
	 * /!\
	 */
	private static int numOffre = 0;

	private double prix = 0;
	/**
	 * /!\
	 * <em>On ne peut pas se fier à un numéro pour répertorier une Offre</em>
	 * /!\ Ce numéro est unique pour chaque instance mais lors de la destruction
	 * d'une instance et la création d'une nouvelle Offre, un numéro
	 * précédemment utilisé est redonné.
	 * */
	private int id = 0;
	private Acheteur ach;

	/**
	 * Contructeur de l'Offre
	 * 
	 * @param acheteur
	 * @param prix
	 */
	public Offre(Acheteur ach, double _prix) {
		numOffre++;
		id = numOffre;
		prix = _prix;
		this.ach = ach;
	}

	/** Obtenir le montant associé à l'offre */
	public double getPrix() {
		return this.prix;
	}

	/** Destructeur d'offres, permet de décrémenter la static numOffre */
	public void finalize() {
		numOffre--;
	}

	/**
	 * /!\
	 * <em>On ne peut pas se fier à un numéro pour répertorier une Offre</em>
	 * /!\ Ce numéro est unique pour chaque instance mais lors de la destruction
	 * d'une instance et la création d'une nouvelle Offre, un numéro
	 * précédemment utilisé est redonné.
	 * 
	 * @return id de l'offre
	 * */
	public int getId() {
		return id;
	}

}
