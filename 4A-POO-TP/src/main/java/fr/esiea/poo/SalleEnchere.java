package fr.esiea.poo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe systeme (singleton) représentant le systeme d'enchere central
 * 
 * @author Thibaut
 * 
 */
public class SalleEnchere
{
	private static SalleEnchere instance;
	private ArrayList<Enchere> encherePubliees, enchereCrees, enchereAnnulees;

	private SalleEnchere()
	{
		this.encherePubliees = new ArrayList<>();
		this.enchereCrees = new ArrayList<>();
	}

	public static SalleEnchere getInstance()
	{
		if (instance == null)
		{
			instance = new SalleEnchere();
		}
		return instance;
	}

	public List<Enchere> getEncherePubliees()
	{
		return encherePubliees;
	}

	public List<Enchere> getEnchereCrees()
	{
		return this.enchereCrees;
	}

	public List<Enchere> getEnchereAnnulees()
	{
		return this.enchereAnnulees;
	}

}
