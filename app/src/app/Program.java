package app;
import view.ApplicationView;

public class Program {
	
	public static void main(String[] args)
	{
		Bootstrapper bootstrapper = new Bootstrapper();
		bootstrapper.run();
		System.out.println("App running");
	}
}
