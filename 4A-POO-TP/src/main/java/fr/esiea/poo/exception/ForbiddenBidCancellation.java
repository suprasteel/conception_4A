package fr.esiea.poo.exception;

public class ForbiddenBidCancellation extends Exception
{
	@Override
	public String getMessage()
	{
		return "Impossible d'annuler cette enchère. Le prix de réserve a déjà été atteind";
	}
}
