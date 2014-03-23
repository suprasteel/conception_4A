package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;

import fr.esiea.poo.ObjetAObserver;
import fr.esiea.poo.ObservateurEncheres;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

public class Enchere extends ObjetAObserver
{
	private Produit produit;
	private Date dateLimite;
	private double prixMin, prixReserve;
	// CREE, PUBLIEE, ANNULEE, TERMINEE
	private Etat etat;
	private ArrayList<Offre> listOffres = new ArrayList<Offre>();

	public Enchere(Produit pdt, Date dateLimite)
	{
		this.produit = pdt;
		this.dateLimite = dateLimite;
		prixMin = prixReserve = 0;
		etat = Etat.CREE;
	}

	public boolean addOffre(Offre o) throws InsuffisantOfferPrice
	{
		boolean succes = false;
		if (listOffres.isEmpty())
		{
			if (o.getPrix() > prixMin)
			{
				listOffres.add(o);
				succes = true;
			}
			else {
				throw new InsuffisantOfferPrice(InsuffisantOfferPrice.INF_PRIX_MIN);
			}
		} else if (getLastOffre().getPrix() < o.getPrix())
		{
			listOffres.add(o);
			succes = true;
		}
		else {
			throw new InsuffisantOfferPrice(InsuffisantOfferPrice.INF_OFFRE_PRECEDENTE);
		}
		notifier(new Alerte(Alerte.TypeAlerte.SURENCHERE, this));
		return succes;
	}

	/**
	 * Retourne la derniere offre emise sur cette enchere
	 * 
	 * @return last offer
	 */
	public Offre getLastOffre()
	{
		if (listOffres.isEmpty())
		{
			return null;
		}

		return listOffres.get(listOffres.size() - 1);
	}

	public Enchere(Produit pdt, Date dateLimite, double prixMin, double prixReserve)
	{
		super();
		this.produit = pdt;
		this.dateLimite = dateLimite;
		this.prixMin = prixMin;
		this.prixReserve = prixReserve;
		etat = Etat.CREE;
	}

	public void setPrixMin(double prixMin)
	{
		this.prixMin = prixMin;
	}

	public void setPrixReserve(double prixReserve)
	{
		this.prixReserve = prixReserve;
	}

	public Etat getEtat()
	{
		return this.etat;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateLimite == null) ? 0 : dateLimite.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prixMin);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prixReserve);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((produit == null) ? 0 : produit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Enchere))
		{
			return false;
		}
		Enchere other = (Enchere) obj;
		if (dateLimite == null)
		{
			if (other.dateLimite != null)
			{
				return false;
			}
		} else if (!dateLimite.equals(other.dateLimite))
		{
			return false;
		}
		if (etat != other.etat)
		{
			return false;
		}
		if (Double.doubleToLongBits(prixMin) != Double.doubleToLongBits(other.prixMin))
		{
			return false;
		}
		if (Double.doubleToLongBits(prixReserve) != Double.doubleToLongBits(other.prixReserve))
		{
			return false;
		}
		if (produit == null)
		{
			if (other.produit != null)
			{
				return false;
			}
		} else if (!produit.equals(other.produit))
		{
			return false;
		}
		return true;
	}

	public void setEtat(Etat publiee)
	{
		this.etat = publiee;
	}

	public boolean hasOffers()
	{
		return !listOffres.isEmpty();
	}

	public boolean isPrixReserveAtteint()
	{
		if (getLastOffre().getPrix() > prixReserve)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public double getPrixMin()
	{
		return prixMin;
	}

}
