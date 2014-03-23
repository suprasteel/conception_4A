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

	@Before
	public void setUp()
	{
		this.ach = new User("a", "b", "c");
		this.prixMin = 0.99;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
	}

	@Test
	public void testEmettreOffre()
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite);
		Offre o = new Offre(10.95);
		this.ach.emettreOffre(ench, o);

		Assert.assertEquals(ench.getLastOffre(), o);
	}

	@Test
	public void testEmettreOffre2()
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);
		Offre o = new Offre(10.95);
		this.ach.emettreOffre(ench, o);

		Assert.assertEquals(ench.getLastOffre().getPrix(), o.getPrix(), 0);
	}
}
