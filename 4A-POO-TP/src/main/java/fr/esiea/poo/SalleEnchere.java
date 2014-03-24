package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe systeme (singleton) représentant le systeme d'enchere central
 * 
 * @author Thibaut 
 * 
 */
public class SalleEnchere {
	private static SalleEnchere instance;
	private ArrayList<Enchere> encherePubliees, enchereCrees, enchereAnnulees;

	private SalleEnchere() {
		/** Enchères visibles et disponibles aux acheteurs */
		this.encherePubliees = new ArrayList<>();
		/** Enchères non visibles */
		this.enchereCrees = new ArrayList<>();
		/**
		 * Enchère annulées, expirées, etc... Il n'est plus possible de faire
		 * d'offres dessus
		 */
		this.enchereAnnulees = new ArrayList<>();
	}

	public static SalleEnchere getInstance() {
		if (instance == null) {
			instance = new SalleEnchere();
		}
		return instance;
	}

	/** Fournit la date pour les enchère (cette classe est la seule à connaitre le temps) */
	public static Date getDate(){
		return new Date();
	}
	
	public List<Enchere> getEncherePubliees() {
		return encherePubliees;
	}

	public List<Enchere> getEnchereCrees() {
		return this.enchereCrees;
	}

	public List<Enchere> getEnchereAnnulees() {
		return this.enchereAnnulees;
	}

	public void creerEnchere(Enchere enchere) {
		instance.enchereCrees.add(enchere);
	}

	public void publierEnchere(Enchere ench) {
		instance.enchereCrees.remove(ench);
		instance.encherePubliees.add(ench);
	}

	public void annulerEnchere(Enchere ench) {
		instance.encherePubliees.remove(ench);
		instance.enchereAnnulees.add(ench);
	}

}
