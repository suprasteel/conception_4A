package fr.esiea.poo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest
{
	
	private User user;
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
		System.out.println("setUp appel avant chaque methode de test");
	}

	@After
	public void tearDown() throws Exception
	{
		System.out.println("tearDown appel apres chaque methode de test");
	}

	@Test
	public void test1()
	{
		System.out.println("Appel methode test1");
	}

	@Test
	public void test2()
	{
		System.out.println("Appel methode test2");
	}
}
