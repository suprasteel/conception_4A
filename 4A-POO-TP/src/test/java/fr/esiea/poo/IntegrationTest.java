package fr.esiea.poo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.poo.Alerte.TypeAlerte;
import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

/**
 * Classe de executant un pseudo test d'intégration
 * 
 * @author Thibaut
 * 
 */
public class IntegrationTest
{
	private User bob, lea, toto;

	private Produit produitToto;

	Date dateLimiteToto;

	// On récupère le systeme centrale des enchères
	private SalleEnchere salesHouse;

	@Before
	public void setUp()
	{
		bob = new User("bobUser", "Boby", "Bob");
		lea = new User("lea123", "Martin", "Lea");
		toto = new User("toto99", "Jean", "Toto");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimiteToto = cal.getTime();

		produitToto = new Produit("pdt1", "Un joli Picasso!");

		salesHouse = SalleEnchere.getInstance();
	}

	@Test
	public void integrationSystemeTest() throws ForbiddenBidOperation, InsuffisantOfferPrice
	{
		//Toto crée une enchère, on s'assure que le système la prend en compte
		Enchere enchereToto = toto.creerEnchere(produitToto, dateLimiteToto, 95, 150000);
		assertTrue(salesHouse.getEnchereCrees().contains(enchereToto));
		
		//Toto publie l'enchère
		((Vendeur)toto).publierEnchere(enchereToto);
		assertFalse(salesHouse.getEnchereCrees().contains(enchereToto));
		assertTrue(salesHouse.getEncherePubliees().contains(enchereToto));
		
		//Bob et Lea enchérissent
		((Acheteur)bob).emettreOffre(enchereToto, 150);
		bob.inscriptionAlerte(enchereToto, TypeAlerte.SURENCHERE);
		((Acheteur)lea).emettreOffre(enchereToto, 200);
		lea.inscriptionAlerte(enchereToto, TypeAlerte.RESERVE_ATTEINTE);
		Assert.assertEquals(bob.getDerniereAlerte().getAlerteType(), TypeAlerte.SURENCHERE);
		((Acheteur)lea).emettreOffre(enchereToto, 200000);
		Assert.assertEquals(lea.getDerniereAlerte().getAlerteType(), TypeAlerte.RESERVE_ATTEINTE);

	}

}
