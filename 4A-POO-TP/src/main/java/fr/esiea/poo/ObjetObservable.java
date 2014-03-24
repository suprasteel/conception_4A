package fr.esiea.poo;

import java.util.HashSet;

import fr.esiea.poo.Alerte.TypeAlerte;

/**
 * Interface d'objet observé
 */
public class ObjetObservable {

	private HashSet<ObservateurEncheres> setObservateursNouvelleOffre = new HashSet<ObservateurEncheres>();
	private HashSet<ObservateurEncheres> setObservateursReserveAtteinte = new HashSet<ObservateurEncheres>();
	private HashSet<ObservateurEncheres> setObservateursFinEnchere = new HashSet<ObservateurEncheres>();
	private HashSet<ObservateurEncheres> setObservateursAnnule = new HashSet<ObservateurEncheres>();

	/**
	 * Ajouter un observateur à toutes les alertes
	 * 
	 * @param pObservateur
	 */
	public void attacherObsToutesAlertes(ObservateurEncheres pObservateur) {
		setObservateursNouvelleOffre.add(pObservateur);
		setObservateursReserveAtteinte.add(pObservateur);
		setObservateursFinEnchere.add(pObservateur);
		setObservateursAnnule.add(pObservateur);
	}

	/**
	 * Ajouter un observateur à la liste des alertes de nouvelles offres sur
	 * enchères
	 * 
	 * @param pObservateur
	 */
	public void attacherObsNvlOffre(ObservateurEncheres pObservateur) {
		setObservateursNouvelleOffre.add(pObservateur);
	}

	/**
	 * Ajouter un observateur à la liste de reception des alertes dont le prix
	 * de reserve est atteint
	 * 
	 * @param pObservateur
	 */

	public void attacherObsReserveAtteinte(ObservateurEncheres pObservateur) {
		setObservateursReserveAtteinte.add(pObservateur);
	}

	/**
	 * Ajouter un observateur à la liste de reception des alertes Expirées
	 * 
	 * @param pObservateur
	 */
	public void attacherObsFinEnchere(ObservateurEncheres pObservateur) {
		setObservateursFinEnchere.add(pObservateur);
	}

	/**
	 * Ajouter un observateur à la liste de reception des alertes
	 * setObservateursAnnule
	 * 
	 * @param pObservateur
	 */
	public void attacherObsAnnule(ObservateurEncheres pObservateur) {
		setObservateursAnnule.add(pObservateur);
	}

	/**
	 * Supprimer un observateur des listes de toutes les alertes
	 * 
	 * @param pObservateur
	 */
	public void supprimerObsToutesAlertes(ObservateurEncheres pObservateur) {
		setObservateursNouvelleOffre.add(pObservateur);
		setObservateursReserveAtteinte.add(pObservateur);
		setObservateursFinEnchere.add(pObservateur);
		setObservateursAnnule.add(pObservateur);
	}

	/**
	 * Supprimer un observateur de la liste des alertes d'encheres annulées
	 * 
	 * @param pObservateur
	 */
	public void supprimerObsNvlOffre(ObservateurEncheres pObservateur) {
		setObservateursNouvelleOffre.remove(pObservateur);
	}

	/**
	 * Supprimer un observateur de la liste des alertes d'encheres
	 * setObservateursReserveAtteinte
	 * 
	 * @param pObservateur
	 */
	public void supprimerObsReserveAtteinte(ObservateurEncheres pObservateur) {
		setObservateursReserveAtteinte.remove(pObservateur);
	}

	/**
	 * Supprimer un observateur de la liste des alertes d'encheres annulées
	 * 
	 * @param pObservateur
	 */
	public void supprimerObsFinEnchere(ObservateurEncheres pObservateur) {
		setObservateursFinEnchere.remove(pObservateur);
	}

	/**
	 * Supprimer un observateur de la liste des alertes d'encheres annulées
	 * 
	 * @param pObservateur
	 */
	public void supprimerObsAnnule(ObservateurEncheres pObservateur) {
		setObservateursAnnule.remove(pObservateur);
	}

	/**
	 * On met chaque utilisateur à jour, il suffit d'appeler notifier(_alerte) sur
	 * l'observé.
	 */
	protected void notifier(Alerte a) {
		if (a.getAlerteType() == TypeAlerte.SURENCHERE) {
			for (ObservateurEncheres lObservateur : setObservateursNouvelleOffre) {
				lObservateur.receptAlerte(a);
			}
		}
		if (a.getAlerteType() == TypeAlerte.RESERVE_ATTEINTE) {
			for (ObservateurEncheres lObservateur : setObservateursReserveAtteinte) {
				lObservateur.receptAlerte(a);
			}
		}
		if (a.getAlerteType() == TypeAlerte.FIN_ENCHERE) {
			for (ObservateurEncheres lObservateur : setObservateursFinEnchere) {
				lObservateur.receptAlerte(a);
			}
		}
		if (a.getAlerteType() == TypeAlerte.ANNULATION) {
			for (ObservateurEncheres lObservateur : setObservateursAnnule) {
				lObservateur.receptAlerte(a);
			}
		}
	}
}
