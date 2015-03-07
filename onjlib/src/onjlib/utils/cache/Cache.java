
package onjlib.utils.cache;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;



/**
 * Cache class manages an object cache which is stored
 * in a hash table.
 */
public class Cache {

	/**
	 * the default ratio for cache threshold.
	 */
	static final double THRESHOLD_RATIO = 0.1;
	/**
	 * the default ratio for cache cleanup.
	 * if no entries have expired and the cache maxSize
	 * is passed then we clean up entries by the cleanup
	 * ratio multiplied by the maxSize.
	 */
	static final double CLEANUP_RATIO = 0.2;
	long m_maxSize;
	long m_threshold;
	/**
	 * the hash table containing cache entries.
	 */
	Hashtable m_cacheTable;
	/**
	 * cache entry's time to live in ms.
	 */
	long m_ttl;

	/**
	 * the number of entries to cleanup if there are
	 * no expired entries.
	 */
	long m_cleanupSize;
	/**
	 * a vector storing the keys of the entries so that we
	 * can do a FIFO based cleanup in case none of the
	 * entries are expired.
	 */
	Vector m_vec;

	/**
	 * the cache handler thread.
	 * This handler thread is responsible for cleaning the cache.
	 */
	CacheHandler m_handler;

	/**
	 * create a cache object.
	 * @param maxSize the maximum size of the cache, when we exceed this
	 * size, the cache activates the handler thread to do some
	 * cleanup.
	 * @param threshold
	 * @params cleanup the number of entries to remove from the cache
	 * in case none of the entries has expired.
	 * @param ttl the cache entry's time to live in ms.
	 */
	public Cache(long maxSize, long threshold, long cleanup, long ttl)
	{
		init(maxSize,threshold,cleanup,ttl);
	}


	/**
	 * create a cache object.
	 * @param maxSize the maximum size of the cache, when we exceed this
	 * size, the cache activates the handler thread to do some
	 * cleanup.
	 * @param ttl the cache entry's time to live in ms.
	 */
	public Cache(long maxSize, long ttl)
	{
		long cleanupSize;

		m_threshold = (long) (maxSize * THRESHOLD_RATIO);
		cleanupSize = (long) (maxSize * CLEANUP_RATIO);

		init(maxSize,m_threshold,cleanupSize,ttl);
	}

	void init(long maxSize, long threshold, long cleanup, long ttl)
	{
		m_maxSize = maxSize;
		m_threshold = threshold;
		m_ttl = ttl;
		m_cleanupSize = cleanup;
		m_vec = new Vector((int)m_cleanupSize);
		m_cacheTable = new Hashtable();
		m_handler = new CacheHandler(m_cacheTable,m_vec,m_ttl);
		m_handler.start();
	}


	public long getMaxSize()
	{
		return(m_maxSize);
	}

	public long getThreashold()
	{
		return(m_threshold);
	}


	public synchronized void put(Object key, Object data)
	{
		CacheEntry entry = new CacheEntry(data,
				System.currentTimeMillis());
		m_cacheTable.put(key,entry);
		/**
		 * add the key to the key vector.
		 */
		m_vec.addElement(key);

		if(m_cacheTable.size() > m_maxSize) {
			System.out.println("activating handler cleanup thread");
			m_handler.m_mutex.unlock();
		}
	}

	public Object get(Object key)
	{
		CacheEntry entry = (CacheEntry)m_cacheTable.get(key);
		if(m_ttl >
			(System.currentTimeMillis() - entry.m_timeStamp)) {
			return(entry.m_data);
		} else {
			System.out.println("entry expired");
			return(null);
		}
	}

	void dumpCache()
	{
		Enumeration e;
		CacheEntry entry;
		Object key;
		e = m_cacheTable.keys();
		while(e.hasMoreElements()) {
			key = e.nextElement();
			entry = (CacheEntry) m_cacheTable.get(key);
			System.out.println("entry = " + entry.toString());
		}
	}


	public long getTtl()
	{
		return(m_ttl);
	}

	protected void finalize()
	{
		try { 
			m_handler.stopRunning();
		} finally {
			try {
				super.finalize();
			} catch(Throwable th){				
			}
		}
		
	}
	public static void main(String argv[])
	{
		Cache c = new Cache(10, 10);
		for(int i=0; i < 20; ++i) {
			String key = "" + i;
			String s = "item# " + i;
			c.put(key,s);
		}
		c.dumpCache();
	}
}

