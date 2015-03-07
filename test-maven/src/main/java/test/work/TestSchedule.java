/**
 * 
 */
package test.work;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author I070659
 *
 */

class MyTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("MyTask running at: " + new Date().toString());
	}
	
	
}

public class TestSchedule {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long interval = 15 * 1000;
		MyTask task = new MyTask();
		Timer timer = new Timer();
		Date now = new Date();
		timer.schedule(task,now,interval );
		timer.schedule(task, 1000);
		
	}

}
