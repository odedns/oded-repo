
package onjlib.utils;
import java.lang.reflect.*;

/**
 * A utility class for building a String from the member variables 
 * of an object. This can be useful for debugging and tracing.
 * Created on 28/09/2004
 * @author Oded Nissan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ToStringBuilder
{

	/**
	 * cannot instantiate class.
	 *
	 */
	private ToStringBuilder()
	{
		
	}
	
	
	private static String getAccessorMethod(String fieldName)
	{
		char c = fieldName.charAt(0);
		char c1 = Character.toUpperCase(c);
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(c1);
		sb.append(fieldName.substring(1));
		return(sb.toString());
	}
	/**
	 * Generate a String containing values of the member
	 * variables of the specific object.
	 * If the members are private it tries to access the variable using a getter
	 * method. If there is no access to the member field 'NO_ACCESS' is printed
	 * instead of the value.
	 * You can always use this utility class inside your object's toString()
	 * method to prevent any access problems.
	 * @param o the object to inspect
	 * @return String containing values of all member vars in the object.
	 * @throws IllegalAccessException
	 */
	public static String toString(Object o) 
	{
		Object obj = null;
		StringBuffer sb = new StringBuffer();
		Class c = o.getClass();
		sb.append(c.getName());
		sb.append(":\t");
		fillBuffer(o,c,sb);
		Class t = c;
		Class superClass = t.getSuperclass();
		while(superClass != null) {
			fillBuffer(o,superClass,sb);
			t = superClass;
			superClass = t.getSuperclass();			
		}
		return(sb.toString());
	}
	private static void fillBuffer(Object o, Class c, StringBuffer sb)
	{
		Field f[] = c.getDeclaredFields();
		Field.setAccessible(f,true);
		for(int i=0; i < f.length; ++i) {
			int m = f[i].getModifiers();
			if (Modifier.isFinal(m)) {				
				continue;			
			}
			sb.append(f[i].getName());
			sb.append("=");			
			try {
					Object obj = f[i].get(o);
					if(obj != null) {
						sb.append(obj.toString());
					} else {
						sb.append("null");
					}
			} catch(IllegalAccessException ile){
					sb.append("NO_ACCESS");
			}
			sb.append(';');						
		}
	}
	}
