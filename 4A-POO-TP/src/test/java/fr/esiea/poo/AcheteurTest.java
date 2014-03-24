package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

public class AcheteurTest
{
	private Acheteur ach;
	private Enchere ench;
	private Produit obj;
	private double prixMin, prixOffre;
	private Date dateLimite;

	// TODO ajouter controle sur ajout d'offre pas encore publiées

	@Before
	public void setUp()
	{
		this.ach = new User("a", "b", "c");
		this.prixMin = 0.99;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
		prixOffre = 25;
	}

	// Un utilisateur dans son rôle d'Acheteur peut émettre des offres pour une
	// enchère publiée
	// par un autre utilisateur que lui.

	/**
	 * Test du cas nominal de l'emission d'offre
	 * 
	 * @throws ForbiddenBidOperation
	 * @throws InsuffisantOfferPrice 
	 */
	@Test
	public void testEmettreOffreOK() throws ForbiddenBidOperation, InsuffisantOfferPrice
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);

		Offre o = this.ach.emettreOffre(ench, prixOffre);

		Assert.assertEquals(ench.getLastOffre(), o);
	}

	/**
	 * Test d'emission d'offre par le vendeur sur son enchère
	 * 
	 * @throws ForbiddenBidOperation
	 * @throws InsuffisantOfferPrice 
	 */
	@Test (expected=ForbiddenBidOperation.class)
	public void testEmettreOffreKOVendeurEqualsAcheteur() throws ForbiddenBidOperation, InsuffisantOfferPrice
	{
		Vendeur vendeur = (Vendeur) this.ach;
		Enchere enchere = vendeur.creerEnchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);

		this.ach.emettreOffre(enchere, prixOffre);
	}
	
	/**
	 * Test d'emission d'offre par le vendeur sur son enchère
	 * 
	 * @throws ForbiddenBidOperation
	 * @throws InsuffisantOfferPrice 
	 */
	@Test (expected=InsuffisantOfferPrice.class)
	public void testEmettreOffreKOPrixMin() throws ForbiddenBidOperation, InsuffisantOfferPrice
	{
		Enchere enchere = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);

		prixOffre = 5;

		this.ach.emettreOffre(enchere, prixOffre);
	}

	/**
	 * Test de la fonction de récupération du prix de réserve point de vu
	 * acheteur
	 * 
	 * @throws ForbiddenBidOperation
	 * @throws InsuffisantOfferPrice 
	 */
	@Test
	public void getPrixReserveAtteint() throws ForbiddenBidOperation, InsuffisantOfferPrice
	{
		Enchere ench = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);
		this.ach.emettreOffre(ench, 13);

		boolean reached = ach.isPrixReserveAtteint(ench);
		Assert.assertFalse(reached);

		this.ach.emettreOffre(ench, prixOffre);
		reached = ach.isPrixReserveAtteint(ench);
		Assert.assertTrue(reached);
	}
}
