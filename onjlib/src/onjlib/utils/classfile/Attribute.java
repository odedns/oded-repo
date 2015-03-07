package onjlib.utils.classfile;

import onjlib.utils.Debug;
import java.io.*;

/**
 * Title: Attribute.java
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Oded Nissan
 * @version 1.0 14/01/2002
 */

public abstract class Attribute {

	int m_nameIndex;
	int m_length;

	public Attribute(int nameIndex, int len)
	{
		m_length = len;
		m_nameIndex = nameIndex;
	}

	public int getLength()
	{
		return(m_length);
	}

	public int getNameIndex()
	{
		return(m_nameIndex);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		Debug.println("nameInx = " + m_nameIndex + " len = " + m_length);
		dos.writeShort(m_nameIndex);
		dos.writeInt(m_length);
		Debug.println("Attribute.write() " + this.toString());
	}

	public abstract void read(DataInputStream dis) throws IOException;
	public abstract String toString();
}
