package tests;


import java.util.Random;
import onjlib.utils.IntComparator;
import onjlib.utils.Search;
import onjlib.utils.Sort;


class Sortest {

	static final int SIZE = 11;
	static void createList(Integer v[], int size)
	{

		Random r = new Random(100);
		short s;

		for(int i=0; i < size; ++i) {
			s = (short) r.nextInt();

			v[i] = new Integer((s >= 0 ? s : -1 * s));
		}
	}
	static void printList(Integer v[], int size)
	{
		for(int i=0; i < size; ++i) {
			System.out.println("v[" + i + "] = " + v[i].intValue());
		}
	}

	public static void main(String[] args)
	{

		Integer[] v = new Integer[SIZE];
		IntComparator ic = new IntComparator();


		System.out.println("Starting ....");
		createList(v,SIZE);
		printList(v,SIZE);
//		Sort.heapSort(v, ic);
		Sort.mergeSort(v,ic);
		printList(v,SIZE);
		int  inx = Search.bsearch(v,v[7],ic);
		System.out.println("inx : " + inx);
		inx = Search.lsearch(v,v[7],ic);
		System.out.println("inx : " + inx);


	}

}
