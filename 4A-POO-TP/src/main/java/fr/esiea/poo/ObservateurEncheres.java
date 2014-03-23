package fr.esiea.poo;

/**
 * Interface des observateurs d'enchères
 * */
public interface ObservateurEncheres {

	/**
	 * Méthode de notification d'une nouvelleEnchère, appelée par l'objet observé
	 */
	public void receptNvlEnchere(Enchere e);

}
