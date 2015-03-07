

package onjlib.utils;


import java.util.Random;
import java.util.Vector;



class Btest {

	static final int SIZE = 11;

	public static void main(String[] args)
	{
		Random r = new Random(100);
		short s;
		IntComparator ic = new IntComparator();
		Btree bt = new Btree(ic);
		Integer n;


		System.out.println("Starting ....");
		for(int i = 0; i < SIZE; ++i) {
			s = (short) r.nextInt();
			n = new Integer((s >= 0 ? s : -1 * s));
			bt.insertElement(n);
		}
		System.out.println("Printing ....");
		bt.printTree();


		Vector v = bt.toVector();
		v.toString();
		System.out.println("size : " + v.size());


	}

}
