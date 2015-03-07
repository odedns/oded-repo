
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

import java.util.Comparator;

/**
 * A comparison function, which imposes a total ordering on some collection
 * of objects. Comparators can be passed to a sort method
 * (such as Collections.sort) to allow precise control over the sort order.
 * Comparators can also be used to control the order of certain data structures
 * (such as TreeSet or TreeMap).
 * The ordering imposed by a Comparator c on a set of elements S is said to be
 * consistent with equals if and only if (compare((Object)e1, (Object)e2)==0)
 * has the same boolean value as e1.equals((Object)e2) for every e1 and e2 in S.
 * Caution should be exercised when using a comparator capable of imposing an
 * ordering inconsistent with equals to order a sorted set (or sorted map).
 * Suppose a sorted set (or sorted map) with an explicit Comparator c is used
 * with elements (or keys) drawn from a set S.
 * If the ordering imposed by c on S is inconsistent with equals, the sorted
 * set (or sorted map) will behave "strangely."
 * In particular the sorted set (or sorted map) will violate the general
 * contract for set (or map), which is defined in terms of equals.
 *
 * For example, if one adds two keys a and b such that
 * (a.equals((Object)b) && c.compare((Object)a, (Object)b) != 0) to a sorted
 * set with comparator c, the second add operation will return false
 * (and the size of the sorted set will not increase) because a and b are
 * equivalent from the sorted set's perspective.
 */
public class DoubleComparator implements Comparator {

/**
 * Compares its two arguments for order. Returns a negative integer, zero,
 * or a positive integer as the first argument is less than, equal to,
 * or greater than the second.
 * @param o1 the first object to be compared.
 * @param o2 the second object to be compared.
 * @return a negative integer, zero, or a positive integer as the first
 * argument is less than, equal to, or greater than the second.
 * @throws ClassCastException if the arguments' types prevent them from being
 * compared by this Comparator.
 */
	public int compare(Object o1, Object o2)
	{
		Double i1 = (Double) o1;
		Double i2 = (Double) o2;
		double d = i1.doubleValue() - i2.doubleValue();
		if(d > 0) {
			d = 1;
		} else {
			if(d < 0) {
			d = -1;
			}
		}
		return((int)d);
	}

	/**
	 * Indicates whether some other object is "equal to" this Comparator.
	 * This method must obey the general contract of Object.equals(Object).
	 * Additionally, this method can return true only if the specified Object
	 * is also a comparator and it imposes the same ordering as this comparator.
	 * Thus, comp1.equals(comp2) implies that
	 * sgn(comp1.compare(o1, o2))==sgn(comp2.compare(o1, o2)) for every object
	 * reference o1 and o2.
	 * Note that it is always safe not to override Object.equals(Object).
	 * However, overriding this method may, in some cases, improve performance
	 * by allowing programs to determine that two distinct Comparators impose
	 * the same order.
	 * @overrides equals in class Object
	 * @param o the reference object with which to compare.
	 * @return true only if the specified object is also a comparator and it
	 * imposes the same ordering as this comparator.
	 */
	public boolean equals(Object o)
	{
		boolean res = false;
		if(o instanceof Comparator) {
			res = true;
		}
		return(res);

	}
}

