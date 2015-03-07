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
import java.util.Comparator;

/**
 * Search class implements searching algorithms.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 31/05/1998.
 */
public abstract class Search {

	/**
	 * Performs binary search on an array.
	 * @param v the array to search.
	 * @param key the key to search for.
	 * @param c Comparator object to perform comparisons.
	 */
	public static int bsearch(Object v[], Object key, Comparator c)
	{
		int start  = 0;
		int end = v.length;
		int middle;
		int res;
		int found = -1;

		while ( found < 0) {
			middle = (start + end) / 2;
			res = c.compare(key,v[middle]);
			if(res == 0) {
				found = middle;
				break;
			}

			if(res > 0) {
				/* key > v[middle]
				 */
				start = middle + 1;
			}
			if(res < 0) {
				end = middle - 1;
			}

		}
		return(found);
	}


	/**
	 * Performs linear search on an array.
	 * @param v the array to search.
	 * @param key the key to search for.
	 * @param c Comparator object to perform comparisons.
	 */
	public static int lsearch(Object v[], Object key, Comparator c)
	{
		int found = -1;

		for(int i=0; i < v.length; ++i) {
			if(0 == c.compare(key,v[i])) {
				found = i;
				break;
			}
		}
		return(found);
	}

}
