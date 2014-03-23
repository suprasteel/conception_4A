package fr.esiea.poo;

import java.util.Date;

import fr.esiea.poo.exception.ForbiddenBidCancellation;
import fr.esiea.poo.exception.ForbiddenBidUpdate;

public interface Vendeur
{
	/**
	 * Crée une enchère sur l'objet spécifié
	 * 
	 * @param obj
	 * @param dateLimite
	 * @return l'enchère créée
	 */
	Enchere creerEnchere(Produit obj, Date dateLimite);

	/**
	 * Crée une enchere avec un prix minimum
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixMin
	 * @return l'enchère créée
	 */
	Enchere creerEncherePrixMin(Produit obj, Date dateLimite, double prixMin);

	/**
	 * Crée une enchere avec un prix de réserve
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixReserve
	 * @return l'enchère créée
	 */
	Enchere creerEncherePrixReserve(Produit obj, Date dateLimite, double prixReserve);

	/**
	 * Crée une enchère avec tout les arguments
	 * 
	 * @param obj
	 * @param dateLimite
	 * @param prixMin
	 * @param prixReserve
	 * @return l'enchère créée
	 */
	Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin, double prixReserve);

	/**
	 * Publie l'enchère en paramètre
	 * 
	 * @param ench
	 * @throws ForbiddenBidUpdate 
	 */
	void publierEnchere(Enchere ench) throws ForbiddenBidUpdate;

	/**
	 * Annule l'enchère passée en paramètre
	 * 
	 * @param ench
	 * @throws ForbiddenBidUpdate 
	 * @throws ForbiddenBidCancellation 
	 */
	void annulerEnchere(Enchere ench) throws ForbiddenBidUpdate, ForbiddenBidCancellation;

}
