package fr.esiea.poo;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest
{
	
	private User vendeur;
	private Enchere e;
	private double prixMin = 10;
	private double prixReserve = 100;
	
//	private Seller seller;
//	private Buyer buyer;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass()
	{
		System.out.println("tearDownAfterClass appel une fois a la fin");
	}

	@Before
	public void setUp() throws Exception
	{
		vendeur = new User("login1", "nom1", "prenom1");
		vendeur.creerEnchere(new Produit("Un produit", "Description de un produit"), new Date(), prixMin, prixReserve);
	}

	@After
	public void tearDown() throws Exception
	{
		System.out.println("tearDown appel apres chaque methode de test");
	}

	@Test
	public void test1()
	{
		System.out.println("Test des tests");
	}

	@Test
	public void test2()
	{
		System.out.println("test2 des tests");
	}
	
	
}
