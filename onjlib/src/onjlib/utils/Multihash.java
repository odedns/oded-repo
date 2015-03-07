

package onjlib.utils;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Multihash class implements a hash table with duplicate
 * keys. The values for each key are stored in a vector
 * in the hashtable.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 10/6/1999 - Initial version.
 * @version 2.00 28/3/2002 - Convert to an extension of Hashmap and use ArrayList
 * instead of Vector to store the duplicate values.
 */
public class Multihash extends HashMap {

	/**
	 * constructor creates an empty Multihash object.
	 */
	public Multihash()
	{
		super();
	}

	/**
	 * constructor creates an empty Multihash object.
	 * @param initialCapacity the initialCapacity of the Hashtable.
	 */
	public Multihash(int initialCapacity)
	{
		super(initialCapacity);
	}

	/**
	 * put a value into the hashtable.
	 * If the current key exists, add the value to the
	 * Vector containing values for the current key.
	 * If the key does not exits, create a Vector insert the value
	 * into the vector and put the vector into the hashtable.
	 * @param key the key the value is mapped to.
	 * @param value the value to be inserted.
	 * @return Object the Object that was replaced with this value
	 * or null if an Object for the current key did not exist in the hash
	 * table.
	 */
	public Object put(Object key, Object value)
	{
		ArrayList ar = null;

		/**
		 * if the key does not exist in the HashMap we will
		 * insert it as a  new ArrayList.
		 * If it does exist we will add an entry to the ArrayList.
		 */
		ar = (ArrayList) super.get(key);

		if(null == ar) {
			ar = new ArrayList(1);
		}
		ar.add(value);
		return(super.put(key,ar));
	}

	public Object put(Object key, Object values[])
	{
		ArrayList ar = null;

		/**
		 * if the key does not exist in the HashMap we will
		 * insert it as a  new ArrayList.
		 * If it does exist we will add an entry to the ArrayList.
		 */
		ar = (ArrayList) super.get(key);

		if(null == ar) {
			ar = new ArrayList(values.length);
		}
		for(int i=0; i< values.length; ++i) {
			ar.add(values[i]);
		}
		return(super.put(key,ar));
	}

	/**
	 * get - get the first value bound to the key from the HashMap.
	 * If there is more than one value bound to the key, only the first value is returned.
	 * @param key the lookup key in the HashMap.
	 * @return Object value or null if the entry is not found.
	 */
	public Object get(String key)
	{
		ArrayList ar = (ArrayList) super.get(key);
		return ( null == ar ) ? (null) : (ar.get(0));

	}

	/**
	 * Get the ArrayList stored at the specific key.
	 * @param key the key to search for.
	 * @return ArrayList the ArrayList object to return, or null
	 * if the entry does not exist.
	 */
	public ArrayList getValuesList(String key)
	{
		return((ArrayList) super.get(key));
	}


	/**
	 * getValues - get all values bound to the key from the HashMap.
	 * return them as an array of objects.
	 * @param key the lookup key in the HashMap.
	 * @return Object[] the array of values or null if the entry is not found.
	 */
	public Object[] getValues(String key)
	{
		ArrayList ar = (ArrayList) super.get(key);
		return ( null == ar ) ? (null) : (ar.toArray());
	}

	/**
	 * getValues - get all values bound to the key from the HashMap.
	 * return them as an array of objects.
	 * @param key the lookup key in the HashMap.
	 * @return Object[] the array of values or null if the entry is not found.
	 */
	public Object[] getValues(String key, Object values[])
	{
		ArrayList ar = (ArrayList) super.get(key);
		return ( null == ar ) ? (null) : (ar.toArray(values));
	}


}
