/*
 * Created on 12/01/2006
 */
package onjlib.db;

/**
 * @author odedn
 *
 * A factory for instantiating an IdGenerator object.
 */
public class IdGeneratorFactory {

	/**
	 * 
	 */
	private IdGeneratorFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get an IdGeneraotr instance. By instantiating the 
	 * default IdGenerator implementation from a property file.
	 * @return IdGenerator the generator object.
	 */
	public static IdGenerator getIdGenerator()
	{
		return(null);
	}
	
	/**
	 * Get an IdGeneraotr instance by instantiating
	 * the class.
	 * @param className the IdGenerator implementation class.
	 * @return IdGenerator the generator object.
	 * @throws DbException in case of error.
	 */
	public static IdGenerator getIdGenerator(String className) throws DbException
	{
		IdGenerator gen = null;
		try {
			gen = (IdGenerator)Class.forName(className).newInstance();
		} catch(Exception e) {
			throw new DbException("error initializing IdGenerator: " + className,e);
		}
		return(gen);
	}

}
