
package onjlib.utils;

public class IntConstructor implements ObjectConstructorIF {

	public Object create()
	{
		return(new Integer(10));
	}
	public Object create(Object o)
	{
		return(new Integer(10));
	}

	public void destroy(Object o)
	{
	}

	public boolean valid(Object o)
	{
		return(true);
	}

}
