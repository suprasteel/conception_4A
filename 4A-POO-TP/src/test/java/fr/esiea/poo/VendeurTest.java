package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

public class VendeurTest
{
	private User vendeur1;
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
		this.vendeur1 = new User("testUser", "User", "Test");
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

		Enchere e = this.vendeur1.creerEnchere(obj, dateLimite);

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

		Enchere e = this.vendeur1.creerEncherePrixMin(obj, dateLimite, prixMin);

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

		Enchere e = this.vendeur1.creerEncherePrixReserve(obj, dateLimite, prixReserve);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test qui crée un enchère avec tout les attributs définits
	 * "Un utilisateur peut creer une enchere"
	 */
	@Test
	public void testCreerEnchereComplet()
	{
		Enchere ench = new Enchere(obj, dateLimite, prixMin, prixReserve);

		Enchere e = this.vendeur1.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		Assert.assertEquals(ench, e);
		Assert.assertTrue(salesHouse.getEnchereCrees().contains(e));
		Assert.assertFalse(salesHouse.getEncherePubliees().contains(e));
	}

	/**
	 * Test de la fonction de publication d'une enchere dans le système
	 * "Un utilisateur peut publier une enchere"
	 */
	@Test
	public void testPublierEnchere()
	{
		Enchere ench = this.vendeur1.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		try
		{
			this.vendeur1.publierEnchere(ench);
		} catch (ForbiddenBidOperation e)
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
		Enchere ench = vendeur1.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		try
		{
			vendeur1.publierEnchere(ench);
			vendeur1.annulerEnchere(ench);
		} catch (ForbiddenBidOperation e)
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
	 * 
	 * @throws ForbiddenBidCancellation
	 */
	@Test(expected = ForbiddenBidOperation.class)
	public void testAnnulerEnchereKO() throws ForbiddenBidOperation
	{
		Enchere ench = vendeur1.creerEnchere(obj, dateLimite, prixMin, prixReserve);

		vendeur1.publierEnchere(ench);
		try {
			//Prix de reserve à 49,99
			ench.addOffre(new Offre(null, 50));
		} catch (InsuffisantOfferPrice e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vendeur1.annulerEnchere(ench);
	}
	
	/**
	 * Test de l'interdictiction de surrenchier sur ses enchère, meme en etant aussi acheteur.
	 * 
	 * @throws ForbiddenBidCancellation
	 */
	@Test(expected = ForbiddenBidOperation.class)
	public void testEncherirMonEnchereKO() throws ForbiddenBidOperation
	{
		Enchere ench = vendeur1.creerEnchere(obj, dateLimite, prixMin, prixReserve);
		vendeur1.publierEnchere(ench);
		vendeur1.emettreOffre(ench, 50.0);
		
	}
}
