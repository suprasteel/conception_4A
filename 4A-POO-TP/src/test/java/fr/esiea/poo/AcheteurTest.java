package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AcheteurTest
{
	private Acheteur ach;
	private Enchere ench;
	private Produit obj;
	private double prixMin;
	private Date dateLimite;
	
	//TODO ajouter controle sur ajout d'offre pas encore publiées

	@Before
	public void setUp()
	{
		this.ach = new User("a", "b", "c");
		this.prixMin = 0.99;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
	}

	//Un utilisateur dans son rôle d'Acheteur peut émettre des offres pour une enchère publiée
//	par un autre utilisateur que lui.
	
	@Test
	public void testEmettreOffre()
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite);
		double prix = 10.95;
		
		Offre o = this.ach.emettreOffre(ench, prix);

		Assert.assertEquals(ench.getLastOffre(), o);
	}

	@Test
	public void testEmettreOffre2()
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);
		
		Offre o = this.ach.emettreOffre(ench, 15.0);

		Assert.assertEquals(ench.getLastOffre().getPrix(), o.getPrix(), 0);
	}
}
