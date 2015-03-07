
package onjlib.gui;

/**
 * an interface for getting an answer from a dialog.
 * the dialog will invoke doAction() as the callback
 * function.
 */
public interface AnswerListener {

	public static final int ANSWER_YES 	= 1;
	public static final int ANSWER_NO  	= 3;
	public static final int ANSWER_CANCEL  	= 3;
	public static final int ANSWER_OK       = 4;
	public static final int ANSWER_CLOSE    = 5;

	/**
	 * callback function invoked by the dialog when a choice
	 * is made.
	 * @param code an int representing the choice made
	 * in the dialog.
	 */
	public void doAction(int code);
}
