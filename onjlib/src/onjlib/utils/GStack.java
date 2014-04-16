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

/**
 * Title: GStack
 * Description:
 * <p> Copyright (c) 2002
 * @author Oded Nissan
 * @version 1.0 14/3/2002
 */



public class GStack extends GDQueue {
	/**
	 * Constructor specifies if the implementation is going to be synchronized or not.
	 * @param synch a boolean that specifies if the object is sycnhronized.
	 */
	public GStack(boolean synch)
	{
		super(synch);
	}

	/**
	 * Default constructor.
	 * This creates a non-synchornized object.
	 */
	public GStack()
	{
		super();
	}

	/**
	 * Pop an object from the back of the queue - LIFO.
	 * @return Object the returned object or null.
	 */
	public Object pop()
	{
		return(super.popBack());
	}

	/**
	 * Peek an object from the back of the queue - LIFO.
	 * The Object is not removed from the queue.
	 * @return Object the returned object or null.
	 */
	public Object peek()
	{
		return(super.peekBack());
	}



}
