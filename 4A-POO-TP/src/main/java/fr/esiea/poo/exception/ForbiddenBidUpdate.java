package fr.esiea.poo.exception;

public class ForbiddenBidUpdate extends Exception
{

	@Override
	public String getMessage()
	{
		return "Error updating Bid. Probably not your bid";
	}
}
