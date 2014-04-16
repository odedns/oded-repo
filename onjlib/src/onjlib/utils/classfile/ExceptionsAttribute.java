package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ExceptionsAttribute extends Attribute {

	int m_arrLen;
	int m_index[];

	public ExceptionsAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_arrLen);
		for(int i=0; i < m_arrLen; ++i) {
			dos.writeShort(m_index[i]);
		}
	}

	public void read(DataInputStream dis) throws IOException
	{
		m_arrLen = dis.readUnsignedShort();
		m_index = new int[m_arrLen];
		for(int i=0; i < m_arrLen; ++i) {
			m_index[i] = dis.readUnsignedShort();
		}
	}

	public String toString()
	{
		return(new String("ExceptionsAttribute:" + m_arrLen));
	}
}
