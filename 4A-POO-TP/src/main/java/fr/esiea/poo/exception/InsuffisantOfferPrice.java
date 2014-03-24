package fr.esiea.poo.exception;

public class InsuffisantOfferPrice extends Exception {
	/**
	 * Générated serial UID
	 */
	private static final long serialVersionUID = 4932550467350867507L;

	/**
	 * Messages standards
	 * */
	public static final String INF_OFFRE_PRECEDENTE = "nouvelle offre inférieure à la derniere offre émise";
	public static final String INF_PRIX_MIN = "nouvelle offre inférieure au prix minimum";

	/**
	 * Crée une nouvelle instance de NombreNonValideException
	 */
	public InsuffisantOfferPrice() {
	}

	/**
	 * Crée une nouvelle instance de InsuffisantOfferPrice
	 * 
	 * @param message
	 *            Le message détaillant exception
	 */
	public InsuffisantOfferPrice(String message) {
		super(message);
	}

	/**
	 * Crée une nouvelle instance de InsuffisantOfferPrice
	 * 
	 * @param cause
	 *            L'exception à l'origine de cette exception
	 */
	public InsuffisantOfferPrice(Throwable cause) {
		super(cause);
	}

	/**
	 * Crée une nouvelle instance de InsuffisantOfferPrice
	 * 
	 * @param message
	 *            Le message détaillant exception
	 * @param cause
	 *            L'exception à l'origine de cette exception
	 */
	public InsuffisantOfferPrice(String message, Throwable cause) {
		super(message, cause);
	}

}
