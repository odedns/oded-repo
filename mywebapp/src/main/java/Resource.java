import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * 
 */

/**
 * @author C5132784
 *
 */

public class Resource {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream is = cl.getResourceAsStream("com/sap/ca/aspose/license/Aspose.Total.Java.lic");
		//InputStream is = cl.getResourceAsStream("com/sap/ca/aspose/license/a.txt");
		if(is == null) {
			System.err.println("Error is is null");
			System.exit(1);
			
		}
		String s;
		try {
			s = IOUtils.toString(is);
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
