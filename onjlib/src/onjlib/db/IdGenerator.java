/*
 * Created on 12/01/2006
 */
package onjlib.db;

/**
 * @author odedn
 * This is an interface defining a uid generator.
 * uid generator implementations should implement
 * this interface.
 */
public interface IdGenerator {
	/**
	 * Get the next key from the
	 * UID generator.
	 * @return long the generated key.
	 */
	public long nextVal();

}
