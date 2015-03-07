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

import java.util.*;

/**
 * Title: GDQueue
 * Description: a General Queue implmentation based on linked list.
 * This can serv as a base class for Stack and Queue implementations.
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author Oded Nissan
 * @version 1.0 14/3/2002
 */

public class GDQueue {

	List m_list;

	/**
	 * Constructor specifies if the implementation is going to
	 * be synchronized or not.
	 * @param synch a boolean that specifies if the object is sycnhronized.
	 */
	public GDQueue(boolean synch)
	{
		this();
		if(synch) {
			m_list = Collections.synchronizedList(m_list);
		}
	}

	/**
	 * Default constructor.
	 * This creates a non-synchornized object.
	 */
	public GDQueue()
	{
		m_list = new LinkedList();
	}

	/**
	 * push an object into the queue list.
	 * The Object will be added to the end of the linked list.
	 * @param o  the object to push.
	 */
	public void push(Object o)
	{
		m_list.add(o);
	}

	/**
	 * Pop an object from the front of the queue - FIFO.
	 * @return Object the returned object or null.
	 */
	protected Object popFront()
	{
		Object o = null;
		if(!m_list.isEmpty()) {
			o =  ((LinkedList)m_list).removeFirst();
		}
		return(o);
	}

	/**
	 * Pop an object from the end of the queue - LIFO.
	 * @return Object the returned object or null.
	 */
	protected Object popBack()
	{
		Object o = null;
		if(!m_list.isEmpty()) {
			o = ((LinkedList)m_list).removeLast();
		}
		return(o);
	}

	/**
	 * Peek the end of the queue - LIFO.
	 * @return Object the returned object or null.
	 */
	protected Object peekBack()
	{
			Object o = null;
		if(!m_list.isEmpty()) {
			o = ((LinkedList)m_list).getLast();
		}
		return(o);
	}

	/**
	 * Peek the  front of the queue - LIFO.
	 * @return Object the returned object or null.
	 */
	protected Object peekFront()
	{
			Object o = null;
		if(!m_list.isEmpty()) {
			o = ((LinkedList)m_list).getFirst();
		}
		return(o);
	}

	/**
	 * search for an Object on the queue.
	 * return the object's position in the queue or -1 if not found.
	 * @param o the Object to search for.
	 * @return int the Object's position in the queue.
	 */
	public int search(Object o)
	{
		int index = -1;
		if(!m_list.isEmpty()) {
			index =  ((LinkedList)m_list).indexOf(o);
		}
		return(index);
	}



}
