package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;

import fr.esiea.poo.Alerte.TypeAlerte;
import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

/**
 * Classe
 * */
public class User extends ObservateurEncheres implements Acheteur, Vendeur {
	private ArrayList<Enchere> userEnchere = new ArrayList<Enchere>();
	private ArrayList<Offre> userOffres = new ArrayList<>();
	private String login, nom, prenom;
	/** Pour les TDD */
	private Alerte derniereAlerteRecue;

	/** Singleton qui centralise les enchères */
	private SalleEnchere salesHouse = SalleEnchere.getInstance();

	/** Constructeur d'un utilisateur */
	public User(String login, String nom, String prenom) {
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Créer une enchère à partir d'un produit et d'une date de fin d'enchère.<br>
	 * Cette méthode abonne automatiquement l'user aux alertes de surrenchères.
	 * */
	@Override
	public Enchere creerEnchere(Produit _produit, Date _dateLimite) {
		Enchere enchere = new Enchere(_produit, _dateLimite);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);
		ArrayList<TypeAlerte> al = new ArrayList<>();
		al.add(TypeAlerte.SURENCHERE);
		this.inscriptionAlerte(enchere, al);

		return enchere;
	}

	/**
	 * Créer une enchère à partir d'un produit et d'une date de fin d'enchère.<br>
	 * Cette méthode abonne automatiquement l'user aux alertes de surrenchères.
	 * */
	@Override
	public Enchere creerEncherePrixMin(Produit obj, Date dateLimite,
			double prixMin) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, 0);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);
		ArrayList<TypeAlerte> al = new ArrayList<>();
		al.add(TypeAlerte.SURENCHERE);
		this.inscriptionAlerte(enchere, al);

		return enchere;
	}

	/**
	 * Créer une enchère à partir d'un produit et d'une date de fin d'enchère.<br>
	 * Cette méthode abonne automatiquement l'user aux alertes de surrenchères.
	 * */
	@Override
	public Enchere creerEncherePrixReserve(Produit obj, Date dateLimite,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, 0, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);
		ArrayList<TypeAlerte> al = new ArrayList<>();
		al.add(TypeAlerte.SURENCHERE);
		this.inscriptionAlerte(enchere, al);
		return enchere;
	}

	/**
	 * Créer une enchère à partir d'un produit et d'une date de fin d'enchère.<br>
	 * Cette méthode abonne automatiquement l'user aux alertes de surrenchères.
	 * */
	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);
		ArrayList<TypeAlerte> al = new ArrayList<>();
		al.add(TypeAlerte.SURENCHERE);
		this.inscriptionAlerte(enchere, al);
		return enchere;
	}

	/**
	 * Permet de récupérer la dernière alerte reçue par un utilisateur (utile
	 * pour les TDD sur alertes)
	 */
	public Alerte getDerniereAlerte() {
		return this.derniereAlerteRecue;
	}

	/**
	 * Lance une exception de type ForbiddenBidOperation si l'offre n'est pas
	 * possible
	 */
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

	/** Permet de rendre accessible à tous les Users l'enchère dans salesHouse */
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

	/**
	 * Lance une exception de type ForbiddenBidOperation si l'annulation n'est pas
	 * possible (prix de reserve atteint ou enchere d'un autre user).
	 */
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

	/** Action à réaliser une fois l'alerte reçue. Pour aller plus loin il serait utile d'utiliser un pattern foncteur. */
	@Override
	public void receptAlerte(Alerte a) {
		System.out.println(this.login + " Alerte reçue : " + a.getAlerteType()
				+ " " + a.getEnchere().toString());
		derniereAlerteRecue = a;
	}

	/** Obtenir l'information de prix de reserve atteint pour une enchère */
	@Override
	public boolean isPrixReserveAtteint(Enchere ench) {
		return ench.isPrixReserveAtteint();
	}

	@Override
	public String toString() {
		return "User [userEnchere=" + userEnchere + ", userOffres="
				+ userOffres + ", login=" + login + ", nom=" + nom
				+ ", prenom=" + prenom + ", salesHouse=" + salesHouse + "]";
	}

}
