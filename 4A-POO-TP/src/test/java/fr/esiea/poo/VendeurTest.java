package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendeurTest
{
	private Vendeur vendeur;
	private Objet obj;
	private Date dateLimite;
	private double prixMin, prixReserve;

	@Before
	public void setUp()
	{
		this.vendeur = new User();
		this.obj = new Objet("1234A", "Obj test");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
		this.prixMin = 0.99;
		this.prixReserve = 49.99;
	}

	@Test
	public void testCreerEnchereSimple()
	{
		Enchere ench = new Enchere(obj, dateLimite);

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite);

		Assert.assertEquals(ench, e);
	}

	@Test
	public void testCreerEncherePrixMin()
	{
		Enchere ench = new Enchere(obj, dateLimite);
		ench.setPrixMin(10);

		Enchere e = this.vendeur.creerEncherePrixMin(obj, dateLimite, prixMin);

		Assert.assertEquals(ench, e);
	}
	
	@Test
	public void testCreerEncherePrixReserve()
	{
		Enchere ench = new Enchere(obj, dateLimite);
		ench.setPrixReserve(11.49);

		Enchere e = this.vendeur.creerEncherePrixReserve(obj, dateLimite, prixReserve);

		Assert.assertEquals(ench, e);
	}
	
	@Test
	public void testCreerEnchereComplet()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		Assert.assertEquals(ench, e);
	}
	
	@Test
	public void testPublierEnchere()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		this.vendeur.publierEnchere(ench);
		
		//TODO rajouter un controle sur l'ajout aux liste de publiée
		//TODO idem pour les autres methodes
		Assert.assertEquals(ench.getEtat(), Etat.PUBLIEE);
	}
}
