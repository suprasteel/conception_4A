package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.esiea.poo.exception.ForbiddenBidCancellation;
import fr.esiea.poo.exception.ForbiddenBidUpdate;

public class VendeurTest
{
	private Vendeur vendeur;
	private Produit obj;
	private Date dateLimite;
	private double prixMin, prixReserve;
	private static SalleEnchere salesHouse;

	@BeforeClass
	public static void setUpBeforeClass()
	{
		salesHouse = SalleEnchere.getInstance();
	}

	@Before
	public void setUp()
	{
		this.vendeur = new User("testUser", "User", "Test");
		this.obj = new Produit("1234A", "Obj test");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
		this.prixMin = 0.99;
		this.prixReserve = 49.99;
	}

	/**
	 * Test qui crée un enchère simple, avec prix de réserve et prix minimum par
	 * défaut
	 */
	@Test
	public void testCreerEnchereSimple()
	{
		Enchere ench = new Enchere(obj, dateLimite);

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec un prix minimum définit
	 */
	@Test
	public void testCreerEncherePrixMin()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, 0);

		Enchere e = this.vendeur.creerEncherePrixMin(obj, dateLimite, prixMin);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec un prix de réserve définit
	 */
	@Test
	public void testCreerEncherePrixReserve()
	{
		Enchere ench = new Enchere(obj, dateLimite, 0, prixReserve);

		Enchere e = this.vendeur.creerEncherePrixReserve(obj, dateLimite, prixReserve);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec tout les attributs définits
	 */
	@Test
	public void testCreerEnchereComplet()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test de la fonction de publication d'une enchere dans le système
	 */
	@Test
	public void testPublierEnchere()
	{
		Enchere ench = this.vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		try
		{
			this.vendeur.publierEnchere(ench);
		} catch (ForbiddenBidUpdate e)
		{
			System.err.println(e.getMessage());
		}

		Assert.assertEquals(ench.getEtat(), Etat.PUBLIEE);
		Assert.assertFalse(salesHouse.getEnchereCrees().contains(ench));
		Assert.assertTrue(salesHouse.getEncherePubliees().contains(ench));
	}

	/**
	 * Test de la fonction annulation d'une enchere dans le système
	 */
	@Test
	public void testAnnulerEnchereOK()
	{
		Enchere ench = vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		try
		{
			vendeur.publierEnchere(ench);
			vendeur.annulerEnchere(ench);
		} catch (ForbiddenBidUpdate | ForbiddenBidCancellation e)
		{
			System.err.println(e.getMessage());
		}

		Assert.assertEquals(ench.getEtat(), Etat.ANNULEE);
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(ench));
		Assert.assertTrue(salesHouse.getEnchereAnnulees().contains(ench));
	}

	/**
	 * Test de la fonction annulation d'une enchere dans le système sur une
	 * enchere possedant deja des offres
	 * @throws ForbiddenBidCancellation 
	 */
	@Test(expected = ForbiddenBidCancellation.class)
	public void testAnnulerEnchereKO() throws ForbiddenBidCancellation
	{
		Enchere ench = vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		try
		{
			vendeur.publierEnchere(ench);
			ench.addOffre(new Offre(50));
			vendeur.annulerEnchere(ench);
		} catch (ForbiddenBidUpdate  e)
		{
			System.err.println(e.getMessage());
		}

//		Assert.assertEquals(ench.getEtat(), Etat.ANNULEE);
//		Assert.assertFalse(salesHouse.getEncherePubliees().contains(ench));
//		Assert.assertTrue(salesHouse.getEnchereAnnulees().contains(ench));
	}

	// (expected=Exception.class)
}
