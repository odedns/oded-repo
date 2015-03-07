
package onjlib.utils;

/**
 * The timer class provides time measuring capabilities.
 * The timer is like a stop watch that returns the elapsed time
 * in milliseconds.
 * @author Oded Nissan.
 * @version 1.0 	10/10/2001
 */
public class GTimer {
	long m_startTime;
	long m_stopTime;

	/**
	 * constructor for the Time object.
	 * initializes the timer.
	 */
	public GTimer()
	{
		m_startTime = m_stopTime = 0;
	}

	/**
	 * start the timer.
	 */
	public void start()
	{
		m_startTime = System.currentTimeMillis();
	}

	/**
	 * stop the timer.
	 */
	public void stop()
	{
		m_stopTime = System.currentTimeMillis();
	}

	/*
	 * get the time in milliseconds that the timer
	 * was started.
	 * @return long the time in milliseconds that the timer
	 * was started.
	 */
	public long getStartTime()
	{
		return(m_startTime);
	}

	/*
	 * get the time in milliseconds that the timer
	 * was stopped.
	 * @return long the time in milliseconds that the timer
	 * was stopped.
	 */
	public long getStopTime()
	{
		return(m_stopTime);
	}

	/**
	 * get the time elapsed between the start time
	 * and the stop time.
	 * @return long the elapsed time in milliseconds.
	 */
	public long getElapsedTime()
	{
		return(m_stopTime - m_startTime);
	}

	/*
	 * main test program.
	 */
	public static void main(String argv[])
	{

		GTimer t = new GTimer();

		t.start();
		try {
			Thread.sleep(3000);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		t.stop();
		System.out.println("elapsed = " + t.getElapsedTime());
	}

}
