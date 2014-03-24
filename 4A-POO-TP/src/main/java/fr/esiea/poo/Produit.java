package fr.esiea.poo;

public class Produit
{
	private String id;
	private String description;
	private User proprietaire;

	public Produit(String _id, String _description)
	{
		this.id = _id;
		this.description = _description;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", description=" + description + "]";
	}
	
	/** Fonction package non demandée dans l'énoncé mais qui permet de définir le proprietaire */
	void setPropietaire(){
		
	}

}
