
package onjlib.utils;

import java.util.*;



class ObjectPoolEntry {
	long m_millis;
	Object m_object;

	ObjectPoolEntry(Object o)
	{
		m_millis = System.currentTimeMillis();
		m_object = o;
	}
	Object getObject()
	{
		return(m_object);
	}

	void update()
	{
		m_millis = System.currentTimeMillis();
	}
	long getMillis()
	{
		return(m_millis);
	}
}

public class ObjectPool extends Thread {
	static final int DEFAULT_MIN_SIZE = 10;
	static final int DEFAULT_MAX_SIZE = 50;
	static final int DEFAULT_SIZE_INTERVAL = 10;
	/**
	 * time to live in seconds.
	 */
	static final long DEFAULT_TTL = 1;
	int m_minSize;
	int m_maxSize;
	ObjectConstructorIF m_ctor;
	ArrayList m_pool;
	int m_poolSize;
	long m_ttl;
	boolean m_continue = true;

	/**
	 * ObjectPool constructor.
	 * @param ctor an Object Constructor Interface.
	 */
	public ObjectPool(ObjectConstructorIF ctor)
	{
		m_minSize = DEFAULT_MIN_SIZE;
		m_maxSize = DEFAULT_MAX_SIZE;
		m_ctor = ctor;
		m_pool = new ArrayList(m_maxSize);
		m_poolSize = 0;
		init();
		start();
	}


	/**
	 * initialize ObjectPool
	 */
	void init()
	{
		Object o;
		ObjectPoolEntry entry;

		Debug.println("init: creating objects in pool");
		for(int i=0; i < m_minSize; ++i) {
			o = m_ctor.create();
			entry = new ObjectPoolEntry(o);
			m_pool.add(entry);
		}
		m_poolSize = m_minSize;
	}


	public void run()
	{
		Debug.println("starting ObjectPool Thread ..");
		while(m_continue) {
			try {
				Thread.sleep(6000);
			} catch(Exception e) {
			}
			for(int i=0; i< m_pool.size(); ++i) {
				/*
				 * do not release the minimum number of connections.
				 */
				if(m_pool.size() <= m_minSize) {
					break;
				}
				ObjectPoolEntry entry = (ObjectPoolEntry) m_pool.get(i);
				if( (System.currentTimeMillis() - entry.getMillis()) >
					 ( m_ttl * 1000) || !m_ctor.valid(entry.getObject()) ) {
						Debug.println("removing expired objects");
						removeObject(i);
				} // if
			} // for

		}
		Debug.println("Thread exiting");

	}
	public void setMaxSize(int maxSize)
	{
		m_maxSize = maxSize;
	}

	public void setMinSize(int minSize)
	{
		m_minSize = minSize;
	}

		public void setTTL(long ttl)
		{
			m_ttl = ttl;
		}
		public long getTTL()
	{
			return(m_ttl);
		}
	public int getMaxSize()
	{
		return(m_maxSize);
	}

	public int getMinSize()
	{
		return(m_minSize);
	}

	/**
	 * get an Object from the ObjectPool.
	 * @return Object the object retrieved from the pool.
	 */
	public synchronized Object getObject()
	{
		Object o;

		while(null == (o = _getObject())) {
			Debug.println("getObject: object is invalid, trying another ..");
		}
		return(o);

	}

	private Object _getObject()
	{
		Object o;
		ObjectPoolEntry entry;

		/*
		 * if the pool size is at maxSize then we
		 * have to wait until an Object is released.
		 */
		if(m_poolSize == m_maxSize) {
			Debug.println("getObject: blocking until pool has free resources");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		/*
		 * if the pool is not empty return an Object
		 * from the pool.
		 */
		if(m_pool.size() > 0) {
			entry = (ObjectPoolEntry) m_pool.get(0);
			o = entry.getObject();
			if(!m_ctor.valid(o)) {
				return(null);
			}
			m_pool.remove(0);


		} else {

			/*
			 * increase the poolsize by an interval.
			 */
			int i;
			for(i=0; i<DEFAULT_SIZE_INTERVAL &&
					(m_poolSize+i)<m_maxSize;
					++i) {
				o = m_ctor.create();
				entry = new ObjectPoolEntry(o);
				m_pool.add(entry);
			}
			Debug.println("getObject: pool increased by: " +i);
			m_poolSize+=i;
			entry = (ObjectPoolEntry) m_pool.get(0);
			o = entry.getObject();
			if(!m_ctor.valid(o)) {
				return(null);
			}
			m_pool.remove(0);
		}

		return(o);
	}

	/**
	 * return the Object to the ObjectPool
	 * @param o The Object to return to the pool.
	 */
	public synchronized void releaseObject(Object o)
	{
		Debug.println("releasing object ");
		ObjectPoolEntry entry = new ObjectPoolEntry(o);
		m_pool.add(entry);
		notify();
	}

	/**
	 * remove an object from the pool
	 * and release its resources.
	 */
	public synchronized void removeObject(int index)
	{
		Debug.println("removing object ");
		ObjectPoolEntry entry = (ObjectPoolEntry) m_pool.get(index);
		Object o = entry.getObject();
		m_ctor.destroy(o);
		m_pool.remove(index);

	}

	public void close()
	{
		releaseAll();
		m_continue = false;

	}

	private void releaseAll()
	{
		for(int i=0; i < m_pool.size(); ++i) {
			removeObject(i);
		}
	}


}
