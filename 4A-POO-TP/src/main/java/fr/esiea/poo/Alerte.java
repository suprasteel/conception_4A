package fr.esiea.poo;

public class Alerte {

	//• Le prix de réserve a été atteint par une offre : Etat.ReserveAtteint
	//• L'enchère a été annulée par le vendeur.
	//• Une offre supérieure à celle émise par l'acheteur a été émise par un autre acheteur.
	//L'enchere arrive à son terme
	/** Enumération des états d'alerte possibles */
	public enum TypeAlerte {
		ANNULATION, SURENCHERE, RESERVE_ATTEINTE, FIN_ENCHERE
	}
	
	/** Attributs d'Alertes */
	TypeAlerte typeAlerte;
	Enchere enchere;
	
	/** Constructeur d'Alertes */
	public Alerte(TypeAlerte _typeAlerte, Enchere _enchere) {
		
		this.typeAlerte = _typeAlerte;
		this.enchere = _enchere;
		
	}
	
}