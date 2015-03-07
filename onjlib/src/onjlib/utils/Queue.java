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

/**
 * Queue class. Implements a simple Queue using a Vector.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 10/11/1998.
 */
public class Queue extends Vector {


	/**
	 * constructor. Creates a Queue object.
	 */
	public Queue()
	{
		super();
	}

	/**
	 * checks if the queue is empty.
	 * @return true if the queue is empty, false otherwise.
	 */
	public boolean empty()
	{
		return(super.size() == 0);
	}

	/**
	 * pops an object from the queue.
	 * @return Object the object popped from the queue.
	 */
	public Object pop()
	{
		Object o = super.elementAt(0);
		super.removeElementAt(0);
		return(o);
	}

	/**
	 * pushes an object into the queue.
	 */
	public void push(Object o)
	{
		super.addElement(o);
	}


	/**
	 * peeks at the queue and returns the next object in
	 * the queue without removing it.
	 * @return Object the next object in the queue.
	 */
	public Object peek()
	{
		return(super.elementAt(0));
	}


	/**
	 * searches for an object in the queue.
	 * @return an index >=0  if the object is in the queue.
	 * -1 if the object is not in the queue.
	 */
	public int search(Object o)
	{
		return(super.indexOf(o));
	}


} /* class Queue */
