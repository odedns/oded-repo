package onjlib.patterns;

/**
 * Visitor design pattern class interface. 
 * This provides an interface for classes implementing
 * the visitor pattern.
 * Users should subclass this interface and the AbstractVisited class
 * or VisitedIF interface.
 * In this class the visit method should be implemented in order to 
 * activate a method on the visited object.
 * 
 * @author odedn
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface VisitorIF {
	/**
	 * cast the visited object to the correct type
	 * and activate the appropriate method on it.
	 * @param o the visited Object.
	 */
	public abstract void visit(Object o);
}
