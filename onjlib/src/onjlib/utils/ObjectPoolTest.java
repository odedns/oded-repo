package onjlib.utils;

import java.io.IOException;

public class ObjectPoolTest {

public static void main(String argv[])
	{
		int size = 20;
		IntConstructor ictor = new IntConstructor();
		ObjectPool pool = new ObjectPool(ictor);
		pool.setMaxSize(25);
		Integer n;

		Debug.setDebug(true);
		for(int i=0; i<size; ++i) {
			n = (Integer) pool.getObject();
			System.out.println("n[" + i +"]=" + n);
			pool.releaseObject(n);
		}


		try {
			Thread.sleep(10000);
		} catch(Exception e) {
		}

		pool.close();
		try {
			System.in.read();
		} catch(IOException ie){}


	}

}