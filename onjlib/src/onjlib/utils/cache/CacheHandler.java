
package onjlib.utils.cache;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import onjlib.utils.Mutex;

class CacheHandler extends Thread {
	Hashtable m_cacheTable;
	Vector m_vec;
	long m_ttl;
	Mutex m_mutex;
	boolean m_continue = true;

	CacheHandler(Hashtable hash, Vector vec, long ttl)
	{
		m_cacheTable = hash;
		m_vec = vec;
		m_ttl = ttl;
		m_mutex = new Mutex();
	}


	public void run()
	{
		Enumeration e;
		CacheEntry entry;
		Object key;
		int removed = 0;

		System.out.println("CacheHandler started");
		while(m_continue) {
			m_mutex.lock();
			System.out.println("CacheHandler active");
			e = m_cacheTable.keys();
			while(e.hasMoreElements()) {
				key = e.nextElement();
				entry = (CacheEntry) m_cacheTable.get(key);
				/**
				* if entry expired remove it.
				 */
				if(m_ttl <
				   (System.currentTimeMillis() - entry.m_timeStamp)) {
					m_cacheTable.remove(key);
					System.out.println("Removing entry");
					++removed;
				}
			}
			System.out.println("CacheHandler done");

		} /* while */
} /* run */

	public void stopRunning()
	{
		m_continue = false;
	}



}
