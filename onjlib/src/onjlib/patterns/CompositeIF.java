package onjlib.patterns;
import java.util.Iterator;

/**
 * Interface implementing the composite pattern.
 * The object can either be a single object or a collection
 * of objects contained in the object.
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface CompositeIF {
	/**
	 * add an object to the tree contained
	 * in the object.
	 * @param o the Object to add.
	 */
	public abstract void add(Object o);
	/**
	 * remove an object from the tree contained
	 * in the object.
	 * @param o the Object to remove.
	 */
	public abstract void remove(Object o);
	/**
	 * return an enumeration of the objects 
	 * contained in this composite object.
	 * @return Enumeration the enumeration of the objects
	 * contained in this composite object.
	 */
	public abstract Iterator elements();

}
