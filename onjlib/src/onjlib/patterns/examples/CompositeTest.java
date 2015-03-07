package onjlib.patterns.examples;

import java.util.*;

import onjlib.patterns.CompositeIF;

class Emp implements CompositeIF {
	String m_name;
	ArrayList m_ar;
	Emp(String name)
	{
		m_name = name;		
		m_ar = new ArrayList();
	}
	
	public void add(Object o)
	{
		m_ar.add(o);
	}
	
	public void remove(Object o)
	{
		m_ar.remove(o);
	}
	public Iterator elements()
	{
		return(m_ar.iterator());

	}
	
	public String toString()
	{
		return(m_name);
	}

}
/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CompositeTest {

	public static void main(String[] args) {
		
		Emp e = new Emp("Jim");
		for(int i=0; i < 5; ++i) {
			e.add(new Emp("emp-" + i));
		}
		
		System.out.println("Emp = " + e.toString());
		Iterator iter = e.elements();
		while(iter.hasNext()) {
			Emp emp = (Emp)iter.next();
			System.out.println("emp = " + emp.toString());
		}
	}
}
