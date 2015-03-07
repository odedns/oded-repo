/**
 * Copyright (c) 1998-2001 Oded Nissan.
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

import java.io.Serializable;

/**
 * Pair class implements a container holding
 * a pair of objects.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 20/4/1999.
 */
public class Pair implements Serializable {
	Object m_first;
	Object m_second;


	/**
	 * creates a Pair object initialize the pair of objects
	 * to null.
	 */
	public Pair()
	{
		m_first = m_second = null;
	}


	/**
	 * creates a Pair object.
	 * @param o1 the first object in the pair.
	 * @param o2 the second object in the pair.
	 */
	public Pair(Object o1, Object o2)
	{
		m_first = o1;
		m_second = o2;
	}

	/**
	 * creates a Pair object from another pair object.
	 * @param p a pair object to get the pair from.
	 */
	public Pair(Pair p)
	{
		m_first = p.m_first;
		m_second = p.m_second;
	}

	/**
	 * checks if the current Pair object is equal to another
	 * pair object.
	 * @param o a Pair object to compare the current object to.
	 * @return boolean true if objects are equal, false otherwise.
	 */
	public boolean equals(Object o)
	{
		Pair p = (Pair)o;
		if(p == null) {
			return(false);
		}
		return(m_first.equals(p.m_first) &&
				m_second.equals(p.m_second));
	}

	/**
	 * get the first object in the pair.
	 * @return Object the first object in the Pair.
	 */
	public Object getFirst()
	{
		return(m_first);
	}

	/**
	 * get the second object in the pair.
	 * @return Object the second object in the Pair.
	 */
	public Object getSecond()
	{
		return(m_second);
	}


}


