

package onjlib.gui;

/**
 * YNHandler class which implements the AnswerInterface interface in order
 * to support a dialog with "yes" and "no" answers.
 */
public class YNHandler implements AnswerListener {
	/**
	 * callback function invoked by the dialog when a choice
	 * is made.
	 * @param code an int representing the choice made
	 * in the dialog.
	 */
	public void doAction(int code)
	{
		System.out.println("in doAction code = " + code);

	}
}
