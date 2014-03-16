package fr.esiea.poo;

import org.junit.Before;
import org.junit.Test;

public class AcheteurTest
{
	private Acheteur ach;

	@Before
	public void setUp()
	{
		this.ach = new User();
	}
	
	@Test
	public void testEmettreOffre()
	{
		this.ach.emettreOffre(new Offre(10.95));
	}
}
