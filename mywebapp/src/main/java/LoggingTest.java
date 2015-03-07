/**
 * Created on 07/04/2005
 * @author P0006439
 * @version $id$
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


import java.io.InputStream;
import java.util.logging.*;



public class LoggingTest
{

	public static void main(String[] args)
	{
		try {
		
			LogManager mng = LogManager.getLogManager();
			InputStream is = mng.getClass().getResourceAsStream("/log4j.xml");
			mng.readConfiguration(is);
			Logger log = Logger.getLogger("root");
	
			log.warning("this is a warning message");
			log.fine("fine message");
			log.info("info message");
			log.severe("severe error ");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
