package fr.esiea.poo;

import java.util.HashSet;

	/**
	 * Interface d'objet observé
	 */
	public class ObjetAObserver {
		
	    private HashSet<ObservateurEncheres> setObservateurs = 
	            new HashSet<ObservateurEncheres>();
	    
	    /**
	     * Ajouter un observateur de la liste
	     * @param pObservateur
	     */
	    public void ajouterObs(ObservateurEncheres pObservateur) {
	        setObservateurs.add(pObservateur);
	    }
	    
	    /**
	     * Supprimer un observateur de la liste
	     * @param pObservateur
	     */
	    public void supprimerObs(ObservateurEncheres pObservateur) {
	        setObservateurs.remove(pObservateur);
	    }
	    
	    /**
	     * On met chaque utilisateur à jour, il suffit d'appeler notifier() sur l'observé.
	     */
	    protected void notifier(Enchere e) {
	        for(ObservateurEncheres lObservateur : setObservateurs) {
	            lObservateur.receptNvlEnchere(e);
	        }
	    }
	}

