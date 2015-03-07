
import java.io.*;


public class OutputThread extends Thread {

	PrintWriter m_os;
	public OutputThread(PrintWriter os)
	{
		m_os = os;
	}


	public void run()
	{
		System.out.println("in OutputThread run ...");
		try {
			Thread.currentThread().sleep(1000);
			System.out.println("in OutputThread run: after sleep ..");
			m_os.println("<p> This is the new output from OutputThread ");
			m_os.flush();
			m_os.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


}
