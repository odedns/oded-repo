
package onjlib.utils;


public class SemTest extends Thread {
	Semaphore m_sem;
	String m_name;

	SemTest(Semaphore sem, String name)
	{
		m_sem = sem;
		m_name = name;

	}


	public void run()
	{

		System.out.println(m_name  + ": waiting for semaphore");
		try {
			m_sem.get();
			System.out.println(m_name  + " : got semaphore");
			Thread.sleep(1000);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		System.out.println(m_name + " : releasing semaphore");
		m_sem.release();
	}


	public static void main(String argv[])
	{
		final int numThreads = 5;
		Semaphore sem = new Semaphore(2);
		String thName = "Thread-";

		for(int i=0; i < numThreads; ++i) {
			SemTest  s = new SemTest(sem, thName + i);
			s.start();
		}
	}



}
