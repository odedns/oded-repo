/**
 * Dec 12, 2007
 * ThreadLocalTest.java
  */
package test;



class Foo2 {
	Foo2()
	{
		System.out.println("in Foo2() name: " + ThreadName.get());
	}
}

class Bar2 {
	Bar2()
	{
		System.out.println("in Bar2() name: " + ThreadName.get());
	}
}


class ThreadName {
	
	private static ThreadLocal<String> tl = new ThreadLocal<String>() {
		 protected synchronized String initialValue() {
	            return (new String(""));
	        }
	 };
	
		     

	public static void set(String s)
	{
		System.out.println("set invoked");
		tl.set(s);
	}
	
    public static String get() {
        return ((String) tl.get());
    }
	
}



class SerialNum {
    // The next serial number to be assigned
    private static int nextSerialNum = 0;

    private static ThreadLocal serialNum = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new Integer(nextSerialNum++);
        }
    };

    public static int get() {
        return ((Integer) (serialNum.get())).intValue();
    }
}


class MyThread extends Thread {
	
	public void run()
	{
		
		System.out.println("in run() : " + getName());
		ThreadName.set(getName());
//		System.out.println("serialnum = " + SerialNum.get());
		Foo2 f = new Foo2();
		Bar2 b = new Bar2();
		
	}
}
/**
 * @author Odedn
 *
 */
public class ThreadLocalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyThread th1 = new MyThread();
		MyThread th2 = new MyThread();
		th1.start();
		th2.start();
	
	}

}
