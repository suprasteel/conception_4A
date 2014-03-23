package fr.esiea.poo;

public class Offre
{
	
	private double prix = 0;
	private int id  = 0;
	private static int numOffre = 0;

	public Offre(double _prix)
	{
		numOffre++;
		id = numOffre;
		prix = _prix;
		// TODO Auto-generated constructor stub
	}
	
	public double getPrix(){
		return this.prix;
	}

}
