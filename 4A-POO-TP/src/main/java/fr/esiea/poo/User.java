package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.esiea.poo.Alerte.TypeAlerte;
import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

public class User implements Acheteur, Vendeur, ObservateurEncheres {
	private ArrayList<Enchere> userEnchere = new ArrayList<Enchere>();
	private ArrayList<Offre> userOffres = new ArrayList<>();
	private String login, nom, prenom;
	private SalleEnchere salesHouse = SalleEnchere.getInstance();

	public User(String login, String nom, String prenom) {
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite) {
		Enchere enchere = new Enchere(obj, dateLimite);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEncherePrixMin(Produit obj, Date dateLimite,
			double prixMin) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, 0);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEncherePrixReserve(Produit obj, Date dateLimite,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, 0, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Offre emettreOffre(Enchere ench, double prix)
			throws ForbiddenBidOperation {
		if (this.userEnchere.contains(ench)) {
			throw new ForbiddenBidOperation(
					"Vous ne pouvez pas enchérir sur une enchere qui vous appartient!");
		}
		if (prix < ench.getPrixMin()) {
			throw new ForbiddenBidOperation(
					"Le prix de l'offre est inférieur au prix minimum de l'enchère!");
		}
		Offre offre = new Offre(this, prix);
		userOffres.add(offre);
		try {
			ench.addOffre(offre);
		} catch (InsuffisantOfferPrice e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return offre;
	}

	@Override
	public void publierEnchere(Enchere ench) throws ForbiddenBidOperation {
		if (!userEnchere.contains(ench)) {
			throw new ForbiddenBidOperation(
					"Error updating Bid. Probably not your bid");
		} else {
			ench.setEtat(Etat.PUBLIEE);
			salesHouse.publierEnchere(ench);
		}
	}

	@Override
	public void annulerEnchere(Enchere ench) throws ForbiddenBidOperation {
		if (!userEnchere.contains(ench)) {
			throw new ForbiddenBidOperation(
					"Error updating Bid. Probably not your bid.");
		}
		if (ench.hasOffers()) {
			throw new ForbiddenBidOperation(
					"Impossible d'annuler cette enchère. Le prix de réserve a déjà été atteint.");
		}
		ench.setEtat(Etat.ANNULEE);

		salesHouse.annulerEnchere(ench);
	}

	/**
	 * Méthode d'abonnement de l'User à des alertes.<br>
	 * Il suffit de passer une liste contenant les types des alertes que l'user
	 * souhaite recevoir, ainsi que l'enchere à laquelle il souhaite s'abonner.
	 * 
	 */
	public void abonnementAlertesEnchere(Enchere _enchere, List<TypeAlerte> _listeAlertesDesirees) {
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
	 * souhaite annuler.
	 */
	public void desabonnementAlertesEnchere(Enchere _enchere, List<TypeAlerte> _listeAlertesASupprimer) {
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

	@Override
	public void receptAlerte(Alerte a) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPrixReserveAtteint(Enchere ench) {
		return ench.isPrixReserveAtteint();
	}
}
