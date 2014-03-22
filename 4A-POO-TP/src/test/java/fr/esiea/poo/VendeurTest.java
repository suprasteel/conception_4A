package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		// this.salesHouse = SalleEnchere.getInstance();
	}

	/**
	 * Test qui crée un enchère simple, avec prix de réserve et prix minimum par
	 * défaut
	 */
	@Test
	public void testCreerEnchereSimple()
	{
		Enchere ench = new Enchere(obj, dateLimite);
		// int nbExpected = this.salesHouse.getEncherePubliee().size();

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite);
		// int nbActual = this.salesHouse.getEncherePubliee().size();

		Assert.assertEquals(ench, e);
		Assert.assertTrue(this.salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(this.salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec un prix minimum définit
	 */
	@Test
	public void testCreerEncherePrixMin()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, 0);
		// int nbExpected = this.salesHouse.getEncherePubliee().size();

		Enchere e = this.vendeur.creerEncherePrixMin(obj, dateLimite, prixMin);
		// int nbActual = this.salesHouse.getEncherePubliee().size();

		Assert.assertEquals(ench, e);
		Assert.assertTrue(this.salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(this.salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec un prix de réserve définit
	 */
	@Test
	public void testCreerEncherePrixReserve()
	{
		Enchere ench = new Enchere(obj, dateLimite, 0, prixReserve);
		// int nbExpected = this.salesHouse.getEncherePubliee().size();

		Enchere e = this.vendeur.creerEncherePrixReserve(obj, dateLimite, prixReserve);
		// int nbActual = this.salesHouse.getEncherePubliee().size();

		Assert.assertEquals(ench, e);
		Assert.assertTrue(this.salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(this.salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec tout les attributs définits
	 */
	@Test
	public void testCreerEnchereComplet()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);
		// int nbExpected = this.salesHouse.getEncherePubliee().size();

		Enchere e = this.vendeur.creerEnchere(obj, dateLimite, prixMin, prixReserve);
		// int nbActual = this.salesHouse.getEncherePubliee().size();

		Assert.assertEquals(ench, e);
		Assert.assertTrue(this.salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(this.salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test de la fonction de publication d'une enchere dans le système
	 */
	@Test
	public void testPublierEnchere()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		this.vendeur.publierEnchere(ench);

		// TODO rajouter un controle sur l'ajout aux liste de publiée
		// TODO idem pour les autres methodes
		Assert.assertEquals(ench.getEtat(), Etat.PUBLIEE);
		Assert.assertFalse(this.salesHouse.getEnchereCrees().contains(ench));
		Assert.assertTrue(this.salesHouse.getEncherePubliees().contains(ench));
	}

	/**
	 * Test de la fonction annulation d'une enchere dans le système
	 */
	@Test
	public void testAnnulerEnchere()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		this.vendeur.annulerEnchere(ench);

		Assert.assertEquals(ench.getEtat(), Etat.ANNULEE);
		Assert.assertFalse(this.salesHouse.getEncherePubliees().contains(ench));
		Assert.assertTrue(this.salesHouse.getEnchereAnnulees().contains(ench));
	}
}
