package onjlib.utils.indexfile;

import java.io.*;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

public class IndexFile {

	ByteArrayOutputStream m_bos;
	RandomAccessFile m_rf;
	ObjectOutputStream m_os;
	ObjectInputStream m_is;

	public IndexFile(String fname) throws IOException
	{
	}

	public void write(String key, Object o) throws IOException
	{
	}

	public Object find(String key) throws IOException
	{
		return(null);
	}


	public void update(String key, Object o) throws IOException
	{
	}


	public void delete(String key) throws IOException
	{
	}

}
