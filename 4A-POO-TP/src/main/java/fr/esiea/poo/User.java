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
		ArrayList<TypeAlerte> al = new ArrayList<>();
		al.add(TypeAlerte.SURENCHERE);
		this.inscriptionAlerte(enchere, al);

		return enchere;
	}

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

	public Alerte getDerniereAlerte() {
		return this.derniereAlerteRecue;
	}

	@Override
	public Offre emettreOffre(Enchere ench, double prix)
			throws ForbiddenBidOperation, InsuffisantOfferPrice {
		if (this.userEnchere.contains(ench)) {
			throw new ForbiddenBidOperation(
					"Vous ne pouvez pas enchérir sur une enchere qui vous appartient!");
		}
		if (prix < ench.getPrixMin()) {
			throw new InsuffisantOfferPrice(InsuffisantOfferPrice.INF_PRIX_MIN);
		}
		Offre offre = new Offre(this, prix);
		userOffres.add(offre);
		ench.addOffre(offre);

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

	@Override
	public void receptAlerte(Alerte a) {
		System.out.println(this.login + " Alerte reçue : " +a.getAlerteType()+" "+ a.getEnchere().toString());
		derniereAlerteRecue = a;
	}

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
