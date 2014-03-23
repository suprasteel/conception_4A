package fr.esiea.poo;

public class Offre
{

	private static int numOffre = 0;

	private double prix = 0;
	private int id = 0;
	private Acheteur ach;

	public Offre(Acheteur ach, double _prix)
	{
		numOffre++;
		id = numOffre;
		prix = _prix;
		this.ach = ach;
	}

	public double getPrix()
	{
		return this.prix;
	}

}
