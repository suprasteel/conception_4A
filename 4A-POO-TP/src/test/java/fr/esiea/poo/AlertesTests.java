package fr.esiea.poo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.esiea.poo.exception.ForbiddenBidOperation;

public class AlertesTests {

	private Vendeur vend;
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
		this.vend = new User("d", "e", "f");
		this.prixMin = 0.99;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		this.dateLimite = cal.getTime();
		prixOffre = 25;
		ench = new Enchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);
	}

	// Un utilisateur dans son rôle d'Acheteur peut émettre des offres pour une
	// enchère publiée
	// par un autre utilisateur que lui.

	/**
	 * Test de la reception de l'alerte par l'user
	 * 
	 * @throws ForbiddenBidOperation
	 */
	@Test
	public void testReceptAlertOK() throws ForbiddenBidOperation
	{
		ench = vend.creerEnchere(new Produit("idp", "desc"), dateLimite, 11.0, 15.5);
		Offre o = this.ach.emettreOffre(ench, prixOffre);

		Assert.assertEquals(ench.getNumEnchere(), ((User)vend).getDerniereAlerte().getEnchere().getNumEnchere());
	}

}
