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
	 * /!\ <em>On ne peut pas se fier à un numéro pour répertorier une Offre</em> /!\
	 */
	private static int numOffre = 0;

	private double prix = 0;
	private int id = 0;
	private Acheteur ach;

	/** Contructeur de l'Offre */
	public Offre(Acheteur ach, double _prix) {
		numOffre++;
		id = numOffre;
		prix = _prix;
		this.ach = ach;
	}

	public double getPrix() {
		return this.prix;
	}
	
	/** Destructeur d'offres, permet de décrémenter la static numOffre */
	public void finalize()
    {
		numOffre--;
    }

}
