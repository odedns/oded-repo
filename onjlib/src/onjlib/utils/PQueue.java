/**
 * Copyright (c) 1994-1999 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package onjlib.utils;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Comparator;

/**
 * Priority Queue class.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 01/11/1998.
 */
public class PQueue {
	Vector m_v;
	int m_nelem;
	Comparator m_c;

	/**
	 * constructor for priority queue object.
	 * @param c Comparator object.
	 */
	public PQueue(Comparator c)
	{
		m_nelem = 0;
		m_c = c;
		m_v = new Vector();
		/* push a dummy */
		m_v.addElement(null);
			//{{INIT_CONTROLS
		//}}
}

	/**
	 * constructor for priority queue object.
	 * @param v Vector containing objects.
	 * @param c Comparator object.
	 **/
	public PQueue(Vector v, Comparator c)
	{
		m_nelem = 0;
		m_c = c;
		m_v = new Vector();
		/* push a dummy */
		m_v.addElement(null);
		createHeap(v);
	}


	private void swap(Vector v, int i, int j)
	{
		Object t;

		t = v.elementAt(i);
		v.setElementAt(v.elementAt(j),i);
		v.setElementAt(t,j);
	}


	/**
	 * push an object into the priority queue.
	 * @param o Object to push into the queue.
	 */
	public void push(Object o)
	{
		int j;
		int i = ++m_nelem;
		m_v.addElement(o);
		while(i > 1) {
			j = i / 2;
			if(m_c.compare(m_v.elementAt(i), m_v.elementAt(j)) > 0 ) {
				swap(m_v,i,j);
				i = j;
			} else {
				break;
			}
		}
	}

	/**
	 * pop an Object from the priority queue.
	 * @return Object the object popped.
	 **/
	public Object pop()
	{
		/* get object at root node */
		Object o = m_v.elementAt(1);

		/* move last item to root */
		m_v.setElementAt(m_v.elementAt(m_nelem),1);
		m_v.removeElementAt(m_nelem);
		--m_nelem;
		/*
		 * push the item at root
		 * to its proper place.
		 */
		int i = 1;
		int child;

		while(i < m_nelem) {
			child = i * 2;
			if(child > m_nelem)
				break;
			if(child < m_nelem) {
				if(m_c.compare(m_v.elementAt(child),
						m_v.elementAt(child+1))< 0) {
					child++;
				}
			}
			if(m_c.compare(m_v.elementAt(i), m_v.elementAt(child))< 0) {
				swap(m_v,i, child);
				i = child;
			} else {
				break;
			}
		} /* while */

		return(o);
	} /* pop */


	/**
	 * checks if the priority queue has more elements.
	 * @return boolean true if the priority queue has more elements.
	 **/
	public boolean hasMoreElements()
	{
		return(m_nelem > 0);
	}


	/**
	 * return an enumeration of the elements.
	 * @return Enumeration.
	 */
	public Enumeration elements()
	{
		return(m_v.elements());
	}
	/**
	 * return the priority queue size in elements.
	 * @return int the number of elements in the pqeueu.
	 **/
	public int size()
	{
		return(m_nelem);
	}

	/**
	 * create a heap from the objects in the input vector.
	 * put them into the priority queue.
	 * @param v the input Vector.
	 **/
	public void createHeap(Vector v)
	{
		Enumeration e = v.elements();
		Object o;


		while(e.hasMoreElements()) {
			o = (Object) e.nextElement();
			System.out.println("o = " + o );
			push(o);
		}
	}


	/**
	 * dump the contents of the internal vector m_v.
	 * used for debug purpose.
	 **/
	void dumpVector()
	{
		Enumeration e = m_v.elements();
		Object o;
		int i = 0;

		System.out.println("Dumping vector ");
		while(e.hasMoreElements()) {
			 o = (Object) e.nextElement();
			 System.out.println("o[ " + i + "] = " + o);
			 ++i;
		}
	}

	public static void main(String argv[])
	{
		IntComparator ic = new IntComparator();
		PQueue pq = new PQueue(ic);
		Vector v = new Vector();

		for(int i = 0; i  < 10; ++i) {
			Integer in = new Integer(i);
			System.out.println("push = " + in);
			pq.push(in);
			v.addElement(in);

		}
		/*
		pq.dumpVector();
		*/

		while(pq.hasMoreElements()) {
			Integer in = (Integer)pq.pop();
			System.out.println("in = " + in);
		}


		System.out.println("pq has " + pq.size());
		pq.createHeap(v);
		System.out.println("pq has " + pq.size());
		System.out.println("after createHeap()" );
		while(pq.hasMoreElements()) {
			Integer in = (Integer)pq.pop();
			System.out.println("in = " + in);
		}


}
	//{{DECLARE_CONTROLS
	//}}
} /* class PQueue */
