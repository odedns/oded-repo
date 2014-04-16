
package onjlib.utils.cache;

/**
 * The cache entry class.
 * contains cache data and the entry's timestamp.
 */
class CacheEntry {
	Object m_data;
	long   m_timeStamp;

	CacheEntry(Object data, long timeStamp)
	{
		m_data = data;
		m_timeStamp = timeStamp;
	}

	public String toString()
	{
		return("timeStamp: " + m_timeStamp + " Data: " +
				m_data.toString());
	}
}
