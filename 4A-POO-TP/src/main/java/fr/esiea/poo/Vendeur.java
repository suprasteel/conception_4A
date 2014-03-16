package fr.esiea.poo;

import java.util.Date;

public interface Vendeur
{
	/**
	 * Crée une enchère sur l'objet spécifié
	 * 
	 * @param obj
	 * @param dateLimite
	 * @return l'enchère créée
	 */
	Enchere creerEnchere(Objet obj, Date dateLimite);

	/**
	 * Crée une enchere avec un prix minimum
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixMin
	 * @return l'enchère créée
	 */
	Enchere creerEncherePrixMin(Objet obj, Date dateLimite, double prixMin);

	/**
	 * Crée une enchere avec un prix de réserve
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixReserve
	 * @return l'enchère créée
	 */
	Enchere creerEncherePrixReserve(Objet obj, Date dateLimite, double prixReserve);

	/**
	 * Crée une enchère avec tout les arguments
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixMin
	 * @param prixReserve
	 * @return l'enchère créée
	 */
	Enchere creerEnchere(Objet obj, Date dateLimite, double prixMin, double prixReserve);

	void publierEnchere(Enchere ench);

}
