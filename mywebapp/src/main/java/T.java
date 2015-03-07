import java.io.Serializable;

/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

class Params implements Serializable {
	int m_code;
	String m_str;
	
	Params(int code, String str)
	{
		m_code = code;
		m_str = str;	
	}	
	
	public String toString()
	{
		return(new String(m_code + "-" + m_str));	
	}	
	
}



public class T {

	/**
	 * Constructor for T.
	 */
	public T() {
		super();
	}
	
	static void foo(Params in)
	{
		Params out = new Params(10,"server");
		in = out;
		
	}
	public static void main(String args[])
	{
		System.out.println("in T");
		
		Params p = new Params(1,"client");
		foo(p);
		System.out.println("out = " + p.toString());
		
		
		String s = "a,b,c";
		
		String v[] = s.split(",");
		for(int i=0; i < v.length; ++i) {
			System.out.println("v[" +i +"]=" + v[i] );
		}
		
		
	}

}
