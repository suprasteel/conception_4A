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
		this.enchereAnnulees = new ArrayList<>();
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

	public void creerEnchere(Enchere enchere)
	{
		instance.enchereCrees.add(enchere);
	}

	public void publierEnchere(Enchere ench)
	{
		instance.enchereCrees.remove(ench);
		instance.encherePubliees.add(ench);
	}

	public void annulerEnchere(Enchere ench)
	{
		instance.encherePubliees.remove(ench);
		instance.enchereAnnulees.add(ench);
	}

}
