/*------------------------------------------------------------------------*/
/*   Module       :                                                       */
/*   File         :                                                       */
/*   Date         :                                                       */
/*   Description  :                                                       */
/*   Author       :  Oded Nissan                                          */
/*                   Copyright (c) 1994-1998 Oded Nissan.                 */
/*   History      :                                                       */
/*------------------------------------------------------------------------*/
/*   Date         |   Description                                         */
/*------------------------------------------------------------------------*/
/*   01/10/1998   |   Initial Release.                                    */
/*                |                                                       */
/*                |                                                       */
/*------------------------------------------------------------------------*/
package onjlib.utils;

/**
 * A Link object used for a linked list.
 * The object contains a data field and forward and backward links.
 */
class Link {
	Object m_data;
	Link   m_next;
	Link   m_prev;

	Link(Object o, Link prev, Link next)
	{
		m_data = o;
		m_prev = prev;
		m_next = next;
			//{{INIT_CONTROLS
		//}}
}
	//{{DECLARE_CONTROLS
	//}}
}


/**
 * Llist Linked list class
 */
public class Llist {


	Link m_curr;
	Link m_last;
	Link m_first;

	/**
	 * Create a List object.
	 */
	public Llist()
	{
		m_curr = m_last = m_first = null;
			//{{INIT_CONTROLS
		//}}
}

	/**
	 * Create a List object from another List object.
	 * @param l an Llist item.
	 **/
	public Llist(Llist l)
	{
		m_curr = l.m_curr;
		m_last = l.m_last;
		m_first = l.m_first;
	}

	/**
	 * checks if the list has more elements.
	 * @return boolean true if List has more elements, false otherwise.
	 **/
	public boolean hasMoreElements()
	{
		return(m_curr != null);
	}

	/**
	 * retrieve the next element in the List.
	 * @return Object the data of the next element in the list.
	 **/
	public Object nextElement()
	{
		Object o;

		if(null == m_curr)
			return(null);
		o = m_curr.m_data;
		m_curr = m_curr.m_next;
		return(o);
	}

	public void begin()
	{
		m_curr = m_first;
	}

	public void end()
	{
		m_curr = m_last;
	}

	/**
	 * adds an element to the end of the list.
	 * @param o Object to add to list.
	 */
	public void addElement(Object o)
	{
		Link l = new Link(o, m_last, null);
		if(m_first == null) {
			m_first = m_last = l;
		} else {
			m_last.m_next = l;
			m_last = l;
		}
	}

	/**
	 * removes an element from the current position
	 * in the list.
	 * @param o Object to remove from list.
	 */
	public Object removeElement()
	{
		Object o;
		Link n;
		Link p;

		if(m_curr == null) {
			return(null);
		}

		o = m_curr.m_data;
		n = m_curr.m_next;
		p = m_curr.m_prev;

		n.m_prev = p;
		p.m_next = n;
		m_curr = n;
		return(o);
	}

	/**
	 * insert a new element at the current position in
	 * the list.
	 * @param o Object to insert to list.
	 */
	public void insertElement(Object o)
	{
		Link l;

		if(m_curr == null) {
			l = new Link(o, m_curr, null);
			m_first = m_last = l;
		} else {
			l = new Link(o, m_curr, m_curr.m_next);
			m_curr.m_next = l;
			if(m_curr.m_next != null) {
				m_curr.m_next.m_prev = l;
			} else {
				m_last = l;
			}
		}
		l.m_prev = m_curr;
		m_curr = l;
	}

	public java.util.Enumeration elements()
	{
		return(new LlistEnum(m_first));
	}

	public static void main(String argv[])
	{
		Llist l = new Llist();
		LlistEnum e;
		String s;


		for(int i = 0;  i < 5; ++i) {
			s = "This is " + i;
			l.addElement(s);
		}
		s = "first item";
		l.begin();
		l.insertElement(s);
//		l.removeElement();
		l.end();
		s = "last item";
		l.insertElement(s);

	//	l.removeElement();
		e = (LlistEnum) l.elements();
		while(e.hasMoreElements()) {
			s = (String) e.nextElement();
			System.out.println("element : " + s);
		}
	}

	//{{DECLARE_CONTROLS
	//}}
}


class LlistEnum implements java.util.Enumeration {

	Link m_cursor;

	public LlistEnum(Link l)
	{
		m_cursor = l;
			//{{INIT_CONTROLS
		//}}
}

	public boolean hasMoreElements()
	{
		return m_cursor != null;
	}

	/**
	* move the iterator to the next position
	* @return the current element (before advancing the
	* position)
	* @exception NoSuchElementException if already at the
	* end of the list
	*/
	public Object nextElement()
	{
		Object o = m_cursor.m_data;

		m_cursor = m_cursor.m_next;
		return(o);
	}


	//{{DECLARE_CONTROLS
	//}}
}
