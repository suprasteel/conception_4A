package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;

import fr.esiea.poo.exception.ForbiddenBidOperation;

public class User implements Acheteur, Vendeur, ObservateurEncheres
{
	private ArrayList<Enchere> userEnchere = new ArrayList<Enchere>();
	private ArrayList<Offre> userOffres = new ArrayList<>();
	private String login, nom, prenom;
	private SalleEnchere salesHouse = SalleEnchere.getInstance();

	public User(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite)
	{
		Enchere enchere = new Enchere(obj, dateLimite);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEncherePrixMin(Produit obj, Date dateLimite, double prixMin)
	{
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, 0);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEncherePrixReserve(Produit obj, Date dateLimite, double prixReserve)
	{
		Enchere enchere = new Enchere(obj, dateLimite, 0, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin, double prixReserve)
	{
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, prixReserve);
		userEnchere.add(enchere);
		salesHouse.creerEnchere(enchere);

		return enchere;
	}

	@Override
	public Offre emettreOffre(Enchere ench, double prix) throws ForbiddenBidOperation
	{
		if (this.userEnchere.contains(ench))
		{
			throw new ForbiddenBidOperation("Vous ne pouvez pas enchérir sur une enchere qui vous appartient!");
		}
		if (prix < ench.getPrixMin())
		{
			throw new ForbiddenBidOperation("Le prix de l'offre est inférieur au prix minimum de l'enchère!");
		}
		Offre offre = new Offre(this, prix);
		userOffres.add(offre);
		ench.addOffre(offre);

		return offre;
	}

	@Override
	public void publierEnchere(Enchere ench) throws ForbiddenBidOperation
	{
		if (!userEnchere.contains(ench))
		{
			throw new ForbiddenBidOperation("Error updating Bid. Probably not your bid");
		} else
		{
			ench.setEtat(Etat.PUBLIEE);
			salesHouse.publierEnchere(ench);
		}
	}

	@Override
	public void annulerEnchere(Enchere ench) throws ForbiddenBidOperation 
	{
		if (!userEnchere.contains(ench))
		{
			throw new ForbiddenBidOperation("Error updating Bid. Probably not your bid.");
		}
		if (ench.hasOffers())
		{
			throw new ForbiddenBidOperation("Impossible d'annuler cette enchère. Le prix de réserve a déjà été atteint.");
		}
		ench.setEtat(Etat.ANNULEE);

		salesHouse.annulerEnchere(ench);
	}

	@Override
	public void receptAlerte(Alerte a)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPrixReserveAtteint(Enchere ench)
	{
		return ench.isPrixReserveAtteint();
	}
}
