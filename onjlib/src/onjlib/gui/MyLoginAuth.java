package onjlib.gui;

public class MyLoginAuth implements LoginAuthenticator {
	public boolean authenticate(String user, String password)
	{
		System.out.println("Authenticating: ");
		System.out.println("User = " + user);
		System.out.println("Password = " + password);
		return(false);
	}

	public void onFailedAuthentication(String user, String password)
	{
		System.out.println("Failed to Autneticate : ");
		System.out.println("user = " + user + "pass = " + password);
	}
}
