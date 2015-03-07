package onjlib.utils;

import java.util.Vector;
import java.util.ArrayList;

public class PagingManager {
	int m_chunkSize;
	Object m_list[];
	ArrayList m_chunk;
	int m_marker = 0;
	int m_lastChunk = 0;

	public PagingManager(int chunkSize)
	{
		setChunkSize(chunkSize);
	}

	public PagingManager(int chunkSize, Object list[])
	{
		setChunkSize(chunkSize);
		setObjectList(list);
	}

	public void setChunkSize(int chunkSize)
	{
		m_chunkSize = chunkSize;
		m_chunk = new ArrayList(chunkSize);
	}

	public void setObjectList(Object list[])
	{
		m_list = list;
	}

	public int getChunkSize()
	{
		return(m_chunkSize);
	}

	public boolean hasMoreElements()
	{
		return(m_marker<=m_list.length-1 ? true: false);
	}

	public boolean hasPrevElements()
	{
		return(m_marker-m_lastChunk > 0 ? true: false);
	}
	public Object[] nextChunk()
	{
		for(int i=0; i < m_chunk.size(); ++i) {
			m_chunk.remove(i);
		}
		for(int i=0; m_marker <= m_list.length-1 && i<m_chunkSize;
				++i,++m_marker)
		{
			m_chunk.add(m_list[m_marker]);
		}
		m_lastChunk = m_chunk.size();
		return(m_chunk.toArray());
	}

	public Object[] prevChunk()
	{
		m_marker = m_marker - m_chunkSize -m_lastChunk;
		if(m_marker < 0) {
			m_marker = 0;
		}
		Object l[] = nextChunk();
		m_marker += m_chunkSize;
		return(l);
	}


	public static void main(String argv[])
	{
		Vector v = new Vector();
		for(int i=0; i<43; ++i) {
			Integer n = new Integer(i);
			v.add(n);
		}
		PagingManager pm = new PagingManager(10,v.toArray());
		Object chunk[] = null;
		while(pm.hasMoreElements()) {
			chunk = pm.nextChunk();
			System.out.println("getting next chunk ..");
			for(int i=0; i<chunk.length; ++i) {
				System.out.println("chunk[" + i+"]=" +
						chunk[i].toString());
			}
			if(pm.hasPrevElements()) {
				chunk = pm.prevChunk();

				System.out.println("getting prev chunk ..");
				for(int i=0; i<chunk.length; ++i) {
					System.out.println("chunk[" + i+"]=" +
						chunk[i].toString());
				}
			}
		}


	}

}
