package app;
import view.ApplicationView;

public class Program {
	
	public static void main(String[] args)
	{
		ApplicationView applicationView = new ApplicationView();
		Application app = new Application(applicationView);
		app.run();
		System.out.println("App running");
	}
}
