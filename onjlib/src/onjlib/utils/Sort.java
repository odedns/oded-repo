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

import java.util.Comparator;

/**
 * Sort class implements sorting algorithms.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 31/05/1998.
 **/
public abstract class Sort {

	static void swap(Object v[],int i, int j)
	{
		Object t;

		t = v[i];
		v[i] = v[j];
		v[j] = t;
	}
	/**
	 * The shell sort sorting algorithm.
	 * @param  v - an array of Objects.
	 * @param  c - a Comparator object.
	 **/
	public static void shellSort(Object v[], Comparator c)
	{
		int gap,i,j;
		int size = v.length;

		for(gap = size/2; gap > 0; gap/=2) {
			for(i = gap; i < size; ++i) {
					for(j=i-gap; j>= 0 &&
					c.compare(v[j+gap],v[j]) < 0; j-=gap) {
					swap(v, j,j+gap);
					}
			}
		}

	} /* shellSort */

	/**
	 * The quick sort sorting algorithm.
	 * @param  v - an array of Objects.
	 * @param  c - a Comparator object.
	 **/
	public static void quickSort(Object v[], Comparator c)
	{
		q_sort(v,0,v.length -1,c);
	}

	static void q_sort(Object v[], int low, int high, Comparator c)
	{
		/* quick sort   algorithm */
		int middle ,i,j;
		Object t;
		i = low; j = high;

		middle = (low + high) / 2;  /* find a pivot value */

			do {
			while (c.compare(v[middle], v[i]) > 0)
			/*
			 * move up while items are lower than pivot
			 */
						 ++i;
				while (c.compare(v[middle], v[j]) < 0)
			/*
			 * move down while items are higher than pivot
			 */
					--j;

			if(i <=j)  {
			/*
			 * if we haven't pased the whole list exchange the
				 * two value which are out of order
			 */
				t = v[i];
				 v[i] = v[j];
				 v[j] = t;
					++i;
					--j;
			}  /* end if */

			}  while(i <= j && i < middle && j > middle);

			/**
		 * repeat until i is smaller than j
		 * and both haven't passed middle
		 **/
			if (i < high)
			q_sort(v,i ,high,c);
		/*
		 * if i is smaller than the highest index
		 * call q_sort with i as low i
		 */

			if (j > low)
			q_sort(v,low,j,c);
		/* if j is higher than low
		 * call recursivly with j as high
		 */


	} /* q_sort */

	/**
	 * The heap sort sorting algorithm.
	 * @param  v - an array of Objects.
	 * @param  c - a Comparator object.
	 **/
	public static void heapSort(Object v[] ,Comparator c)
	{
		int size = v.length - 1;
		int n = size -1;

		Object t;
		int i,j;

		for(i = (size /2 ); i >= 0; --i) {
			heapify(v,i,size,c);
		}

		j = 1;
		for(i = j; i < size; ++i) {
			heapify(v,i,size,c);
			++j;
		}


		for(i=size-1; i >= 0; --i) {

			t = v[i+1];
			v[i+1] = v[0];
			v[0] = t;
			heapify(v,0,i,c);

		}

	}


	static void heapify(Object v[], int i, int n,Comparator c)
	{
		int j;
		boolean loopFlag;
		Object max;

		max = v[i];
		j = i * 2;

		loopFlag = (j <= n);

		while(loopFlag) {
			if(j < n) {
				if(c.compare(v[j] ,v[j+1]) < 0) {
					++j;
				}
			}
			if(c.compare(v[j],max) < 0) {
				loopFlag = false;
			} else {
				v[i] = v[j];
				i = j;
				j = 2 * i;
				loopFlag = (j <= n);
			}
		} /* while */
		/* final placement of root key */
		v[i] = max;
	}




	public static void mergeSort(Object dest[],Comparator c)
	{
		Object src[] = (Object[])dest.clone();
		mergeSort(src,dest,0,src.length,c);
	}

	private static void mergeSort(Object src[], Object dest[],
			  int low, int high, Comparator c)
	{
		int length = high - low;

		/*
		 * Insertion sort on smallest arrays
		 */
		if (length < 7) {
			for (int i=low; i<high; i++)
				for (int j=i; j>low &&
				 (c.compare(dest[j],dest[j-1])<0); j--)
					swap(dest, j, j-1);
					return;
		}

		// Recursively sort halves of dest into src
		int mid = (low + high)/2;
		mergeSort(dest, src, low, mid,c);
		mergeSort(dest, src, mid, high,c);

		/*
		 * If list is already sorted,
		 * just copy from src to dest.  This is an
		 * optimization that results in faster sorts for
		 * nearly ordered lists.
		 */
		if (c.compare(src[mid],src[mid-1]) >= 0) {
			System.arraycopy(src, low, dest, low, length);
			return;
		}
		// Merge sorted halves (now in src) into dest
		for(int i = low, p = low, q = mid; i < high; i++) {
					if (q>=high || p<mid && (c.compare(src[q],src[p])>=0)) {
						dest[i] = src[p++];
				} else {
						dest[i] = src[q++];
					}
			}
	 }

} /* sort */



