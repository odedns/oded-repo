package onjlib.patterns;

/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class AbstractVisited implements VisitedIF {

	/**
	 * Constructor for AbstractVisited.
	 */
	public AbstractVisited() {
		super();
	}

	/**
	 * This method calls the visit method on the visitor class.
	 * The this reference is passed to the visit method allowing
	 * the visitor class to activate methods on this class.
	 * @see onjlib.patterns.VisitedIF#accept(Object)
	 */
	public void accept(VisitorIF v) {
		v.visit(this);
	}

}
