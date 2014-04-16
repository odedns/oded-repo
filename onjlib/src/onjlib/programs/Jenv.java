package onjlib.programs;
import java.util.*;


/**
 * Print java environment variables.
 * Using System.getProperties().
 */
public class Jenv {
	public static void main(String argv[])
	{

		/**
		 * if a variable was specified on the command
		 * line - pront its value.
		 */
		if(argv.length > 0) {
			String value = System.getProperty(argv[0]);
			if(null != value) {
				System.out.println(value);
			}
			System.exit(0);
		}
		/**
		 * If no arguments specified, print all environment 
		 * variables.
		 */
		Properties props = System.getProperties();
		Enumeration e = props.propertyNames();
		while(e.hasMoreElements()) {
			String key = (String)e.nextElement();
			String value = props.getProperty(key);
			System.out.println(key+"="+ value);
		}
		
	}


}
