package fr.esiea.poo;

import java.util.List;

import fr.esiea.poo.Alerte.TypeAlerte;

/**
 * Interface des observateurs d'enchères
 * */
public abstract class ObservateurEncheres {

	/**
	 * Méthode de notification d'une nouvelleEnchère, appelée par l'objet
	 * observé
	 */
	public abstract void receptAlerte(Alerte a);

	/**
	 * Méthode d'abonnement de l'User à des alertes.<br>
	 * Il suffit de passer une liste contenant les types des alertes que l'user
	 * souhaite recevoir, ainsi que l'enchere à laquelle il souhaite s'abonner.
	 * 
	 */
	public void inscriptionAlerte(Enchere _enchere,
			List<TypeAlerte> _listeAlertesDesirees) {
		if (_listeAlertesDesirees.contains(TypeAlerte.ANNULATION)) {
			_enchere.attacherObsAnnule(this);
		}
		if (_listeAlertesDesirees.contains(TypeAlerte.FIN_ENCHERE)) {
			_enchere.attacherObsFinEnchere(this);
		}
		if (_listeAlertesDesirees.contains(TypeAlerte.RESERVE_ATTEINTE)) {
			_enchere.attacherObsReserveAtteinte(this);
		}
		if (_listeAlertesDesirees.contains(TypeAlerte.SURENCHERE)) {
			_enchere.attacherObsNvlOffre(this);
		}
	}

	/**
	 * Méthode de désabonnement de l'User à des alertes.<br>
	 * Il suffit de passer une liste contenant les types des alertes que l'user
	 * souhaite annuler.<br>
	 * Méthode développée dans ObservateurEnchere par commodité,
	 * l'implémentation de User se rapproche mieux du principe SRP
	 */
	public void desabonnementAlertesEnchere(Enchere _enchere,
			List<TypeAlerte> _listeAlertesASupprimer) {
		if (_listeAlertesASupprimer.contains(TypeAlerte.ANNULATION)) {
			_enchere.attacherObsAnnule(this);
		}
		if (_listeAlertesASupprimer.contains(TypeAlerte.FIN_ENCHERE)) {
			_enchere.attacherObsFinEnchere(this);
		}
		if (_listeAlertesASupprimer.contains(TypeAlerte.RESERVE_ATTEINTE)) {
			_enchere.attacherObsReserveAtteinte(this);
		}
		if (_listeAlertesASupprimer.contains(TypeAlerte.SURENCHERE)) {
			_enchere.attacherObsNvlOffre(this);
		}
	}
	
	/**
	 * Méthode de désabonnement de l'User à des alertes.<br>
	 * Il suffit de passer une liste contenant les types des alertes que l'user
	 * souhaite annuler.<br>
	 * Méthode développée dans ObservateurEnchere par commodité,
	 * l'implémentation de User se rapproche mieux du principe SRP
	 */
	public void desabonnementAlertesEnchere(Enchere _enchere){
		_enchere.supprimerObsToutesAlertes(this);
	}

}
