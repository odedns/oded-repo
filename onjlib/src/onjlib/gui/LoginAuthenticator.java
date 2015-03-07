package onjlib.gui;

/**
 * LoginAuthenticator interface this is an interface that
 * used by the login dialog.
 */
public interface LoginAuthenticator {

	/**
	 * callback function invoked by the login dialog when
	 * the user has typed the user and password.
	 * @param user the user field in the login dialog.
	 * @param password the password field in the login dialog.
	 * @return boolean true in case user authentication succeeded.
	 * false otherwise.
	 */
	public boolean authenticate(String user, String password);
	/**
	 * callback function invoked by the login dialog when user
	 * authentication has failed.
	 * @param user the user field in the login dialog.
	 * @param password the password field in the login dialog.
	 */
	public void onFailedAuthentication(String user, String password);
}
