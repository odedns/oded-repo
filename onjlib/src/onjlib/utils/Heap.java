/**
 * Copyright (c) 1994-2002 Oded Nissan.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import java.util.AbstractCollection;


/**
 * This class implements a heap in Java.
 * Operations supported are:
 * insert an Object into the heap, remove an object from the heap
 * and convert an array into a heap.
 * The class can use a synchronized or nonsynchronized list to
 * store the objects.
 *
 * <p>Copyright (c) 2002
 * @author Oded Nissan
 * @version 1.0 17/04/2002
 */

public class Heap extends AbstractCollection {
	List m_ar;
	Comparator m_c;


	/**
	 * creates an empty heap using the Comparator object
	 * to compare the objects in the heap.
	 * @param c Comparator to compare the objects in the heap.
	 */
	public Heap(Comparator c)
	{
		m_ar = new ArrayList();
		m_ar.add(null);
		m_c = c;
	}

	/**
	 * creates an empty heap using the Comparator object
	 * to compare the objects in the heap.
	 * @param c Comparator to compare the objects in the heap.
	 * @param synch a boolean specifying if the Heap should use a sycnhorinized
	 * List to store the objects.
	 */
	public Heap(Comparator c, boolean synch)
	{
		this(c);
		if(synch) {
			m_ar = Collections.synchronizedList(m_ar);
		}

	}
	/**
	 * creates a heap from the input array using the Comparator object
	 * to compare the objects in the heap.
	 * @param array an array of objects to insert into the heap.
	 * @param c Comparator to compare the objects in the heap.
	 */
	public Heap(Object array[], Comparator c)
	{
		this(c);
		for(int i=0; i < array.length; ++i) {
			add(array[i]);
		}
	}

	/**
	 * creates a heap from the input array using the Comparator object
	 * to compare the objects in the heap.
	 * @param array an array of objects to insert into the heap.
	 * @param c Comparator to compare the objects in the heap.
	 * @param synch a boolean specifying if the Heap should use a sycnhorinized
	 * List to store the objects.
	 */
	public Heap(Object array[], Comparator c, boolean synch)
	{
		this(array,c);
		if(synch) {
			m_ar = Collections.synchronizedList(m_ar);
		}
	}

	/**
	 * Makes this class synchronized by using
	 * a synchronized version of ArrayList to
	 * store the objects.
	 */
	public void setSynchronized()
	{
		m_ar = Collections.synchronizedList(m_ar);
	}


	/**
	 * Removes all this collection's elements that are also contained
	 * in the specified collection (optional operation).
	 * After this call returns, this collection will contain no elements
	 * in common with the specified collection.
	 * @param c - elements to be removed from this collection
	 * @return true if this collection changed as a result of the call.
	 */

	public boolean removeAll(Collection c)
	{
		return(m_ar.removeAll(c));
	}




	/**
	 * Removes all of the elements from this collection (optional operation).
	 * This collection will be empty after this method returns unless
	 * it throws an exception.
	 */
	public void clear()
	{
		m_ar.clear();
	}

	/**
	 * Returns an iterator over the elements contained in this collection.
	 * @return an iterator over the elements contained in this collection.
	 */
	public Iterator iterator()
	{
		return(m_ar.iterator());
	}

	/**
	 * add an object to the heap.
	 * The object is shifted to its correct position in the heap.
	 * @param o Object the object to insert into the heap.
	 * @return true if the collection changed as a result of the call.
	 */
	public boolean add(Object o)
	{
		m_ar.add(o);
		int i = m_ar.size() -1;
		int j = i /2;
		while(j > 0 && j < i ) {
			if(m_c.compare(o,m_ar.get(j)) < 0) {
				break;
			} else {
				swap(m_ar,i,j);
				i = j;
				j = i / 2;
			} // if
		} // while
		return(true);
	}


	/**
	 * Removes an object from the heap. The object at the root node is
	 * removed. This is also the largest object.
	 * The heap is then reheapified.
	 * @return Object the removed object.
	 */
	public Object remove()
	{
		int last = m_ar.size() - 1;

		if(last <= 0) {
			return(null);
		}

		Object o = m_ar.get(1);
		Object o2 = m_ar.remove(last--);
		if(last <= 0) {
			return(o);
		} else {
			m_ar.set(1,o2);
		}

		int i = 1;
		int j = 2 * i;
		while(j <= last) {
			if( (j < last) &&
				(m_c.compare(m_ar.get(j), m_ar.get(j+1)) < 0) ) {
				++j;
			}
			if(m_c.compare(m_ar.get(i), m_ar.get(j)) < 0) {
				swap(m_ar,i,j);
			} // if
			i = j;
			j = 2 * i;

		} // while

		return(o);
	}


	/**
	 * Returns the size of the Heap.
	 * This method is overridden since the first object is a dummy.
	 * @return int the number of elements in the heap.
	 */
	public int size()
	{
		return(m_ar.size() -1);
	}


	/**
	 * Convert the heap into an array.
	 * The objects will be removed from the heap one
	 * by one and inserted into the array.
	 */
	public Object[] toArray()
	{
		Object array[] = new Object[size()];
		return(toArray(array));
	}

	/**
	 * Convert the heap into an array.
	 * The objects will be removed from the heap one
	 * by one and inserted into the array.
	 */
	public Object[] toArray(Object array[])
	{
		int size = size();
		if(array.length < size) {
			array = (Object[])java.lang.reflect.Array.newInstance(
				array.getClass().getComponentType(), size);
		}

		Heap h = null;
		try {
			h = (Heap)this.clone();
		}	catch (CloneNotSupportedException ex) {
			return(null);
		}

		int i=0;
		Object o;
		while(null != (o = h.remove())) {
			array[i++] = o;
		}
		return(array);
	}


	/**
	 * Returns a string representation of this collection.
	 * The string representation consists of a list of the collection's
	 * elements in the order they are returned by its iterator,
	 * enclosed in square brackets ("[]").
	 * Adjacent elements are separated by the characters ", "(comma and space).
	 * Elements are converted to strings as by String.valueOf(Object).
	 * This implementation creates an empty string buffer, appends a left
	 * square bracket, and iterates over the collection appending the string
	 * representation of each element in turn.
	 * After appending each element except the last, the string ", "
	 * is appended.
	 * Finally a right bracket is appended. A string is obtained from the
	 * string buffer, and returned.
	 * @return String a string representation of this collection.
	 */
	public String toString()
	{
		return(m_ar.toString());
	}

	/**
	 * swaps two objects in the ArrayList.
	 */
	private void swap(List ar, int i, int j)
	{
		Object t;
		t = ar.get(i);
		ar.set(i,ar.get(j));
		ar.set(j,t);
	}


	/**
	 * main test program.
	 */
	public static void main(String argv[])
	{
		Debug.setDebug(true);
		IntComparator inc = new IntComparator();
		Heap h = new Heap(inc);
		for(int i=0; i < 5; ++i) {
			h.add(new Integer(i));
		}

		int j = 0;
		Integer i;
		while(null != (i = (Integer) h.remove())) {
			System.out.println(i.toString());
		}



	} // main

} // class Heap

