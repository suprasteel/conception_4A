package fr.esiea.poo;

import fr.esiea.poo.exception.ForbiddenBidOperation;

public interface Acheteur
{

	/**
	 * Emet une offre sur l'enchere passée en parametre et retourne l'offre crée
	 * 
	 * @param ench
	 * @param prix
	 * @return offre émise
	 * @throws ForbiddenBidOperation 
	 */
	Offre emettreOffre(Enchere ench, double prix) throws ForbiddenBidOperation;

	/**
	 * Retourne vrai si le prix de réserve à été atteint, faux sinon
	 * 
	 * @param ench
	 * @return boolean
	 */
	boolean isPrixReserveAtteint(Enchere ench);

}
