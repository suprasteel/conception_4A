package fr.esiea.poo;

import java.util.ArrayList;
import java.util.Date;

public class User implements Acheteur, Vendeur, ObservateurEncheres {
	ArrayList<Enchere> userEnchere = new ArrayList<Enchere>();
	private String login, nom, prenom;

	public User(String login, String nom, String prenom) {
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite) {
		Enchere enchere = new Enchere(obj, dateLimite);
		userEnchere.add(enchere);
		return enchere;
	}

	@Override
	public Enchere creerEncherePrixMin(Produit obj, Date dateLimite,
			double prixMin) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, 0);
		userEnchere.add(enchere);
		return enchere;
	}

	@Override
	public Enchere creerEncherePrixReserve(Produit obj, Date dateLimite,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, 0, prixReserve);
		userEnchere.add(enchere);
		return enchere;
	}

	@Override
	public Enchere creerEnchere(Produit obj, Date dateLimite, double prixMin,
			double prixReserve) {
		Enchere enchere = new Enchere(obj, dateLimite, prixMin, prixReserve);
		userEnchere.add(enchere);
		return enchere;
	}

	@Override
	public void publierEnchere(Enchere ench) {
		// TODO Auto-generated method stub

	}

	@Override
	public void annulerEnchere(Enchere ench) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receptAlerte(Alerte a) {
		// TODO Auto-generated method stub

	}

	@Override
	public Offre emettreOffre(Enchere ench, double prix) {
		// TODO Auto-generated method stub
		return null;
	}
}
