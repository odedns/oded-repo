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


package onjlib.utils.old;
/**
 * An abstract class for comparing two objects.
 * used when performing comparisons.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 1/10/1998
 * @version 1.1 30/3/2002 - move to package onjlib.utils.old
 * the jdk 1.2 supports Comparator.
 */
public abstract class Comparator {
	/**
	 * compares two objects.
	 * @param o1 the first object.
	 * @param o2 the second object.
	 * @return int positive if f o2 > o1. negative if o2 < o1
	 * 0 if objects are equal.
	 */
	public abstract int compare(Object o1, Object o2);
	/**
	 * checks id the first object is less than the second object.
	 * @return boolean true if o2 is less than o1.
	 */
	public boolean lessThan(Object o1, Object o2)
	{
		return(compare(o1,o2) > 0);
	}
	/**
	 * checks id the first object is greater than the second object.
	 * @return boolean true if o2 is greater than o1.
	 */
	public boolean greaterThan(Object o1, Object o2)
	{
		return(compare(o1,o2) < 0);
	}
}
