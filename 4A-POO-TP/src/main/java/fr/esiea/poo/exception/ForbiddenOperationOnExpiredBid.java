package fr.esiea.poo.exception;

public class ForbiddenOperationOnExpiredBid extends Exception{

	public static final String ENCHERE_EXPIREE = "L'enchere est expirée, impossible de réaliser l'opération";
	public static final String ENCHERE_ANNULEE = "L'enchere est annulée, impossible de réaliser l'opération";
	
	public ForbiddenOperationOnExpiredBid()
	{
		// TODO Auto-generated constructor stub
	}

	public ForbiddenOperationOnExpiredBid(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenOperationOnExpiredBid(Throwable arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenOperationOnExpiredBid(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ForbiddenOperationOnExpiredBid(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
}
