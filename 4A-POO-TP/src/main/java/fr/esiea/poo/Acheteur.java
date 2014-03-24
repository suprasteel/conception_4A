package fr.esiea.poo;

import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

public interface Acheteur
{

	/**
	 * Emet une offre sur l'enchere passée en parametre et retourne l'offre crée
	 * 
	 * @param ench
	 * @param prix
	 * @return offre émise
	 * @throws ForbiddenBidOperation 
	 * @throws InsuffisantOfferPrice 
	 */
	Offre emettreOffre(Enchere ench, double prix) throws ForbiddenBidOperation, InsuffisantOfferPrice;

	/**
	 * Retourne vrai si le prix de réserve à été atteind, faux sinon
	 * 
	 * @param ench
	 * @return
	 */
	boolean isPrixReserveAtteint(Enchere ench);

}
