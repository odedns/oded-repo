
/*------------------------------------------------------------------------*/
/*   Module       :  onjlib.utils.Set                                       */
/*   File         :  Set.java                                             */
/*   Date         :  21/11/1998                                           */
/*   Description  :                                                       */
/*   Author       :  Oded Nissan                                          */
/*                   Copyright (c) 1994-1998 Oded Nissan.                 */
/*   History      :                                                       */
/*------------------------------------------------------------------------*/
/*   Date         |   Description                                         */
/*------------------------------------------------------------------------*/
/*   21/11/1998   |   Initial Release.                                    */
/*                |                                                       */
/*                |                                                       */
/*------------------------------------------------------------------------*/
package onjlib.utils.old;
import java.util.Vector;

/**
 * a class implementing a set datastructure.
 * derived from the jdk vector class.
 */
public class Set extends Vector {

	/**
	 * constructor. Creates a Set object.
	 */
	public Set()
	{
		super();
	}

/*

	public Set(Set o)
	{
		this = o;
	}
*/
	/**
	 * adds an object to the set.
	 * @param o 	the object to add to the set.
	 * @return boolean true if object can be added
	 * to the set, false otherwise.
	 */
	public boolean add(Object o)
	{
		if(contains(o)) {
			return(false);
		}
		addElement(o);
		return(true);
	}

	/**
	 * deletes an object from the set.
	 * @param o 	the object to delete from the set.
	 * @return boolean true if object can be deleted
	 * from the set, false otherwise.
	 */
	public boolean delete(Object o)
	{
		return(super.removeElement(o));
	}

	/**
	 * checks if the set contains a specific object.
	 * @param o the object to search for.
	 * @return boolean 	true if the object exists
	 * in the set, false otherwise.
	 */
	public boolean has(Object o)
	{
		return(super.contains(o));
	}


	/**
	 * get the predecessor of a specific object
	 * in the set.
	 * @param o the object for which we need
	 * to find the predecessor.
	 * @return Object the object we searched for, or null
	 * if the object could not be found or the param object
	 * does not have a predecessor.
	 */
	public Object getPrev(Object o)
	{
		int i = super.indexOf(o);
		return ( i > 0 ) ? (super.elementAt(--i)) : (null);

	}

	/**
	 * get the successor of a specific object
	 * in the set.
	 * @param o the object for which we need
	 * to find the successor.
	 * @return Object the object we searched for, or null
	 * if the object could not be found or the param object
	 * does not have a successor.
	 */
	public Object getNext(Object o)
	{
		int i = super.indexOf(o);
		if(i !=  -1 && i < super.size() ) {
			return(super.elementAt(++i));
		} else {
			return(null);
		}
	}



} /* class Set */
