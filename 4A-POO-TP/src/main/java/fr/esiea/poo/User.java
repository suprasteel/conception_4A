package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;

import fr.esiea.poo.exception.ForbiddenBidUpdate;

public class User implements Acheteur, Vendeur
{
	ArrayList<Enchere> userEnchere = new ArrayList<Enchere>();
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
		salesHouse.getEnchereCrees().add(enchere);
		
		return enchere;
	}

	@Override
	public Enchere creerEncherePrixMin(Produit obj, Date dateLimite, double prixMin)
	{
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, 0);
		userEnchere.add(enchere);
		salesHouse.getEnchereCrees().add(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEncherePrixReserve(Produit obj, Date dateLimite, double prixReserve)
	{
		Enchere enchere = new Enchere(obj, dateLimite, 0, prixReserve);
		userEnchere.add(enchere);
		salesHouse.getEnchereCrees().add(enchere);

		return enchere;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin, double prixReserve)
	{
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, prixReserve);
		userEnchere.add(enchere);
		salesHouse.getEnchereCrees().add(enchere);

		return enchere;
	}

	@Override
	public void publierEnchere(Enchere ench) throws ForbiddenBidUpdate
	{
		if(!userEnchere.contains(ench))
		{
			throw new ForbiddenBidUpdate();
		}else
		{
			ench.setEtat(Etat.PUBLIEE);
			salesHouse.getEnchereCrees().remove(ench);
			salesHouse.getEncherePubliees().add(ench);
		}
	}

	@Override
	public void annulerEnchere(Enchere ench) throws ForbiddenBidUpdate
	{
		if(!userEnchere.contains(ench))
		{
			throw new ForbiddenBidUpdate();
		}else
		{
			ench.setEtat(Etat.ANNULEE);
			salesHouse.getEncherePubliees().remove(ench);
			salesHouse.getEnchereAnnulees().add(ench);
		}
	}

	@Override
	public void emettreOffre(Offre offre)
	{
		// TODO Auto-generated method stub

	}
}
