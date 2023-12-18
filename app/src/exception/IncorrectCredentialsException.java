package exception;

public class IncorrectCredentialsException extends Exception{
	private static final String message = "Incorrect credentials";
	
	public IncorrectCredentialsException()
	{
		super(message);
	}
}
