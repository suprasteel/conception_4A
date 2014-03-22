package fr.esiea.poo;

import java.util.HashSet;

	/**
	 * Interface d'objet observé
	 */
	public class ObjetAObserver {
		
	    private HashSet<ObservateurEncheres> listeObservateurs = 
	            new HashSet<ObservateurEncheres>();
	    
	    /**
	     * Ajouter un observateur de la liste
	     * @param pObservateur
	     */
	    public void ajouterObs(ObservateurEncheres pObservateur) {
	        listeObservateurs.add(pObservateur);
	    }
	    
	    /**
	     * Supprimer un observateur de la liste
	     * @param pObservateur
	     */
	    public void supprimerObs(ObservateurEncheres pObservateur) {
	        listeObservateurs.remove(pObservateur);
	    }
	    
	    /**
	     * On met chaque utilisateur à jour
	     */
	    protected void notifier() {
	        for(ObservateurEncheres lObservateur : listeObservateurs) {
	            lObservateur.receptNvlEnchere();
	        }
	    }
	}

