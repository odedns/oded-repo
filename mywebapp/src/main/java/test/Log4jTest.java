/**
 * Dec 12, 2007
 * Log4jTest.java
  */
package test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;


class Foo {
	static final Logger log = Logger.getLogger(Foo.class);
	Foo()
	{
		log.debug("in Foo()");
	}
}

class Bar {
	static final Logger log = Logger.getLogger(Bar.class);
	Bar()
	{
		log.debug("in Bar() ");
	}
}

/**
 * @author Odedn
 *
 */
public class Log4jTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		//PropertyConfigurator.configure("c:/tmp/log4j.properties");
		//DOMConfigurator.configure("c:/temp/log4j.xml");
		BasicConfigurator.configure();
		
		Logger log = Logger.getLogger("Web");
		log.debug("log debug message");
		log.info("log info message");
		log.error("log error message");
		log.fatal("log fatal message");
		Foo f = new Foo();
		Bar b = new Bar();
	}

}
