/**
 * Copyright (c) 1998-2002 Oded Nissan.
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
import java.util.LinkedList;

/**
 * Semaphore class implements a semaphore lock.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 10/12/1998
 *
 */
public class Semaphore {
	int m_count;
	int m_max;
	LinkedList m_threadList;

	/**
	 * create a Semaphore object with count usage handles.
	 * @param count the number of handles the semaphore contains.
	 * when all handles are in use the semaphore will block until
	 * a handle is freed.
	 */
	public Semaphore(int count)
	{
		m_count = m_max = count;
		LinkedList m_threadList = new LinkedList();
	}


	/**
	 * get a semaphore handle.
	 * If a handle is available then use it.
	 * If a handle is not available then we block until a handle
	 * is made available.
	 */
	public synchronized void get()
	{
		get(0);
	}

	/**
	 * get a semaphore handle.
	 * If a handle is available then use it.
	 * If a handle is not available then we block until a handle
	 * is made available.
	 * @param timeout specifies the timeout to wait for the semaphore.
	 */
	public synchronized boolean get(int timeout)
	{
		boolean status = true;

		Thread owner = Thread.currentThread();
		if(m_count > 0) {
			m_count--;
			m_threadList.add(owner);
		} else {
			try {
				if(timeout >0 ) {
				// use timeout
					wait(timeout);
					/**
					 * if semaphore was not released during
					 * the timeout. we need to return false
					 */
					if(!(m_count > 0) ) {
						status = false;
					}
				} else {
				// no timeout
					wait();
				}
			} catch(InterruptedException ie) {
			}
			m_count--;
			m_threadList.add(owner);
		}
		return(status);
	}


	/**
	 * release a semaphore handle.
	 * after using the semaphore handle, release it.
	 */
	public synchronized void release() throws IllegalStateException
	{
		if(m_count < m_max) {
			Thread owner = Thread.currentThread();
			if(m_threadList.contains(owner)) {
				m_count++;
				m_threadList.remove(owner);
				notify();
			} else {
				throw new IllegalStateException("Semaphore.release() : thread not owner");
			}
		}
	}
} /* Semaphore */

