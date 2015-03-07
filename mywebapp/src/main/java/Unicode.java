import java.nio.charset.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Unicode {

    public Unicode() {
    }

	static void dumpBuf(byte b[])
	{
		System.out.println("b.length = " + b.length);
		for(int i=0; i < b.length; ++i) {
			System.out.print(Integer.toHexString((int)b[i])+ ' ');

		}
		System.out.print('\n');
	}
	public static void main(String argv[])
	{
		String s= " ?????? ?????";


		try {
			System.out.println("available charsets: ");
			SortedMap map = Charset.availableCharsets();
			Collection c = map.values();
			Iterator iter = c.iterator();
			while(iter.hasNext()) {
				Object val = iter.next();
				System.out.println(val.toString());
			}
			
			char c1[] = { 0x05d0, 0x05d1 };							
			String s1 = new String(c1);			
			System.out.println(new String(s1.getBytes(),"windows-1255"));
			/*
			byte b[] = s.getBytes();
			System.out.println("default");
			dumpBuf(b);
			b = s.getBytes("utf-16");
			System.out.println("utf-16");
			dumpBuf(b);
			System.out.println("utf-8");
			b = s.getBytes("utf-8");
			dumpBuf(b);
			System.out.println("windows-1252");
			b = s.getBytes("windows-1252");
			dumpBuf(b);
			System.out.println("s = " + s);
	    	System.out.println("s.length = " + s.length());


			System.out.println("Cp1252");
		    b = s.getBytes("Cp1252");
			dumpBuf(b);
			System.out.println("Cp1255");
			b = s.getBytes("Cp1255");
			dumpBuf(b);

			System.out.println("Cp1252 to Cp1255 : ");
			String t = new String(s.getBytes("Cp1252"),"Cp1255");
			b = t.getBytes("Cp1255");
			dumpBuf(b);


			System.out.println("t =" + t);
			*/
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}



	}
}


