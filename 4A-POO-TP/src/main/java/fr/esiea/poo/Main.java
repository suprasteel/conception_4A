package fr.esiea.poo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.esiea.poo.Alerte.TypeAlerte;
import fr.esiea.poo.exception.ForbiddenBidOperation;
import fr.esiea.poo.exception.InsuffisantOfferPrice;

/**
 * Classe main modelisant le systeme A remplacer eventuellement par un singleton
 * ?
 * 
 * @author Thibaut
 * 
 */
public class Main {

	public static void main(String[] args) {
		User bob = new User("bobUser", "Boby", "Bob");
		User lea = new User("lea123", "Martin", "Lea");
		User toto = new User("toto99", "Jean", "Toto");

		/** Centralisation des enchères */
		List<Enchere> encheres = new ArrayList<>();

		/** Liste des alertes auxquelles s'inscrire */
		List<TypeAlerte> typeAlerteList = new ArrayList<TypeAlerte>();
		typeAlerteList.add(TypeAlerte.ANNULATION);
		typeAlerteList.add(TypeAlerte.FIN_ENCHERE);
		typeAlerteList.add(TypeAlerte.RESERVE_ATTEINTE);
		typeAlerteList.add(TypeAlerte.SURENCHERE);

		encheres.add(bob.creerEnchere(new Produit("Chaise",
				"Objet destiné à s'asseoir..."), Date.valueOf("2015-12-12"),
				10.00, 100.00));// Bob crée une enchere sur sa
		// chaise, 10 min, reserve à 100
		/**
		 * Lea s'inscrit pour recevoir des alertes sur cette chaise, elle veut
		 * toutes les notifications
		 */
		lea.inscriptionAlerte(encheres.get(0), typeAlerteList);

		/** Toto essaye une offre */
		try {
			toto.emettreOffre(encheres.get(0), 15.50);
		} catch (InsuffisantOfferPrice | ForbiddenBidOperation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
