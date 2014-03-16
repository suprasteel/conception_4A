package fr.esiea.poo;

import java.util.Date;

public class Enchere
{
	Objet objet;
	Date dateLimite;
	double prixMin, prixReserve;
	Etat etat;

	public Enchere(Objet obj, Date dateLimite)
	{
		// TODO Auto-generated constructor stub
	}

	public Enchere(Objet obj, Date dateLimite, double prixMin, double prixReserve)
	{
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
